package zowe.client.sdk.examples.zosfiles;

import org.apache.commons.io.IOUtils;
import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.zosfiles.dsn.input.DownloadParams;
import zowe.client.sdk.zosfiles.dsn.methods.DsnGet;

import java.io.InputStream;
import java.io.StringWriter;

/**
 * Class example to showcase DownloadDataset functionality.
 *
 * @author Leonid Baranov
 * @author Frank Giordano
 * @version 2.0
 */
public class DownloadDatasetTst extends TstZosConnection {

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * DownloadDataset functionality. Calls DownloadDataset example methods.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Leonid Baranov
     */
    public static void main(String[] args) throws Exception {
        String datasetName = "xxx.xxx.xxx";
        String memberName = "xxx";
        DownloadParams params = new DownloadParams.Builder().build();
        ZOSConnection connection = new ZOSConnection(hostName, zosmfPort, userName, password);
        DownloadDatasetTst.downloadDsnMember(connection, datasetName, memberName, params);
    }

    /**
     * Download a dataset member
     *
     * @param connection ZOSConnection object
     * @param dsName     data set name
     * @param memName    member name that exists within the specified data set name
     * @param params     download parameters object
     * @throws Exception error processing request
     * @author Leonid Baranov
     */
    public static void downloadDsnMember(ZOSConnection connection, String dsName, String memName,
                                         DownloadParams params) throws Exception {
        try (InputStream inputStream = new DsnGet(connection)
                .get(String.format("%s(%s)", dsName, memName), params)) {
            if (inputStream != null) {
                StringWriter writer = new StringWriter();
                IOUtils.copy(inputStream, writer, "UTF8");
                String content = writer.toString();
                System.out.println(content);
            }
        }
    }

}
