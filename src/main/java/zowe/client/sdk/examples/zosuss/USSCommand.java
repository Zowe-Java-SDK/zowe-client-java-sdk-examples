package zowe.client.sdk.examples.zosuss;

import zowe.client.sdk.core.SSHConnection;
import zowe.client.sdk.zosuss.Shell;

/**
 * Class example to showcase USS command(s) execution.
 */
public class USSCommand {

    /**
     * Main method defines SSH connection and showcases executing USS commands.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     */
    public static void main(String[] args) throws Exception {
        SSHConnection conn = new SSHConnection("XXX", 22, "XXX", "XXX");
        Shell shell = new Shell(conn);
        // 10000 is the timeout value in milliseconds
        System.out.println(shell.executeSshCwd("mkdir test;cd test;touch frank;ls", 10000));
        // value "frank" should display
    }

}
