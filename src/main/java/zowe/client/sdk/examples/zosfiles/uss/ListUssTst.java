package zowe.client.sdk.examples.zosfiles.uss;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.zosfiles.uss.input.ListParams;
import zowe.client.sdk.zosfiles.uss.input.ListZfsParams;
import zowe.client.sdk.zosfiles.uss.methods.UssList;
import zowe.client.sdk.zosfiles.uss.response.UnixFile;
import zowe.client.sdk.zosfiles.uss.response.UnixZfs;

import java.util.List;

/**
 * Class example to showcase UssList class functionality.
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class ListUssTst extends TstZosConnection {

    private static ZosConnection connection;

    /**
     * Main method performs setup and method calls to test UssList
     *
     * @param args for main not used
     * @throws Exception error processing request
     * @author Frank Giordano
     */
    public static void main(String[] args) throws Exception {
        String fileNamePath = "/xxx/xx/xx";
        String dirNamePath = "/xxx/xx/xx";

        connection = new ZosConnection(hostName, zosmfPort, userName, password);
        fileList(fileNamePath);
        zfsList(dirNamePath);
    }

    /**
     * Perform a UNIX zFS list
     *
     * @param value file name with path
     * @throws Exception processing error
     * @author Frank Giordano
     */
    private static void zfsList(String value) throws Exception {
        UssList ussList = new UssList(connection);
        ListZfsParams params = new ListZfsParams.Builder().path(value).build();
        List<UnixZfs> items = ussList.getZfsSystems(params);
        items.forEach(System.out::println);
    }

    /**
     * Perform a UNIX file list
     *
     * @param value file name with path
     * @throws Exception processing error
     * @author Frank Giordano
     */
    private static void fileList(String value) throws Exception {
        UssList ussList = new UssList(connection);
        ListParams params = new ListParams.Builder().path(value).build();
        List<UnixFile> items = ussList.getFiles(params);
        items.forEach(System.out::println);
    }

}
