package zowe.client.sdk.examples.zosfiles.dsn;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.examples.utility.Util;
import zowe.client.sdk.rest.Response;
import zowe.client.sdk.rest.exception.ZosmfRequestException;
import zowe.client.sdk.zosfiles.dsn.input.CreateParams;
import zowe.client.sdk.zosfiles.dsn.methods.DsnCreate;

/**
 * Class example to showcase CreateDataset functionality via DsnCreate class.
 *
 * @author Leonid Baranov
 * @author Frank Giordano
 * @version 2.0
 */
public class DsnCreateExp extends TstZosConnection {

    private static ZosConnection connection;

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * DsnCreate functionality.
     *
     * @param args for main not used
     * @author Leonid Baranov
     */
    public static void main(String[] args) {
        String dataSetName = "xxx";
        connection = new ZosConnection(hostName, zosmfPort, userName, password);
        createPartitionDataSet(dataSetName);
        dataSetName = "xxx";
        createSequentialDataSet(dataSetName);
    }

    /**
     * Create a new sequential dataset.
     *
     * @param dataSetName name of a dataset to create (e.g. 'DATASET.LIB')
     * @author Frank Giordano
     */
    public static void createSequentialDataSet(String dataSetName) {
        Response response;
        try {
            DsnCreate dsnCreate = new DsnCreate(connection);
            response = dsnCreate.create(dataSetName, sequential());
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }

        System.out.println("status code = " +
                (response.getStatusCode().isEmpty() ? "no status code available" : response.getStatusCode().getAsInt()));
    }

    /**
     * Create a new partition dataset.
     *
     * @param dataSetName name of a dataset to create (e.g. 'DATASET.LIB')
     * @author Frank Giordano
     */
    public static void createPartitionDataSet(String dataSetName) {
        Response response;
        try {
            DsnCreate dsnCreate = new DsnCreate(connection);
            response = dsnCreate.create(dataSetName, partitioned());
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }

        System.out.println("status code = " +
                (response.getStatusCode().isEmpty() ? "no status code available" : response.getStatusCode().getAsInt()));
    }

    /**
     * Example of a prebuilt CreateParams for creating a binary dataset.
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
