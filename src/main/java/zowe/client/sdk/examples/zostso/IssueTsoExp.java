package zowe.client.sdk.examples.zostso;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.examples.utility.Util;
import zowe.client.sdk.rest.exception.ZosmfRequestException;
import zowe.client.sdk.zostso.method.IssueTso;
import zowe.client.sdk.zostso.response.IssueResponse;

import java.util.Arrays;

/**
 * Class example to test tso command functionality via IssueTso class.
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class IssueTsoExp extends TstZosConnection {

    private static ZosConnection connection;

    /**
     * Main method defines z/OSMF host and user connection, and tso command parameters used for the example test.
     *
     * @param args for main not used
     * @author Frank Giordano
     */
    public static void main(String[] args) {
        String command = "xxx";
        String accountNumber = "xxx";

        connection = new ZosConnection(hostName, zosmfPort, userName, password);
        IssueResponse response = IssueTsoExp.issueCommand(accountNumber, command);
        String[] results = response.getCommandResponses().orElse("").split("\n");
        Arrays.stream(results).sequential().forEach(System.out::println);
    }

    /**
     * Issue issueCommand method from IssueTso class which will execute the given tso command.
     *
     * @param accountNumber user's z/OSMF permission account number
     * @param cmd           tso command to execute
     * @return issue response object
     * @author Frank Giordano
     */
    public static IssueResponse issueCommand(String accountNumber, String cmd) {
        IssueTso issueTso = new IssueTso(connection);
        try {
            return issueTso.issueCommand(accountNumber, cmd);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
    }

}
