package zowe.client.sdk.examples.zosjobs;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.examples.utility.Util;
import zowe.client.sdk.rest.Response;
import zowe.client.sdk.rest.exception.ZosmfRequestException;
import zowe.client.sdk.zosjobs.input.ModifyJobParams;
import zowe.client.sdk.zosjobs.methods.JobDelete;
import zowe.client.sdk.zosjobs.response.Job;

/**
 * Class example to showcase JobDelete class functionality.
 *
 * @author Leonid Baranov
 * @author Frank Giordano
 * @version 3.0
 */
public class JobDeleteExp extends TstZosConnection {

    private static ZosConnection connection;
    private static String jobName;
    private static String jobId;

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * JobDelete functionality.
     *
     * @param args for main not used
     * @author Leonid Baranov
     */
    public static void main(String[] args) {
        connection = new ZosConnection(hostName, zosmfPort, userName, password);
        System.out.println(deleteCommonWithVersion("2.0"));
        System.out.println(deleteCommon());
        System.out.println(deleteByJob());
        System.out.println(deleteJob());
    }

    /**
     * Example on how to call JobDelete deleteCommon method.
     * The deleteCommon method accepts a DeleteJobParams object with parameters filled needed
     * to delete a given job and the version to indicate 1.0 for async or 2.0 for sync
     * processing of the request.
     *
     * @param version value to indicate sync or async request processing
     * @return response object
     * @author Frank Giordano
     */
    public static Response deleteCommonWithVersion(String version) {
        jobId = "xxx";
        jobName = "xxx";
        ModifyJobParams params = new ModifyJobParams.Builder(jobName, jobId).version(version).build();
        try {
            return new JobDelete(connection).deleteCommon(params);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
    }

    /**
     * Example on how to call JobDelete deleteCommon method.
     * The deleteCommon method accepts a DeleteJobParams object with parameters
     * filled needed to delete a given job.
     *
     * @return response object
     * @author Frank Giordano
     */
    public static Response deleteCommon() {
        jobId = "xxx";
        jobName = "xxx";
        ModifyJobParams params = new ModifyJobParams.Builder(jobName, jobId).build();
        try {
            return new JobDelete(connection).deleteCommon(params);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
    }

    /**
     * Example on how to call JobDelete deleteByJob method.
     * The deleteByJob method accepts a jobName and jobId values which will be used to delete its job.
     *
     * @return response object
     * @author Frank Giordano
     */
    public static Response deleteByJob() {
        jobId = "xxx";
        jobName = "xxx";
        try {
            return new JobDelete(connection).deleteByJob(
                    new Job.Builder().jobName(jobName).jobId(jobId).build(), null);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
    }

    /**
     * Example on how to call JobDelete delete method.
     * The delete method accepts a jobName and jobId values which will be used to delete its job.
     *
     * @return response object
     * @author Frank Giordano
     */
    public static Response deleteJob() {
        jobId = "xxx";
        jobName = "xxx";
        try {
            return new JobDelete(connection).delete(jobName, jobId, null);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
    }

}
