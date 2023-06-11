package zowe.client.sdk.examples.zosfiles;

import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.rest.Response;
import zowe.client.sdk.zosfiles.dsn.methods.DsnWrite;

/**
 * Class example to showcase WriteDataset functionality.
 *
 * @author Leonid Baranov
 * @author Frank Giordano
 * @version 2.0
 */
public class WriteDatasetTst extends TstZosConnection {

    private static ZOSConnection connection;

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * WriteDataset functionality. Calls WriteDataset example methods.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Leonid Baranov
     */
    public static void main(String[] args) throws Exception {
        String dataSetName = "xxx";
        String member = "xxx";
        connection = new ZOSConnection(hostName, zosmfPort, userName, password);
        var content = "NEW CONTENT\nTHE SECOND LINE UPDATED";
        WriteDatasetTst.writeToDsnMember(dataSetName, member, content);
    }

    /**
     * Write to the given member name specified replacing its content. If it does exist, it will be created.
     *
     * @param dataSetName name of a dataset where member should be located (e.g. 'DATASET.LIB')
     * @param member      name of member to write
     * @param content     content for write
     * @throws Exception error processing request
     * @author Frank Giordano
     */
    public static void writeToDsnMember(String dataSetName, String member, String content) throws Exception {
        DsnWrite dsnWrite = new DsnWrite(connection);
        Response response = dsnWrite.write(dataSetName, member, content);
        System.out.println("http response code " + response.getStatusCode());
    }

}
