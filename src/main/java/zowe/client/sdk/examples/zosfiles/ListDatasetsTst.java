package zowe.client.sdk.examples.zosfiles;

import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.zosfiles.dsn.input.ListParams;
import zowe.client.sdk.zosfiles.dsn.methods.DsnList;
import zowe.client.sdk.zosfiles.dsn.response.Dataset;
import zowe.client.sdk.zosfiles.dsn.response.Member;
import zowe.client.sdk.zosfiles.dsn.types.AttributeType;

import java.util.List;

/**
 * Class example to showcase ListDatasets functionality via DsnList class.
 *
 * @author Leonid Baranov
 * @author Frank Giordano
 * @version 2.0
 */
public class ListDatasetsTst extends TstZosConnection {

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * DsnList functionality.
     *
     * @param args for main not used
     * @throws Exception error processing request
     * @author Leonid Baranov
     */
    public static void main(String[] args) throws Exception {
        String dataSetMask = "xxx";
        String dataSetName = "xxx";
        ZOSConnection connection = new ZOSConnection(hostName, zosmfPort, userName, password);
        ListDatasetsTst.listDsn(connection, dataSetMask);
        ListDatasetsTst.listDsnVol(connection, dataSetMask);
        ListDatasetsTst.listMembersWithAllAttributes(connection, dataSetName);
        ListDatasetsTst.listMembers(connection, dataSetName);
    }

    /**
     * List out all members and its attribute values of the given data set
     *
     * @param connection  ZOSConnection object
     * @param dataSetName name of a dataset
     * @throws Exception error processing request
     * @author Leonid Baranov
     */
    public static void listMembersWithAllAttributes(ZOSConnection connection, String dataSetName) throws Exception {
        ListParams params = new ListParams.Builder().attribute(AttributeType.BASE).build();
        DsnList dsnList = new DsnList(connection);
        List<Member> datasets = dsnList.listDsnMembers(dataSetName, params);
        datasets.forEach(m -> System.out.println(m.toString()));
    }

    /**
     * List out all members of the given data set
     *
     * @param connection  ZOSConnection object
     * @param dataSetName name of a dataset
     * @throws Exception error processing request
     * @author Leonid Baranov
     */
    public static void listMembers(ZOSConnection connection, String dataSetName) throws Exception {
        ListParams params = new ListParams.Builder().attribute(AttributeType.MEMBER).build();
        DsnList dsnList = new DsnList(connection);
        List<Member> datasets = dsnList.listDsnMembers(dataSetName, params);
        datasets.forEach(m -> System.out.println(m.toString()));
    }

    /**
     * List out all data sets of the given data set. Each dataset returned will contain all of its properties.
     *
     * @param connection  ZOSConnection object
     * @param dataSetName name of a dataset
     * @throws Exception error processing request
     * @author Leonid Baranov
     */
    public static void listDsn(ZOSConnection connection, String dataSetName) throws Exception {
        ListParams params = new ListParams.Builder().attribute(AttributeType.BASE).build();
        DsnList dsnList = new DsnList(connection);
        List<Dataset> datasets = dsnList.listDsn(dataSetName, params);
        datasets.forEach(i -> System.out.println(i));
    }

    /**
     * List out all data sets of the given data set. Each dataset returned will contain its volume property.
     *
     * @param connection  ZOSConnection object
     * @param dataSetName name of a dataset
     * @throws Exception error processing request
     * @author Frank Giordano
     */
    public static void listDsnVol(ZOSConnection connection, String dataSetName) throws Exception {
        ListParams params = new ListParams.Builder().attribute(AttributeType.VOL).build();
        DsnList dsnList = new DsnList(connection);
        List<Dataset> datasets = dsnList.listDsn(dataSetName, params);
        datasets.forEach(i -> System.out.println(i));
    }

}
