package zowe.client.sdk.examples.zosmfInfo;

import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.zosmfinfo.methods.ZosmfSystems;
import zowe.client.sdk.zosmfinfo.response.ZosmfListDefinedSystemsResponse;

import java.util.Arrays;

/**
 * Class example to showcase ZosmfSystems class functionality.
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class ZosmfDefinedSystemsTst extends TstZosConnection {

    private static ZosmfSystems zosmfSystems;

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * ZosmfSystems class functionality. This method perform API call to retrieve the entire list of
     * defined z/OSMF systems running on the z/OS backend.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void main(String[] args) throws Exception {
        ZOSConnection connection = new ZOSConnection(hostName, zosmfPort, userName, password);

        zosmfSystems = new ZosmfSystems(connection);
        ZosmfListDefinedSystemsResponse zosmfInfoResponse = zosmfSystems.get();
        System.out.println(zosmfInfoResponse.toString());
        Arrays.stream(zosmfInfoResponse.getDefinedSystems().get()).forEach(i -> System.out.println(i.toString()));
    }

}