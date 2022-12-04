package zowe.client.sdk.examples.zoslogs;

import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.ZosConnection;
import zowe.client.sdk.zoslogs.GetZosLog;
import zowe.client.sdk.zoslogs.input.DirectionType;
import zowe.client.sdk.zoslogs.input.HardCopyType;
import zowe.client.sdk.zoslogs.input.ZosLogParams;
import zowe.client.sdk.zoslogs.response.ZosLogReply;

public class ZosGetLog extends ZosConnection {

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * z/OS SYSLOG retrieval functionality.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     */
    public static void main(String[] args) throws Exception {
        ZOSConnection connection = new ZOSConnection(hostName, zosmfPort, userName, password);
        GetZosLog getZosLog = new GetZosLog(connection);
        ZosLogParams zosLogParams = new ZosLogParams.Builder()
                .startTime("2022-11-27T05:06:20Z")
                .hardCopy(HardCopyType.SYSLOG)
                .timeRange("24h")
                .direction(DirectionType.BACKWARD)
                .processResponses(true)
                .build();
        ZosLogReply zosLogReply = getZosLog.getZosLog(zosLogParams);
        zosLogReply.getItemLst().forEach(i -> System.out.println(i.getMessage().get()));

        // following sends in an empty zosLogParams, hence API uses all default values
        zosLogParams = new ZosLogParams.Builder().build();
        zosLogReply = getZosLog.getZosLog(zosLogParams);
        zosLogReply.getItemLst().forEach(i -> System.out.println(i.getMessage().get()));
    }

}
