package zowe.client.sdk.examples.zosfiles;

import org.apache.commons.io.IOUtils;
import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.zosfiles.ZosDsnDownload;
import zowe.client.sdk.zosfiles.input.DownloadParams;

import java.io.InputStream;
import java.io.StringWriter;

/**
 * Class example to showcase DownloadDataset functionality.
 *
 * @author Leonid Baranov
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
        String datasetMember = "xxx";
        DownloadParams params = new DownloadParams.Builder().build();
        ZOSConnection connection = new ZOSConnection(hostName, zosmfPort, userName, password);
        DownloadDatasetTst.downloadDsnMember(connection, datasetMember, params);
    }

    /**
     * Download dataset members
     *
     * @param connection ZOSConnection object
     * @param name       data set name
     * @param params     download parameters object
     * @throws Exception error processing request
     * @author Leonid Baranov
     */
    public static void downloadDsnMember(zowe.client.sdk.core.ZOSConnection connection, String name, DownloadParams params) throws Exception {
        try (InputStream inputStream = new ZosDsnDownload(connection).downloadDsn(name, params)) {
            if (inputStream != null) {
                StringWriter writer = new StringWriter();
                IOUtils.copy(inputStream, writer, "UTF8");
                String content = writer.toString();
                System.out.println(content);
            }
        }
    }

}
