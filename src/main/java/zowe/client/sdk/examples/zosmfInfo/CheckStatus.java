package zowe.client.sdk.examples.zosmfInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.ZosConnection;
import zowe.client.sdk.zosmfinfo.response.ZosmfInfoResponse;

import java.util.Arrays;

public class CheckStatus extends ZosConnection {

    private static final Logger LOG = LoggerFactory.getLogger(CheckStatus.class);

    private static zowe.client.sdk.zosmfinfo.CheckStatus checkStatus;

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

        checkStatus = new zowe.client.sdk.zosmfinfo.CheckStatus(connection);
        ZosmfInfoResponse zosmfInfoResponse = checkStatus.getZosmfInfo();
        LOG.info(zosmfInfoResponse.toString());
        Arrays.stream(zosmfInfoResponse.getZosmfPluginsInfo().get()).forEach(i -> LOG.info(i.toString()));
    }

}