package zowe.client.sdk.examples.zosfiles.dsn;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.examples.utility.Util;
import zowe.client.sdk.rest.exception.ZosmfRequestException;
import zowe.client.sdk.zosfiles.dsn.methods.DsnGet;
import zowe.client.sdk.zosfiles.dsn.response.Dataset;

/**
 * Class example to showcase retrieval of dataset information functionality via DsnGet class.
 *
 * @author Frank Giordano
 * @version 3.0
 */
public class DsnGetInfoExp extends TstZosConnection {

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * DsnGet functionality.
     *
     * @param args for main not used
     * @author Frank Giordano
     */
    public static void main(String[] args) {
        String dataSetName = "xxx";
        ZosConnection connection = new ZosConnection(hostName, zosmfPort, userName, password);
        System.out.println(DsnGetInfoExp.getDataSetInfo(connection, dataSetName));
    }

    /**
     * Retrieve dataset information.
     *
     * @param connection  ZosConnection object
     * @param dataSetName name of a dataset
     * @return Dataset object
     * @author Frank Giordano
     */
    public static Dataset getDataSetInfo(ZosConnection connection, String dataSetName) {
        try {
            DsnGet dsnGet = new DsnGet(connection);
            return dsnGet.getDsnInfo(dataSetName);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
    }

}
