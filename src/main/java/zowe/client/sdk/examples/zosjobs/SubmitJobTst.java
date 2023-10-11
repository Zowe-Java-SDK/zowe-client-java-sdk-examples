package zowe.client.sdk.examples.zosjobs;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.zosjobs.methods.JobMonitor;
import zowe.client.sdk.zosjobs.methods.JobSubmit;
import zowe.client.sdk.zosjobs.response.Job;
import zowe.client.sdk.zosjobs.types.JobStatus;

/**
 * Class example to showcase JobSubmit class functionality.
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class SubmitJobTst extends TstZosConnection {

    /**
     * Main method defines z/OSMF host and user connection needed to showcase
     * JobSubmit functionality.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void main(String[] args) throws Exception {
        ZosConnection connection = new ZosConnection(hostName, zosmfPort, userName, password);
        System.out.println(SubmitJobTst.submitJob(connection, "xxx.xxx.xxx.xxx(xxx)"));

        String jclString = "//TESTJOBX JOB (),MSGCLASS=H\n// EXEC PGM=IEFBR14";
        Job submitJobsTest = SubmitJobTst.submitJclJob(connection, jclString);
        // Wait for the job to complete
        JobMonitor jobMonitor = new JobMonitor(connection);
        submitJobsTest = jobMonitor.waitByStatus(submitJobsTest, JobStatus.Type.OUTPUT);
        System.out.println(submitJobsTest);
        // Get the return code
        String retCode = submitJobsTest.getRetCode().orElse("n/a");
        System.out.println("Expected Return Code = CC 0000 [" + retCode + "]");
    }

    /**
     * Example on how to call JobSubmit submitByJcl method.
     * The submitByJcl method is given a jcl string to use to submit it as a job.
     *
     * @param connection ZosConnection object
     * @param jclString  jcl formatted string
     * @return job document
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static Job submitJclJob(ZosConnection connection, String jclString) throws Exception {
        JobSubmit jobSubmit = new JobSubmit(connection);
        return jobSubmit.submitByJcl(jclString, null, null);
    }

    /**
     * Example on how to call JobSubmit submit method.
     * The submit method is given a Dataset member value to use to submit it as a job.
     *
     * @param connection ZosConnection object
     * @param dsMember   dataset member value
     * @return job document
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static Job submitJob(ZosConnection connection, String dsMember) throws Exception {
        JobSubmit jobSubmit = new JobSubmit(connection);
        return jobSubmit.submit(dsMember);
    }

}
