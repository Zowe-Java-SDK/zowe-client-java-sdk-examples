package zowe.client.sdk.examples.zosfiles.uss;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.rest.Response;
import zowe.client.sdk.zosfiles.dsn.methods.DsnWrite;

/**
 * Class example to showcase WriteDataset functionality via DsnWrite class.
 *
 * @author Leonid Baranov
 * @author Frank Giordano
 * @version 2.0
 */
public class WriteDatasetTst extends TstZosConnection {

    private static ZosConnection connection;

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * DsnWrite functionality.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Leonid Baranov
     */
    public static void main(String[] args) throws Exception {
        String dataSetName = "xxx";
        String datasetSeqName = "xxx";
        String member = "xxx";
        connection = new ZosConnection(hostName, zosmfPort, userName, password);
        var content = "NEW CONTENT\nTHE SECOND LINE UPDATED";
        WriteDatasetTst.writeToDsnMember(dataSetName, member, content);
        WriteDatasetTst.writeToDsnSequential(datasetSeqName, content);
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

    /**
     * Write to the given content to sequential dataset.
     *
     * @param dataSetName name of sequential dataset (e.g. 'DATASET.LIB')
     * @param content     content for write
     * @author Frank Giordano
     */
    public static void writeToDsnSequential(String dataSetName, String content) {
        DsnWrite dsnWrite = new DsnWrite(connection);
        Response response = dsnWrite.write(dataSetName, content);
        System.out.println("http response code " + response.getStatusCode());
    }

}
