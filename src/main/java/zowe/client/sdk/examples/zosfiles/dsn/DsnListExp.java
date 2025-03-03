package zowe.client.sdk.examples.zosfiles.dsn;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.examples.utility.Util;
import zowe.client.sdk.rest.exception.ZosmfRequestException;
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
 * @version 3.0
 */
public class DsnListExp extends TstZosConnection {

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * DsnList functionality.
     *
     * @param args for main not used
     * @author Leonid Baranov
     */
    public static void main(String[] args) {
        String dataSetMask = "xxx";
        String dataSetName = "xxx";
        ZosConnection connection = new ZosConnection(hostName, zosmfPort, userName, password);
        DsnListExp.listDsn(connection, dataSetMask);
        DsnListExp.listDsnVol(connection, dataSetMask);
        DsnListExp.listMembersWithAllAttributes(connection, dataSetName);
        DsnListExp.listMembers(connection, dataSetName);
    }

    /**
     * List out all members and its attribute values of the given data set
     *
     * @param connection  ZosConnection object
     * @param dataSetName name of a dataset
     * @author Leonid Baranov
     */
    public static void listMembersWithAllAttributes(ZosConnection connection, String dataSetName) {
        List<Member> datasets;
        try {
            ListParams params = new ListParams.Builder().attribute(AttributeType.BASE).build();
            DsnList dsnList = new DsnList(connection);
            datasets = dsnList.getMembers(dataSetName, params);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
        datasets.forEach(m -> System.out.println(m.toString()));
    }

    /**
     * List out all members of the given data set
     *
     * @param connection  ZosConnection object
     * @param dataSetName name of a dataset
     * @author Leonid Baranov
     */
    public static void listMembers(ZosConnection connection, String dataSetName) {
        List<Member> datasets;
        try {
            ListParams params = new ListParams.Builder().attribute(AttributeType.MEMBER).build();
            DsnList dsnList = new DsnList(connection);
            datasets = dsnList.getMembers(dataSetName, params);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
        datasets.forEach(m -> System.out.println(m.toString()));
    }

    /**
     * List out all data sets of the given data set. Each dataset returned will contain all of its properties.
     *
     * @param connection  ZosConnection object
     * @param dataSetName name of a dataset
     * @author Leonid Baranov
     */
    public static void listDsn(ZosConnection connection, String dataSetName) {
        List<Dataset> datasets;
        try {
            ListParams params = new ListParams.Builder().attribute(AttributeType.BASE).build();
            DsnList dsnList = new DsnList(connection);
            datasets = dsnList.getDatasets(dataSetName, params);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
        datasets.forEach(System.out::println);
    }

    /**
     * List out all data sets of the given data set. Each dataset returned will contain its volume property.
     *
     * @param connection  ZosConnection object
     * @param dataSetName name of a dataset
     * @author Frank Giordano
     */
    public static void listDsnVol(ZosConnection connection, String dataSetName) {
        List<Dataset> datasets;
        try {
            ListParams params = new ListParams.Builder().attribute(AttributeType.VOL).build();
            DsnList dsnList = new DsnList(connection);
            datasets = dsnList.getDatasets(dataSetName, params);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
        datasets.forEach(System.out::println);
    }

}
