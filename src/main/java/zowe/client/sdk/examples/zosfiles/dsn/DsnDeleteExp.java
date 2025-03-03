package zowe.client.sdk.examples.zosfiles.dsn;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.examples.utility.Util;
import zowe.client.sdk.rest.Response;
import zowe.client.sdk.rest.exception.ZosmfRequestException;
import zowe.client.sdk.zosfiles.dsn.methods.DsnDelete;

/**
 * Class example to showcase DeleteDataset functionality via DsnDelete class.
 *
 * @author Leonid Baranov
 * @author Frank Giordano
 * @version 3.0
 */
public class DsnDeleteExp extends TstZosConnection {

    private static ZosConnection connection;

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * DeleteDataset functionality. Calls DeleteDataset example methods.
     *
     * @param args for main not used
     * @author Leonid Baranov
     */
    public static void main(String[] args) {
        String dataSetName = "xxx";
        String member = "xxx";
        connection = new ZosConnection(hostName, zosmfPort, userName, password);
        deleteDataSet(dataSetName);
        deleteMember(dataSetName, member);
    }

    /**
     * Delete a dataset
     *
     * @param dataSetName name of a dataset to delete (e.g. 'DATASET.LIB')
     * @author Frank Giordano
     */
    public static void deleteDataSet(String dataSetName) {
        Response response;
        try {
            DsnDelete zosDsn = new DsnDelete(connection);
            response = zosDsn.delete(dataSetName);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }

        System.out.println("status code = " +
                (response.getStatusCode().isEmpty() ? "no status code available" : response.getStatusCode().getAsInt()));
    }

    /**
     * Delete a partition dataset member
     *
     * @param dataSetName name of a dataset where member should be located (e.g. 'DATASET.LIB')
     * @param member      name of member to delete
     * @author Frank Giordano
     */
    public static void deleteMember(String dataSetName, String member) {
        Response response;
        try {
            DsnDelete zosDsn = new DsnDelete(connection);
            response = zosDsn.delete(dataSetName, member);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }

        System.out.println("status code = " +
                (response.getStatusCode().isEmpty() ? "no status code available" : response.getStatusCode().getAsInt()));
    }

}
