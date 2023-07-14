package zowe.client.sdk.examples.zosfiles;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.rest.Response;
import zowe.client.sdk.zosfiles.uss.input.CreateParams;
import zowe.client.sdk.zosfiles.uss.methods.UssCreate;
import zowe.client.sdk.zosfiles.uss.types.CreateType;

/**
 * Class example to test unix system services create command functionality via UssCreate class.
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class CreateUssTst extends TstZosConnection {

    private static ZosConnection connection;

    /**
     * Main method performs setup and method calls to test UssCreate
     *
     * @param args for main not used
     * @throws Exception error processing request
     * @author Frank Giordano
     */
    public static void main(String[] args) throws Exception {
        String fileNamePath = "/xxx/xx/xx";
        String dirNamePath = "/xxx/xx/xx";

        connection = new ZosConnection(hostName, zosmfPort, userName, password);
        Response response = CreateFile(fileNamePath);
        System.out.println(response.getStatusCode().get());
        response = CreateDirectory(dirNamePath);
        System.out.println(response.getStatusCode().get());
    }

    /**
     * Create a Unix System Service directory
     *
     * @param value directory name with path to create
     * @return Response object
     * @throws Exception processing error
     * @author Frank Giordano
     */
    private static Response CreateDirectory(String value) throws Exception {
        UssCreate ussCreate = new UssCreate(connection);
        CreateParams params = new CreateParams(CreateType.DIR, "-wx-wx-wx");
        return ussCreate.create(value, params);
    }

    /**
     * Create a Unix System Service file
     *
     * @param value file name with path to create
     * @return Response object
     * @throws Exception processing error
     * @author Frank Giordano
     */
    private static Response CreateFile(String value) throws Exception {
        UssCreate ussCreate = new UssCreate(connection);
        CreateParams params = new CreateParams(CreateType.FILE, "-wx-wx-wx");
        return ussCreate.create(value, params);
    }

}
