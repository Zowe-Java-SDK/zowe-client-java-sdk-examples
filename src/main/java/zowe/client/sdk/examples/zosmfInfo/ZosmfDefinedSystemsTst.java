package zowe.client.sdk.examples.zosmfInfo;

import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.zosmfinfo.methods.ZosmfGetSystems;
import zowe.client.sdk.zosmfinfo.response.ZosmfListDefinedSystemsResponse;

import java.util.Arrays;

/**
 * Class example to showcase ListDefinedSystems functionality.
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class ZosmfDefinedSystemsTst extends TstZosConnection {

    private static ZosmfGetSystems zosmfGetSystems;

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * ListDefinedSystems functionality.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void main(String[] args) throws Exception {
        ZOSConnection connection = new ZOSConnection(hostName, zosmfPort, userName, password);

        zosmfGetSystems = new ZosmfGetSystems(connection);
        ZosmfListDefinedSystemsResponse zosmfInfoResponse = zosmfGetSystems.listDefinedSystems();
        System.out.println(zosmfInfoResponse.toString());
        Arrays.stream(zosmfInfoResponse.getDefinedSystems().get()).forEach(i -> System.out.println(i.toString()));
    }

}