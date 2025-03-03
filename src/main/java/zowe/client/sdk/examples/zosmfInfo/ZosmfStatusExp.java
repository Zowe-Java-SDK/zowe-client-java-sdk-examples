package zowe.client.sdk.examples.zosmfInfo;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.examples.utility.Util;
import zowe.client.sdk.rest.exception.ZosmfRequestException;
import zowe.client.sdk.zosmfinfo.methods.ZosmfStatus;
import zowe.client.sdk.zosmfinfo.response.ZosmfInfoResponse;

import java.util.Arrays;

/**
 * Class example to showcase ZosmfStatus class functionality.
 *
 * @author Frank Giordano
 * @version 3.0
 */
public class ZosmfStatusExp extends TstZosConnection {

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * ZosmfStatus class functionality. This method perform API call to retrieve the status of the
     * running z/OSMF instance on the z/OS backend.
     *
     * @param args for main not used
     * @author Frank Giordano
     */
    public static void main(String[] args) {
        ZosConnection connection = new ZosConnection(hostName, zosmfPort, userName, password);
        ZosmfStatus zosmfStatus = new ZosmfStatus(connection);
        ZosmfInfoResponse zosmfInfoResponse;
        try {
            zosmfInfoResponse = zosmfStatus.get();
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
        System.out.println(zosmfInfoResponse.toString());
        Arrays.stream(zosmfInfoResponse.getZosmfPluginsInfo().get()).forEach(i -> System.out.println(i.toString()));
    }

}
