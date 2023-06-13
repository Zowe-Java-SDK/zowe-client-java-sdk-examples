package zowe.client.sdk.examples.zosfiles;

import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.rest.Response;
import zowe.client.sdk.zosfiles.dsn.methods.DsnDelete;

/**
 * Class example to showcase DeleteDataset functionality vai DsnDelete class.
 *
 * @author Leonid Baranov
 * @author Frank Giordano
 * @version 2.0
 */
public class DeleteDatasetTst extends TstZosConnection {

    private static ZOSConnection connection;

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * DsnDelete functionality.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Leonid Baranov
     */
    public static void main(String[] args) throws Exception {
        String dataSetName = "xxx";
        String member = "xxx";
        connection = new ZOSConnection(hostName, zosmfPort, userName, password);
        deleteDataSet(dataSetName);
        deleteMember(dataSetName, member);
    }

    /**
     * Delete a dataset.
     *
     * @param dataSetName name of a dataset to delete (e.g. 'DATASET.LIB')
     * @throws Exception error processing request
     * @author Frank Giordano
     */
    public static void deleteDataSet(String dataSetName) throws Exception {
        DsnDelete dsnDelete = new DsnDelete(connection);
        Response response = dsnDelete.delete(dataSetName);
        System.out.println("http response code " + response.getStatusCode());
    }

    /**
     * Delete a member.
     *
     * @param dataSetName name of a dataset where member should be located (e.g. 'DATASET.LIB')
     * @param member      name of member to delete
     * @throws Exception error processing request
     * @author Frank Giordano
     */
    public static void deleteMember(String dataSetName, String member) throws Exception {
        DsnDelete dsnDelete = new DsnDelete(connection);
        Response response = dsnDelete.delete(dataSetName, member);
        System.out.println("http response code " + response.getStatusCode());
    }

}
