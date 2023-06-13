package zowe.client.sdk.examples.zosconsole;

import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.zosconsole.input.IssueParams;
import zowe.client.sdk.zosconsole.input.ZosmfIssueParams;
import zowe.client.sdk.zosconsole.method.IssueConsole;
import zowe.client.sdk.zosconsole.response.ConsoleResponse;
import zowe.client.sdk.zosconsole.response.ZosmfIssueResponse;

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
        ZOSConnection connection = new ZOSConnection(hostName, zosmfPort, userName, password);
        IssueCommandTst.consoleCmdByIssue(connection, command);
        IssueCommandTst.consoleCmdByIssueSimple(connection, command);
        IssueCommandTst.consoleCmdByIssueDefConsoleCommon(connection, command);
    }

    /**
     * Issue IssueConsole issueCommand which will execute the given mvs console command.
     *
     * @param connection connection information, see ZOSConnection object
     * @param cmd        mvs command to execute
     * @author Frank Giordano
     */
    public static void consoleCmdByIssue(ZOSConnection connection, String cmd) {
        IssueParams params = new IssueParams();
        params.setCommand(cmd);
        ConsoleResponse response;
        IssueConsole issueConsole = new IssueConsole(connection);
        try {
            response = issueConsole.issueCommand(params);
            System.out.println(response.getCommandResponse().orElse(""));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Issue IssueConsole issueCommand method which will execute the given simple mvs console command
     * without params object.
     *
     * @param connection connection information, see ZOSConnection object
     * @param cmd        mvs command to execute
     * @author Frank Giordano
     */
    public static void consoleCmdByIssueSimple(ZOSConnection connection, String cmd) {
        ConsoleResponse response;
        IssueConsole issueConsole = new IssueConsole(connection);
        try {
            response = issueConsole.issueCommand(cmd);
            System.out.println(response.getCommandResponse().orElse(""));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Issue IssueConsole issueDefConsoleCommon method which will execute the given mvs console command.
     *
     * @param connection connection information, see ZOSConnection object
     * @param cmd        mvs command to execute
     * @author Frank Giordano
     */
    public static void consoleCmdByIssueDefConsoleCommon(ZOSConnection connection, String cmd) {
        ZosmfIssueParams params = new ZosmfIssueParams();
        params.setCmd(cmd);
        ZosmfIssueResponse response;
        IssueConsole issueConsole = new IssueConsole(connection);
        try {
            response = issueConsole.issueDefConsoleCommon(params);
            System.out.println(response.getCmdResponse().orElse(""));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
