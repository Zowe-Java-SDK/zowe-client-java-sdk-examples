package zowe.client.sdk.examples.zosjobs;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.examples.utility.Util;
import zowe.client.sdk.rest.exception.ZosmfRequestException;
import zowe.client.sdk.zosjobs.methods.JobMonitor;
import zowe.client.sdk.zosjobs.methods.JobSubmit;
import zowe.client.sdk.zosjobs.response.Job;
import zowe.client.sdk.zosjobs.types.JobStatus;

/**
 * Class example to showcase JobSubmit class functionality.
 *
 * @author Frank Giordano
 * @version 3.0
 */
public class JobSubmitExp extends TstZosConnection {

    /**
     * Main method defines z/OSMF host and user connection needed to showcase
     * JobSubmit functionality.
     *
     * @param args for main not used
     * @author Frank Giordano
     */
    public static void main(String[] args) {
        ZosConnection connection = new ZosConnection(hostName, zosmfPort, userName, password);
        System.out.println(JobSubmitExp.submitJob(connection, "xxx.xxx.xxx.xxx(xxx)"));

        String jclString = "//TESTJOBX JOB (),MSGCLASS=H\n// EXEC PGM=IEFBR14";
        Job submitJobsTest = JobSubmitExp.submitJclJob(connection, jclString);
        // Wait for the job to complete
        JobMonitor jobMonitor = new JobMonitor(connection);
        try {
            submitJobsTest = jobMonitor.waitByStatus(submitJobsTest, JobStatus.Type.OUTPUT);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
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
     * @author Frank Giordano
     */
    public static Job submitJclJob(ZosConnection connection, String jclString) {
        JobSubmit jobSubmit = new JobSubmit(connection);
        try {
            return jobSubmit.submitByJcl(jclString, null, null);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
    }

    /**
     * Example on how to call JobSubmit submit method.
     * The submit method is given a Dataset member value to use to submit it as a job.
     *
     * @param connection ZosConnection object
     * @param dsMember   dataset member value
     * @return job document
     * @author Frank Giordano
     */
    public static Job submitJob(ZosConnection connection, String dsMember) {
        JobSubmit jobSubmit = new JobSubmit(connection);
        try {
            return jobSubmit.submit(dsMember);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
    }

}
