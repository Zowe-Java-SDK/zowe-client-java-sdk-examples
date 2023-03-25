package zowe.client.sdk.examples.zosjobs;

import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.zosjobs.MonitorJobs;
import zowe.client.sdk.zosjobs.SubmitJobs;
import zowe.client.sdk.zosjobs.response.Job;
import zowe.client.sdk.zosjobs.types.JobStatus;

/**
 * Class example to showcase SubmitJobs functionality.
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class SubmitJobsTst extends TstZosConnection {

    /**
     * Main method defines z/OSMF host and user connection needed to showcase
     * SubmitJobs functionality. Calls SubmitJobs example methods.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void main(String[] args) throws Exception {
        ZOSConnection connection = new ZOSConnection(hostName, zosmfPort, userName, password);
        System.out.println(SubmitJobsTst.submitJob(connection, "xxx.xxx.xxx.xxx(xxx)"));

        String jclString = "//TESTJOBX JOB (),MSGCLASS=H\n// EXEC PGM=IEFBR14";
        Job submitJobsTest = SubmitJobsTst.submitJclJob(connection, jclString);
        // Wait for the job to complete
        MonitorJobs monitorJobs = new MonitorJobs(connection);
        submitJobsTest = monitorJobs.waitForJobStatus(submitJobsTest, JobStatus.Type.OUTPUT);
        System.out.println(submitJobsTest);
        // Get the return code
        String retCode = submitJobsTest.getRetCode().orElse("n/a");
        System.out.println("Expected Return Code = CC 0000 [" + retCode + "]");
    }

    /**
     * Example on how to call SubmitJobs submitJcl method.
     * submitJcl is given a jcl string to use to submit it as a job.
     *
     * @param connection ZOSConnection object
     * @param jclString  jcl formatted string
     * @return job document
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static Job submitJclJob(zowe.client.sdk.core.ZOSConnection connection, String jclString) throws Exception {
        SubmitJobs submitJobs = new SubmitJobs(connection);
        return submitJobs.submitJcl(jclString, null, null);
    }

    /**
     * Example on how to call SubmitJobs submitJcl method.
     * submitJcl is given a Dataset member value to use to submit it as a job.
     *
     * @param connection ZOSConnection object
     * @param dsMember   dataset member value
     * @return job document
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static Job submitJob(zowe.client.sdk.core.ZOSConnection connection, String dsMember) throws Exception {
        SubmitJobs submitJobs = new SubmitJobs(connection);
        return submitJobs.submitJob(dsMember);
    }

}
