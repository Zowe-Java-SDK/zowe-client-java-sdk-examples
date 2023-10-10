package zowe.client.sdk.examples.zosfiles.uss;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.rest.Response;
import zowe.client.sdk.zosfiles.uss.input.CreateParams;
import zowe.client.sdk.zosfiles.uss.input.GetParams;
import zowe.client.sdk.zosfiles.uss.methods.UssCreate;
import zowe.client.sdk.zosfiles.uss.methods.UssGet;
import zowe.client.sdk.zosfiles.uss.methods.UssWrite;
import zowe.client.sdk.zosfiles.uss.types.CreateType;

/**
 * Class example to showcase UssGet class functionality.
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class GetUssTst extends TstZosConnection {

    private static ZosConnection connection;

    /**
     * Main method performs setup and method calls to test UssGet
     *
     * @param args for main not used
     * @throws Exception error processing request
     * @author Frank Giordano
     */
    public static void main(String[] args) throws Exception {
        String fileNamePath = "/xx/xx/xxx";  // where xxx is a file name the rest a directory path...

        connection = new ZosConnection(hostName, zosmfPort, userName, password);
        getFileTextContentWithSearchFilterNoResults(fileNamePath);
        getFileTextContentWithSearchFilter(fileNamePath);
        getFileTextContent(fileNamePath);
        getFileTextContentWithRange(fileNamePath);
    }

    /**
     * This method setups the file and its data for the rest of the test methods. As such, this method should
     * be called first in the main method.
     * <p>
     * Retrieve the contents of the fileNamePath value based on its search filter settings.
     * <p>
     * For this case, no search result is returned due to case-sensitive search.
     *
     * @param fileNamePath file name with path
     * @author Frank Giordano
     */
    private static void getFileTextContentWithSearchFilterNoResults(String fileNamePath) {
        UssCreate ussCreate = new UssCreate(connection);
        try {
            ussCreate.create(fileNamePath, new CreateParams(CreateType.FILE, "rwxr--r--"));
        } catch (Exception e) {
            final String msg = "The specified file already exists";
            if (!e.getMessage().contains(msg)) {
                throw new RuntimeException(e);
            }
        }

        UssWrite ussWrite = new UssWrite(connection);
        ussWrite.writeText(fileNamePath, "Frank\nFrank2\nApple\nhelp\n");

        UssGet ussGet = new UssGet(connection);
        GetParams params = new GetParams.Builder().insensitive(false).search("apple").build();
        Response response = ussGet.getCommon(fileNamePath, params);
        System.out.println(response.getResponsePhrase().get());
    }

    /**
     * This method performs a search against the fileNamePath value.
     * It returns data from the file from the starting point of the search value.
     *
     * @param fileNamePath file name with path
     * @author Frank Giordano
     */
    private static void getFileTextContentWithSearchFilter(String fileNamePath) {
        UssGet ussGet = new UssGet(connection);
        GetParams params = new GetParams.Builder().insensitive(false).search("Apple").build();
        Response response = ussGet.getCommon(fileNamePath, params);
        System.out.println(response.getResponsePhrase().get());
    }

    /**
     * This method returns the entire text content of the fileNamePath value.
     *
     * @param fileNamePath file name with path
     * @throws Exception processing error
     * @author Frank Giordano
     */
    private static void getFileTextContent(String fileNamePath) throws Exception {
        UssGet ussGet = new UssGet(connection);
        System.out.println(ussGet.getText(fileNamePath));
    }

    /**
     * This method returns the last two records (lines) from the file name path value.
     *
     * @param fileNamePath file name with path
     * @author Frank Giordano
     */
    private static void getFileTextContentWithRange(String fileNamePath) {
        UssGet ussGet = new UssGet(connection);
        GetParams params = new GetParams.Builder().recordsRange("-2").build();
        Response response = ussGet.getCommon(fileNamePath, params);
        System.out.println(response.getResponsePhrase().get());
    }

}
