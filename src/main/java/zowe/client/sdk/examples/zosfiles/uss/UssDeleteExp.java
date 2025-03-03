package zowe.client.sdk.examples.zosfiles.uss;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.examples.utility.Util;
import zowe.client.sdk.rest.Response;
import zowe.client.sdk.rest.exception.ZosmfRequestException;
import zowe.client.sdk.zosfiles.uss.methods.UssDelete;

/**
 * Class example to test unix system services delete command functionality via UssDelete class.
 *
 * @author Frank Giordano
 * @version 3.0
 */
public class UssDeleteExp extends TstZosConnection {

    private static zowe.client.sdk.zosfiles.uss.methods.UssDelete ussDelete;

    /**
     * Main method performs setup and method calls to test UssDelete
     *
     * @param args for main not used
     * @author Frank Giordano
     */
    public static void main(String[] args) {
        String fileNamePath = "/xxx/xx/xx";
        String dirNamePath = "/xxx/xx/xx";

        ZosConnection connection = new ZosConnection(hostName, zosmfPort, userName, password);
        ussDelete = new UssDelete(connection);
        Response response = DeleteFile(fileNamePath);
        System.out.println("status code = " +
                (response.getStatusCode().isEmpty() ? "no status code available" : response.getStatusCode().getAsInt()));
        response = DeleteDirectory(dirNamePath);
        System.out.println("status code = " +
                (response.getStatusCode().isEmpty() ? "no status code available" : response.getStatusCode().getAsInt()));
    }

    /**
     * Delete a UNIX file
     *
     * @param value file name with path to delete
     * @return Response object
     * @author Frank Giordano
     */
    private static Response DeleteFile(String value) {
        try {
            return ussDelete.delete(value);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
    }

    /**
     * Delete UNIX files and directories within recursively
     *
     * @param value directory name with path to delete
     * @return Response object
     * @author Frank Giordano
     */
    private static Response DeleteDirectory(String value) {
        try {
            return ussDelete.delete(value, true);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
    }

}
