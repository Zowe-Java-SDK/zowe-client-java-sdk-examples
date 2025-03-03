package zowe.client.sdk.examples.zosfiles.uss;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.examples.utility.Util;
import zowe.client.sdk.rest.Response;
import zowe.client.sdk.rest.exception.ZosmfRequestException;
import zowe.client.sdk.zosfiles.uss.input.CreateParams;
import zowe.client.sdk.zosfiles.uss.methods.UssCreate;
import zowe.client.sdk.zosfiles.uss.types.CreateType;

/**
 * Class example to test unix system services create command functionality via UssCreate class.
 *
 * @author Frank Giordano
 * @version 3.0
 */
public class UssCreateExp extends TstZosConnection {

    private static ZosConnection connection;

    /**
     * Main method performs setup and method calls to test UssCreate
     *
     * @param args for main not used
     * @author Frank Giordano
     */
    public static void main(String[] args) {
        String fileNamePath = "/xxx/xx/xx";
        String dirNamePath = "/xxx/xx/xx";

        connection = new ZosConnection(hostName, zosmfPort, userName, password);
        Response response = CreateFile(fileNamePath);
        System.out.println("status code = " +
                (response.getStatusCode().isEmpty() ? "no status code available" : response.getStatusCode().getAsInt()));
        response = CreateDirectory(dirNamePath);
        System.out.println("status code = " +
                (response.getStatusCode().isEmpty() ? "no status code available" : response.getStatusCode().getAsInt()));
    }

    /**
     * Create a Unix directory
     *
     * @param value directory name with path to create
     * @return Response object
     * @author Frank Giordano
     */
    private static Response CreateDirectory(String value) {
        try {
            UssCreate ussCreate = new UssCreate(connection);
            CreateParams params = new CreateParams(CreateType.DIR, "-wx-wx-wx");
            return ussCreate.create(value, params);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
    }

    /**
     * Create a Unix file
     *
     * @param value file name with path to create
     * @return Response object
     * @author Frank Giordano
     */
    private static Response CreateFile(String value) {
        try {
            UssCreate ussCreate = new UssCreate(connection);
            CreateParams params = new CreateParams(CreateType.FILE, "-wx-wx-wx");
            return ussCreate.create(value, params);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
    }

}
