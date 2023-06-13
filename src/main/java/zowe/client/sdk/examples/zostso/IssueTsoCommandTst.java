package zowe.client.sdk.examples.zostso;

import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.zostso.method.IssueTso;
import zowe.client.sdk.zostso.response.IssueResponse;

import java.util.Arrays;

/**
 * Class example to test tso command functionality via IssueTso class.
 *
 * @author Frank Giordano
 * @version 1.0
 */
public class IssueTsoCommandTst extends TstZosConnection {

    private static ZOSConnection connection;

    /**
     * Main method defines z/OSMF host and user connection, and tso command parameters used for the example test.
     *
     * @param args for main not used
     * @throws Exception error processing request
     * @author Frank Giordano
     */
    public static void main(String[] args) throws Exception {
        String command = "xxx";
        String accountNumber = "xxx";

        connection = new ZOSConnection(hostName, zosmfPort, userName, password);
        IssueResponse response = IssueTsoCommandTst.tsoConsoleCmdByIssue(accountNumber, command);
        String[] results = response.getCommandResponses().orElse("").split("\n");
        Arrays.stream(results).sequential().forEach(System.out::println);
    }

    /**
     * Issue issueCommand method from IssueTso class which will execute the given tso command.
     *
     * @param accountNumber user's z/OSMF permission account number
     * @param cmd           tso command to execute
     * @return issue response object
     * @throws Exception error processing request
     * @author Frank Giordano
     */
    public static IssueResponse tsoConsoleCmdByIssue(String accountNumber, String cmd) throws Exception {
        IssueTso issueTso = new IssueTso(connection);
        return issueTso.issueCommand(accountNumber, cmd);
    }

}
