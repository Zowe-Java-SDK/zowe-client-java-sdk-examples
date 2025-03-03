package zowe.client.sdk.examples.zosfiles.uss;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.examples.utility.Util;
import zowe.client.sdk.rest.exception.ZosmfRequestException;
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
 * @version 3.0
 */
public class UssListExp extends TstZosConnection {

    private static ZosConnection connection;

    /**
     * Main method performs setup and method calls to test UssList
     *
     * @param args for main not used
     * @author Frank Giordano
     */
    public static void main(String[] args) {
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
     * @author Frank Giordano
     */
    private static void zfsList(String value) {
        List<UnixZfs> items;
        try {
            UssList ussList = new UssList(connection);
            ListZfsParams params = new ListZfsParams.Builder().path(value).build();
            items = ussList.getZfsSystems(params);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }

        items.forEach(System.out::println);
    }

    /**
     * Perform a UNIX file list
     *
     * @param value file name with path
     * @author Frank Giordano
     */
    private static void fileList(String value) {
        List<UnixFile> items;
        try {
            UssList ussList = new UssList(connection);
            ListParams params = new ListParams.Builder().path(value).build();
            items = ussList.getFiles(params);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }

        items.forEach(System.out::println);
    }

}
