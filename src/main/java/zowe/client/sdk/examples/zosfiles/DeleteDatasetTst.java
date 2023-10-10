package zowe.client.sdk.examples.zosfiles;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.rest.Response;
import zowe.client.sdk.zosfiles.dsn.methods.DsnDelete;

/**
 * Class example to showcase DeleteDataset functionality.
 *
 * @author Leonid Baranov
 * @author Frank Giordano
 * @version 2.0
 */
public class DeleteDatasetTst extends TstZosConnection {

    private static ZosConnection connection;

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * DeleteDataset functionality. Calls DeleteDataset example methods.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Leonid Baranov
     */
    public static void main(String[] args) throws Exception {
        String dataSetName = "xxx";
        String member = "xxx";
        connection = new ZosConnection(hostName, zosmfPort, userName, password);
        deleteDataSet(dataSetName);
        deleteMember(dataSetName, member);
    }

    /**
     * @param dataSetName name of a dataset to delete (e.g. 'DATASET.LIB')
     * @author Frank Giordano
     */
    public static void deleteDataSet(String dataSetName) {
        DsnDelete zosDsn = new DsnDelete(connection);
        Response response = zosDsn.delete(dataSetName);
        System.out.println("http response code " + response.getStatusCode());
    }

    /**
     * @param dataSetName name of a dataset where member should be located (e.g. 'DATASET.LIB')
     * @param member      name of member to delete
     * @throws Exception error processing request
     * @author Frank Giordano
     */
    public static void deleteMember(String dataSetName, String member) throws Exception {
        DsnDelete zosDsn = new DsnDelete(connection);
        Response response = zosDsn.delete(dataSetName, member);
        System.out.println("http response code " + response.getStatusCode());
    }

}
