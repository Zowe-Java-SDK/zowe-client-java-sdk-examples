package zowe.client.sdk.examples.zoslogs;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.zoslogs.input.DirectionType;
import zowe.client.sdk.zoslogs.input.HardCopyType;
import zowe.client.sdk.zoslogs.input.ZosLogParams;
import zowe.client.sdk.zoslogs.method.ZosLog;
import zowe.client.sdk.zoslogs.response.ZosLogReply;

/**
 * Class example to showcase ZosLog class functionality.
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class ZosGetLogTst extends TstZosConnection {

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * z/OS SYSLOG retrieval functionality via ZosLog class.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void main(String[] args) throws Exception {
        ZosConnection connection = new ZosConnection(hostName, zosmfPort, userName, password);
        ZosLog zosLog = new ZosLog(connection);
        ZosLogParams zosLogParams = new ZosLogParams.Builder()
                .startTime("2022-11-27T05:06:20Z")
                .hardCopy(HardCopyType.SYSLOG)
                .timeRange("24h")
                .direction(DirectionType.BACKWARD)
                .processResponses(true)
                .build();
        ZosLogReply zosLogReply = zosLog.issueCommand(zosLogParams);
        zosLogReply.getItemLst().forEach(i -> System.out.println(i.getTime().get() + " " + i.getMessage().get()));

        // get the last one minute of syslog from the date/time of now backwards...
        zosLogParams = new ZosLogParams.Builder()
                .hardCopy(HardCopyType.SYSLOG)
                .timeRange("1m")
                .direction(DirectionType.BACKWARD)
                .processResponses(true)
                .build();
        zosLogReply = zosLog.issueCommand(zosLogParams);
        zosLogReply.getItemLst().forEach(i -> System.out.println(i.getTime().get() + " " + i.getMessage().get()));
    }

}
