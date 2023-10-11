package zowe.client.sdk.examples.zosconsole;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.zosconsole.ConsoleConstants;
import zowe.client.sdk.zosconsole.input.IssueConsoleParams;
import zowe.client.sdk.zosconsole.method.IssueConsole;
import zowe.client.sdk.zosconsole.response.ConsoleResponse;

/**
 * Class example to showcase mvs console command functionality via IssueConsole class.
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class IssueCommandTst extends TstZosConnection {

    /**
     * Main method defines z/OSMF host and user connection, and mvs command used for the example tests.
     *
     * @param args for main not used
     * @author Frank Giordano
     */
    public static void main(String[] args) {
        String command = "D IPLINFO";
        ZosConnection connection = new ZosConnection(hostName, zosmfPort, userName, password);
        IssueCommandTst.issueCommand(connection, command);
        IssueCommandTst.issueCommandCommon(connection, command);
    }

    /**
     * Issue IssueConsole issueCommand method which will execute the given simple mvs console command
     * without params object.
     *
     * @param connection connection information, see ZosConnection object
     * @param cmd        mvs command to execute
     * @author Frank Giordano
     */
    public static void issueCommand(ZosConnection connection, String cmd) {
        IssueConsole issueConsole = new IssueConsole(connection);
        ConsoleResponse response;
        try {
            response = issueConsole.issueCommand(cmd);
            System.out.println(response.getCommandResponse().orElse(""));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Issue IssueConsole issueCommandCommon method which will execute the given mvs console command.
     *
     * @param connection connection information, see ZosConnection object
     * @param cmd        mvs command to execute
     * @author Frank Giordano
     */
    public static void issueCommandCommon(ZosConnection connection, String cmd) {
        IssueConsole issueConsole = new IssueConsole(connection);
        ConsoleResponse response;
        try {
            response = issueConsole.issueCommandCommon(ConsoleConstants.RES_DEF_CN, new IssueConsoleParams(cmd));
            System.out.println(response.getCommandResponse().orElse(""));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
