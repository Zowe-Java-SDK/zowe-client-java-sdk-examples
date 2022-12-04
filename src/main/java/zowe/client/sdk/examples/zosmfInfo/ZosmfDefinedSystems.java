package zowe.client.sdk.examples.zosmfInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.ZosConnection;
import zowe.client.sdk.zosmfinfo.ListDefinedSystems;
import zowe.client.sdk.zosmfinfo.response.ZosmfListDefinedSystemsResponse;

import java.util.Arrays;

/**
 * Class example to showcase ListDefinedSystems functionality.
 *
 * @author Frank Giordano
 * @version 1.0
 */
public class ZosmfDefinedSystems extends ZosConnection {

    private static final Logger LOG = LoggerFactory.getLogger(CheckStatus.class);

    private static ListDefinedSystems listDefinedSystems;

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

        listDefinedSystems = new ListDefinedSystems(connection);
        ZosmfListDefinedSystemsResponse zosmfInfoResponse = listDefinedSystems.listDefinedSystems();
        LOG.info(zosmfInfoResponse.toString());
        Arrays.stream(zosmfInfoResponse.getDefinedSystems().get()).forEach(i -> LOG.info(i.toString()));
    }

}