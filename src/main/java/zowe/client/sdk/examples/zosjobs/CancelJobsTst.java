package zowe.client.sdk.examples.zosjobs;

import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.rest.Response;
import zowe.client.sdk.zosjobs.input.ModifyJobParams;
import zowe.client.sdk.zosjobs.methods.JobCancel;
import zowe.client.sdk.zosjobs.response.Job;

/**
 * Class example to showcase CancelJobs functionality.
 *
 * @author Leonid Baranov
 * @author Frank Giordano
 * @version 2.0
 */
public class CancelJobsTst extends TstZosConnection {

    private static ZOSConnection connection;
    private static String jobName;
    private static String jobId;

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * CancelJobs functionality. Calls CancelJobs example methods.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Leonid Baranov
     */
    public static void main(String[] args) throws Exception {
        connection = new ZOSConnection(hostName, zosmfPort, userName, password);
        System.out.println(cancelJobsCommonWithVersion("2.0"));
        System.out.println(cancelJobsCommon());
        System.out.println(cancelJobForJob());
        System.out.println(cancelJob());
    }

    /**
     * Example on how to call CancelJobs cancelJobsCommon method.
     * cancelJobsCommon accepts a CancelJobParams object with parameters filled needed to cancel a given job and
     * the version to indicate 1.0 for async or 2.0 for sync processing of the request
     *
     * @param version version value
     * @return response http Response object
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static Response cancelJobsCommonWithVersion(String version) throws Exception {
        jobId = "xxx";
        jobName = "xxx";
        ModifyJobParams params = new ModifyJobParams.Builder(jobName, jobId).version(version).build();
        return new JobCancel(connection).cancelJobsCommon(params);
    }

    /**
     * Example on how to call CancelJobs cancelJobsCommon method.
     * cancelJobsCommon accepts a CancelJobParams object with parameters filled needed to cancel a given job.
     *
     * @return response http Response object
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static Response cancelJobsCommon() throws Exception {
        jobId = "xxx";
        jobName = "xxx";
        ModifyJobParams params = new ModifyJobParams.Builder(jobName, jobId).build();
        return new JobCancel(connection).cancelJobsCommon(params);
    }

    /**
     * Example on how to call CancelJobs cancelJobForJob method.
     * cancelJobForJob accepts a jobName and jobId values which will be used to cancel its job.
     *
     * @return response http Response object
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static Response cancelJobForJob() throws Exception {
        jobId = "xxx";
        jobName = "xxx";
        return new JobCancel(connection).cancelJobForJob(
                new Job.Builder().jobName(jobName).jobId(jobId).build(), null);
    }

    /**
     * Example on how to call CancelJobs cancelJob method.
     * cancelJob accepts a jobName and jobId values which will be used to cancel its job.
     *
     * @return response http Response object
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static Response cancelJob() throws Exception {
        jobId = "xxx";
        jobName = "xxx";
        return new JobCancel(connection).cancelJob(jobName, jobId, null);
    }

}
