package zowe.client.sdk.examples.zosjobs;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.examples.utility.Util;
import zowe.client.sdk.rest.exception.ZosmfRequestException;
import zowe.client.sdk.zosjobs.input.MonitorJobWaitForParams;
import zowe.client.sdk.zosjobs.methods.JobMonitor;
import zowe.client.sdk.zosjobs.methods.JobSubmit;
import zowe.client.sdk.zosjobs.response.Job;
import zowe.client.sdk.zosjobs.types.JobStatus;

/**
 * Class example to showcase JobMonitor class functionality.
 *
 * @author Frank Giordano
 * @version 3.0
 */
public class JobMonitorExp extends TstZosConnection {

    private static JobSubmit submitJob;
    private static ZosConnection connection;

    /**
     * Main method defines z/OSMF host and user connection needed to showcase
     * JobMonitor functionality.
     *
     * @param args for main not used
     * @author Frank Giordano
     */
    public static void main(String[] args) {
        connection = new ZosConnection(hostName, zosmfPort, userName, password);
        submitJob = new JobSubmit(connection);
        JobMonitorExp.monitorJobForOutputStatusByJobObject();
        JobMonitorExp.monitorJobForOutputStatusByJobNameAndId();
        JobMonitorExp.monitorJobForStatusByJobObject(JobStatus.Type.INPUT);
        JobMonitorExp.monitorJobForStatusByJobNameAndId(JobStatus.Type.ACTIVE);
        JobMonitorExp.monitorWaitForJobMessage("xxx");
        monitorIsJobRunning();
    }

    /**
     * Example on how to call JobMonitor isJobRunning method.
     * The isJobRunning method accepts a MonitorJobWaitForParams object.
     *
     * @author Frank Giordano
     */
    public static void monitorIsJobRunning() {
        JobMonitor jobMonitor = new JobMonitor(connection);
        MonitorJobWaitForParams monitorParams = new MonitorJobWaitForParams.Builder("XXX", "XXX").build();
        try {
            System.out.println(jobMonitor.isRunning(monitorParams));
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
    }

    /**
     * Example on how to call JobMonitor waitByOutputStatus method.
     * The waitByOutputStatus method accepts a job object which will monitor the job status
     * until it reaches OUTPUT status or times out if not reached.
     *
     * @author Frank Giordano
     */
    public static void monitorJobForOutputStatusByJobObject() {
        String jclString = "//TESTJOBX JOB (),MSGCLASS=H\r // EXEC PGM=IEFBR14";
        Job job;
        try {
            job = submitJob.submitByJcl(jclString, null, null);
            JobMonitor jobMonitor = new JobMonitor(connection);
            job = jobMonitor.waitByOutputStatus(job);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
        System.out.println("Job status for Job " + job.getJobName().orElse("n/a") + ":" +
                job.getJobId().orElse("n/a") + " is " + job.getStatus().orElse("n/a"));
    }

    /**
     * Example on how to call JobMonitor waitByOutputStatus method.
     * The waitByOutputStatus method accepts a jobName and jobId values which will
     * monitor the job status until it reaches OUTPUT status or times out if not reached.
     *
     * @author Frank Giordano
     */
    public static void monitorJobForOutputStatusByJobNameAndId() {
        String jclString = "//TESTJOBX JOB (),MSGCLASS=H\r // EXEC PGM=IEFBR14";
        Job job;
        try {
            job = submitJob.submitByJcl(jclString, null, null);
            JobMonitor jobMonitor = new JobMonitor(connection);
            job = jobMonitor.waitByOutputStatus(
                    job.getJobName().orElseThrow(() -> new ZosmfRequestException("job name not specified")),
                    job.getJobId().orElseThrow(() -> new ZosmfRequestException("job id not specified")));
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
        System.out.println("Job status for Job " + job.getJobName().orElse("n/a") + ":" +
                job.getJobId().orElse("n/a") + " is " + job.getStatus().orElse("n/a"));
    }

    /**
     * Example on how to call JobMonitor waitByStatus method.
     * The waitByStatus method accepts a job object and status value which will monitor the
     * job status until it reaches the given status value or times out if not reached.
     *
     * @param statusType given status type to use for monitoring
     * @author Frank Giordano
     */
    public static void monitorJobForStatusByJobObject(JobStatus.Type statusType) {
        // determine an existing job in your system that is in execute queue and make a Job for it
        Job job = new Job.Builder().jobName("xxx").jobId("xxx").build();
        JobMonitor jobMonitor = new JobMonitor(connection);
        try {
            job = jobMonitor.waitByStatus(job, statusType);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
        System.out.println("Job status for Job " + job.getJobName().orElse("n/a") + ":" +
                job.getJobId().orElse("n/a") + " is " + job.getStatus().orElse("n/a"));
    }

    /**
     * Example on how to call JobMonitor waitByStatus method.
     * The waitByStatus method accepts a jobName and jobId values and status value which will monitor the
     * job status until it reaches the given status value or times out if not reached.
     *
     * @param statusType given status type to use for monitoring
     * @author Frank Giordano
     */
    public static void monitorJobForStatusByJobNameAndId(JobStatus.Type statusType) {
        // determine an existing job in your system that is in execute queue and make a Job for it
        Job job = new Job.Builder().jobName("xxx").jobId("xxx").build();
        JobMonitor jobMonitor = new JobMonitor(connection);
        try {
            job = jobMonitor.waitByStatus(
                    job.getJobName().orElseThrow(() -> new ZosmfRequestException("job name not specified")),
                    job.getJobId().orElseThrow(() -> new ZosmfRequestException("job id not specified")), statusType);
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
        System.out.println("Job status for Job " + job.getJobName().orElse("n/a") + ":" +
                job.getJobId().orElse("n/a") + " is " + job.getStatus().orElse("n/a"));
    }

    /**
     * Example on how to call JobMonitor waitByMessage method.
     * The waitByMessage method accepts a message value which will monitor the job output until
     * the message is seen or times out if not seen.
     *
     * @param message given message text to monitor job output
     * @author Frank Giordano
     */
    public static void monitorWaitForJobMessage(String message) {
        // determine an existing job in your system that is in execute queue and make a Job for it
        Job job = new Job.Builder().jobName("xxx").jobId("xxx").build();
        JobMonitor jobMonitor = new JobMonitor(connection);
        try {
            System.out.println("Found message = " + jobMonitor.waitByMessage(job, message));
        } catch (ZosmfRequestException e) {
            final String errMsg = Util.getResponsePhrase(e.getResponse());
            throw new RuntimeException((errMsg != null ? errMsg : e.getMessage()));
        }
    }

}
