package zowe.client.sdk.examples.zosfiles;

import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.zosfiles.dsn.methods.DsnGet;
import zowe.client.sdk.zosfiles.dsn.response.Dataset;

/**
 * Class example to showcase retrieval of dataset information functionality via DsnGet class.
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class DataSetInfoTst extends TstZosConnection {

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * DsnGet functionality.
     *
     * @param args for main not used
     * @throws Exception error processing request
     * @author Frank Giordano
     */
    public static void main(String[] args) throws Exception {
        String dataSetName = "xxx";
        ZOSConnection connection = new ZOSConnection(hostName, zosmfPort, userName, password);
        System.out.println(DataSetInfoTst.getDataSetInfo(connection, dataSetName));
    }

    /**
     * Retrieve dataset information.
     *
     * @param connection  ZOSConnection object
     * @param dataSetName name of a dataset
     * @return
     * @throws Exception error processing request
     * @author Frank Giordano
     */
    public static Dataset getDataSetInfo(ZOSConnection connection, String dataSetName) throws Exception {
        DsnGet dsnGet = new DsnGet(connection);
        return dsnGet.getDsnInfo(dataSetName);
    }

}
