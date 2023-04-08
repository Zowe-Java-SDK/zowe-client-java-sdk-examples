package zowe.client.sdk.examples.zosfiles;

import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.rest.Response;
import zowe.client.sdk.zosfiles.ZosDsnCopy;
import zowe.client.sdk.zosfiles.input.CopyParams;

/**
 * Class example to showcase CopyDataset functionality.
 *
 * @author Leonid Baranov
 * @author Frank Giordano
 * @version 2.0
 */
public class CopyDatasetTst extends TstZosConnection {

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * CopyDataset functionality. Calls CopyDataset example methods.
     *
     * @param args for main not used
     * @throws Exception error processing examples
     * @author Leonid Baranov
     */
    public static void main(String[] args) throws Exception {
        String fromDataSetName = "xxx";
        String toDataSetName = "xxx";
        ZOSConnection connection = new ZOSConnection(hostName, zosmfPort, userName, password);
        copyDataset(connection, fromDataSetName, toDataSetName);
        copyDatasetByCopyParams(connection, fromDataSetName, toDataSetName);
        fromDataSetName = "xxx";  // specify a partition dataset only no member
        toDataSetName = "xxx"; // specify a partition dataset only no member
        copyFullPartitionDatasetByCopyParams(connection, fromDataSetName, toDataSetName);
    }

    /**
     * Example on how to call ZosDsnCopy copy method.
     * Copy method accepts a from and too strings for copying.
     * <p>
     * This copy method allows the following copy operations:
     * <p>
     * - sequential dataset to sequential dataset
     * - sequential dataset to partition dataset member
     * - partition dataset member to partition dataset member
     * - partition dataset member to partition dataset non-existing member
     * - partition dataset member to sequential dataset
     * <p>
     * This example sends false value for copyAllMembers parameter in copy method to indicate we
     * are not copying all members in a partition dataset to another.
     *
     * @param connection      ZOSConnection
     * @param fromDataSetName source dataset (e.g. 'SOURCE.DATASET' or 'SOURCE.DATASET(MEMBER)')
     * @param toDataSetName   destination dataset (e.g. 'TARGET.DATASET' or 'TARGET.DATASET(MEMBER)')
     * @throws Exception error processing copy request
     * @author Frank Giordano
     */
    public static void copyDataset(ZOSConnection connection, String fromDataSetName, String toDataSetName)
            throws Exception {
        ZosDsnCopy zosDsnCopy = new ZosDsnCopy(connection);
        Response response = zosDsnCopy.copy(fromDataSetName, toDataSetName, true, false);
        System.out.println("http response code " + response.getStatusCode());
    }

    /**
     * Example on how to call ZosDsnCopy copy method.
     * Copy method accepts a CopyParams object.
     * <p>
     * This copy method allows the following copy operations:
     * <p>
     * - sequential dataset to sequential dataset
     * - sequential dataset to partition dataset member
     * - partition dataset member to partition dataset member
     * - partition dataset member to partition dataset non-existing member
     * - partition dataset member to sequential dataset
     *
     * @param connection      ZOSConnection
     * @param fromDataSetName source dataset (e.g. 'SOURCE.DATASET' or 'SOURCE.DATASET(MEMBER)')
     * @param toDataSetName   destination dataset (e.g. 'TARGET.DATASET' or 'TARGET.DATASET(MEMBER)')
     * @throws Exception error processing copy request
     * @author Frank Giordano
     */
    public static void copyDatasetByCopyParams(zowe.client.sdk.core.ZOSConnection connection, String fromDataSetName,
                                               String toDataSetName) throws Exception {
        ZosDsnCopy zosDsnCopy = new ZosDsnCopy(connection);
        // 'replace' builder variable here will be true by default if not specified in builder.
        // 'copyAllMembers' builder variable here will be false by default
        CopyParams copyParams = new CopyParams.Builder().fromDataSet(fromDataSetName).toDataSet(toDataSetName).build();
        Response response = zosDsnCopy.copy(copyParams);
        System.out.println("http response code " + response.getStatusCode());
    }

    /**
     * Example on how to call ZosDsnCopy copy method.
     * Copy method accepts a CopyParams object.
     * <p>
     * This copy method is different from the other two examples above as it
     * sets the copyAllMember variable true to indicate that the copy operation will be performed
     * on a partition dataset to another partition dataset copying all its members to the target.
     *
     * @param connection      ZOSConnection
     * @param fromDataSetName source dataset (e.g. 'SOURCE.PARTITION.DATASET')
     * @param toDataSetName   destination dataset (e.g. 'TARGET.PARTITION.DATASET')
     * @throws Exception error processing copy request
     * @author Frank Giordano
     */
    public static void copyFullPartitionDatasetByCopyParams(ZOSConnection connection, String fromDataSetName,
                                                            String toDataSetName) throws Exception {
        ZosDsnCopy zosDsnCopy = new ZosDsnCopy(connection);
        // 'replace' here will be true by default if not specified in builder.
        CopyParams copyParams = new CopyParams.Builder()
                .fromDataSet(fromDataSetName).toDataSet(toDataSetName).copyAllMembers(true).build();
        Response response = zosDsnCopy.copy(copyParams);
        System.out.println("http response code " + response.getStatusCode());
    }

}