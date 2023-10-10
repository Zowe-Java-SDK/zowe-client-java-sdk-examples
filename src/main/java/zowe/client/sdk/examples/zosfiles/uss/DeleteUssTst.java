package zowe.client.sdk.examples.zosfiles.uss;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.rest.Response;
import zowe.client.sdk.zosfiles.uss.methods.UssDelete;

/**
 * Class example to test unix system services delete command functionality via UssDelete class.
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class DeleteUssTst extends TstZosConnection {

    private static ZosConnection connection;
    private static UssDelete ussDelete;

    /**
     * Main method performs setup and method calls to test UssDelete
     *
     * @param args for main not used
     * @throws Exception error processing request
     * @author Frank Giordano
     */
    public static void main(String[] args) throws Exception {
        String fileNamePath = "/xxx/xx/xx";
        String dirNamePath = "/xxx/xx/xx";

        connection = new ZosConnection(hostName, zosmfPort, userName, password);
        ussDelete = new UssDelete(connection);
        Response response = DeleteFile(fileNamePath);
        System.out.println(response.getStatusCode().getAsInt());
        response = DeleteDirectory(dirNamePath);
        System.out.println(response.getStatusCode().getAsInt());
    }

    /**
     * Delete a Unix System Service file
     *
     * @param value file name with path to delete
     * @return Response object
     * @throws Exception processing error
     */
    private static Response DeleteFile(String value) throws Exception {
        return ussDelete.delete(value);
    }

    /**
     * Delete a Unix System Service path along with all file and directories within recursively
     *
     * @param value directory name with path to delete
     * @return Response object
     */
    private static Response DeleteDirectory(String value) {
        return ussDelete.delete(value, true);
    }

}
