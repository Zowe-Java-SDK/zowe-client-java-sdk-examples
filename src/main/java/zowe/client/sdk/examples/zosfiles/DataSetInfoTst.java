package zowe.client.sdk.examples.zosfiles;

import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.zosfiles.ZosDsn;
import zowe.client.sdk.zosfiles.response.Dataset;

/**
 * Class example to showcase ZosDsn getDataSetInfo functionality.
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class DataSetInfoTst extends TstZosConnection {

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * ZosDsn getDataSetInfo functionality.
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

    private static Dataset getDataSetInfo(zowe.client.sdk.core.ZOSConnection connection, String dataSetName) throws Exception {
        ZosDsn zosDsn = new ZosDsn(connection);
        return zosDsn.getDataSetInfo(dataSetName);
    }

}