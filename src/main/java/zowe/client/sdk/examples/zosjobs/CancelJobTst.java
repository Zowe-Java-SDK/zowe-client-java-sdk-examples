package zowe.client.sdk.examples.zosjobs;

import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.rest.Response;
import zowe.client.sdk.zosjobs.input.ModifyJobParams;
import zowe.client.sdk.zosjobs.methods.JobCancel;
import zowe.client.sdk.zosjobs.response.Job;

/**
 * Class example to showcase JobCancel class functionality.
 *
 * @author Leonid Baranov
 * @author Frank Giordano
 * @version 2.0
 */
public class CancelJobTst extends TstZosConnection {

    private static ZOSConnection connection;
    private static String jobName;
    private static String jobId;

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * JobCancel functionality.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Leonid Baranov
     */
    public static void main(String[] args) throws Exception {
        connection = new ZOSConnection(hostName, zosmfPort, userName, password);
        System.out.println(cancelCommonWithVersion("2.0"));
        System.out.println(cancelCommon());
        System.out.println(cancelByJob());
        System.out.println(cancel());
    }

    /**
     * Example on how to call JobCancel cancelCommon method.
     * The cancelCommon method accepts a CancelJobParams object with parameters filled needed
     * to cancel a given job and the version to indicate 1.0 for async or 2.0 for sync processing
     * of the request.
     *
     * @param version version value
     * @return response http Response object
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static Response cancelCommonWithVersion(String version) throws Exception {
        jobId = "xxx";
        jobName = "xxx";
        ModifyJobParams params = new ModifyJobParams.Builder(jobName, jobId).version(version).build();
        return new JobCancel(connection).cancelCommon(params);
    }

    /**
     * Example on how to call JobCancel cancelCommon method.
     * The cancelCommon method accepts a CancelJobParams object with parameters filled needed
     * to cancel a given job.
     *
     * @return response http Response object
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static Response cancelCommon() throws Exception {
        jobId = "xxx";
        jobName = "xxx";
        ModifyJobParams params = new ModifyJobParams.Builder(jobName, jobId).build();
        return new JobCancel(connection).cancelCommon(params);
    }

    /**
     * Example on how to call JobCancel cancelByJob method.
     * The cancelByJob method accepts a jobName and jobId values which will be used to cancel its job.
     *
     * @return response http Response object
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static Response cancelByJob() throws Exception {
        jobId = "xxx";
        jobName = "xxx";
        return new JobCancel(connection).cancelByJob(
                new Job.Builder().jobName(jobName).jobId(jobId).build(), null);
    }

    /**
     * Example on how to call JobCancel cancel method.
     * The cancel method accepts a jobName and jobId values which will be used to cancel its job.
     *
     * @return response http Response object
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static Response cancel() throws Exception {
        jobId = "xxx";
        jobName = "xxx";
        return new JobCancel(connection).cancel(jobName, jobId, null);
    }

}
