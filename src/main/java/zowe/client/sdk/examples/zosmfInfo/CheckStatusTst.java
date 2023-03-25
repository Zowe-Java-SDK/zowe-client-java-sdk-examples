package zowe.client.sdk.examples.zosmfInfo;

import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.zosmfinfo.CheckStatus;
import zowe.client.sdk.zosmfinfo.response.ZosmfInfoResponse;

import java.util.Arrays;

/**
 * Class example to showcase CheckStatus functionality.
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class CheckStatusTst extends TstZosConnection {

    private static CheckStatus checkStatus;

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * CheckStatus functionality. Calls
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void main(String[] args) throws Exception {
        ZOSConnection connection = new ZOSConnection(hostName, zosmfPort, userName, password);

        checkStatus = new CheckStatus(connection);
        ZosmfInfoResponse zosmfInfoResponse = checkStatus.getZosmfInfo();
        System.out.println(zosmfInfoResponse.toString());
        Arrays.stream(zosmfInfoResponse.getZosmfPluginsInfo().get()).forEach(i -> System.out.println(i.toString()));
    }

}