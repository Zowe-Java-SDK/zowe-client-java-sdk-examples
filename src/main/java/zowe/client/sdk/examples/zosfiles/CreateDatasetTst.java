package zowe.client.sdk.examples.zosfiles;

import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.rest.Response;
import zowe.client.sdk.zosfiles.ZosDsn;
import zowe.client.sdk.zosfiles.input.CreateParams;

/**
 * Class example to showcase CreateDataset functionality.
 *
 * @author Leonid Baranov
 * @version 2.0
 */
public class CreateDatasetTst extends TstZosConnection {

    private static ZOSConnection connection;

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * CreateDataset functionality. Calls CreateDataset example methods.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Leonid Baranov
     */
    public static void main(String[] args) throws Exception {
        String dataSetName = "xxx";
        connection = new ZOSConnection(hostName, zosmfPort, userName, password);
        createPartitionDataSet(dataSetName);
        dataSetName = "xxx";
        createSequentialDataSet(dataSetName);

    }

    /**
     * Create a new sequential data set
     *
     * @param dataSetName name of a dataset to create (e.g. 'DATASET.LIB')
     * @throws Exception error processing request
     * @author Frank Giordano
     */
    public static void createSequentialDataSet(String dataSetName) throws Exception {
        ZosDsn zosDsn = new ZosDsn(connection);
        Response response = zosDsn.createDsn(dataSetName, sequential());
        System.out.println("http response code " + response.getStatusCode());
    }

    /**
     * Create a new partition data set
     *
     * @param dataSetName name of a dataset to create (e.g. 'DATASET.LIB')
     * @throws Exception error processing request
     * @author Frank Giordano
     */
    public static void createPartitionDataSet(String dataSetName) throws Exception {
        ZosDsn zosDsn = new ZosDsn(connection);
        Response response = zosDsn.createDsn(dataSetName, partitioned());
        System.out.println("http response code " + response.getStatusCode());
    }

    /**
     * Example of a prebuilt CreateParams for creating a binary dataset
     *
     * @return prebuilt CreateParams
     */
    public static CreateParams binary() {
        return new CreateParams.Builder()
                .dsorg("PO")
                .alcunit("CYL")
                .primary(10)
                .secondary(10)
                .dirblk(25)
                .recfm("U")
                .blksize(27998)
                .lrecl(27998)
                .build();
    }

    /**
     * Example of a prebuilt CreateParams for creating a c dataset
     *
     * @return prebuilt CreateParams
     */
    public static CreateParams c() {
        return new CreateParams.Builder()
                .dsorg("PO")
                .alcunit("CYL")
                .primary(1)
                .secondary(1)
                .dirblk(25)
                .recfm("VB")
                .blksize(32760)
                .lrecl(260)
                .build();
    }

    /**
     * Example of a prebuilt CreateParams for creating classic dataset
     *
     * @return prebuilt CreateParams
     */
    public static CreateParams classic() {
        return new CreateParams.Builder()
                .dsorg("PO")
                .alcunit("CYL")
                .primary(1)
                .secondary(1)
                .dirblk(25)
                .recfm("FB")
                .blksize(6160)
                .lrecl(80)
                .build();
    }

    /**
     * Example of a prebuilt CreateParams for creating partitioned dataset
     *
     * @return prebuilt CreateParams
     */
    public static CreateParams partitioned() {
        return new CreateParams.Builder()
                .dsorg("PO")
                .alcunit("CYL")
                .primary(1)
                .secondary(1)
                .dirblk(5)
                .recfm("FB")
                .blksize(6160)
                .lrecl(80)
                .build();
    }

    /**
     * Example of a prebuilt CreateParams for creating sequential dataset
     *
     * @return prebuilt CreateParams
     */
    public static CreateParams sequential() {
        return new CreateParams.Builder()
                .dsorg("PS")
                .alcunit("CYL")
                .primary(1)
                .secondary(1)
                .recfm("FB")
                .blksize(6160)
                .lrecl(80)
                .build();
    }

}
