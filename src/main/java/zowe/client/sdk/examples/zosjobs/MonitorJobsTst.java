package zowe.client.sdk.examples.zosjobs;

import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.zosjobs.SubmitJobs;
import zowe.client.sdk.zosjobs.input.MonitorJobWaitForParams;
import zowe.client.sdk.zosjobs.response.Job;
import zowe.client.sdk.zosjobs.types.JobStatus;

/**
 * Class example to showcase MonitorJobs functionality.
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class MonitorJobsTst extends TstZosConnection {

    private static SubmitJobs submitJobs;
    private static ZOSConnection connection;

    /**
     * Main method defines z/OSMF host and user connection needed to showcase
     * MonitorJobs functionality. Calls MonitorJobs example methods.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void main(String[] args) throws Exception {
        connection = new ZOSConnection(hostName, zosmfPort, userName, password);
        submitJobs = new SubmitJobs(connection);
        MonitorJobsTst.monitorJobsForOutputStatusByJobObject();
        MonitorJobsTst.monitorJobsForOutputStatusByJobNameAndId();
        MonitorJobsTst.monitorJobsForStatusByJobObject(JobStatus.Type.INPUT);
        MonitorJobsTst.monitorJobsForStatusByJobNameAndId(JobStatus.Type.ACTIVE);
        MonitorJobsTst.monitorWaitForJobMessage("xxx");
        monitorIsJobRunning();
    }

    /**
     * Example on how to call MonitorJobs isJobRunning method.
     * isJobRunning accepts a MonitorJobWaitForParams object
     *
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void monitorIsJobRunning() throws Exception {
        zowe.client.sdk.zosjobs.MonitorJobs monitorJobs = new zowe.client.sdk.zosjobs.MonitorJobs(connection);
        MonitorJobWaitForParams monitorParams = new MonitorJobWaitForParams.Builder("XXX", "XXX").build();
        System.out.println(monitorJobs.isJobRunning(monitorParams));
    }

    /**
     * Example on how to call MonitorJobs waitForJobOutputStatus method.
     * waitForJobOutputStatus accepts a job object which will monitor the job status
     * until it reaches OUTPUT status or times out if not reached.
     *
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void monitorJobsForOutputStatusByJobObject() throws Exception {
        String jclString = "//TESTJOBX JOB (),MSGCLASS=H\r // EXEC PGM=IEFBR14";
        Job job = submitJobs.submitJcl(jclString, null, null);
        zowe.client.sdk.zosjobs.MonitorJobs monitorJobs = new zowe.client.sdk.zosjobs.MonitorJobs(connection);
        job = monitorJobs.waitForJobOutputStatus(job);
        System.out.println("Job status for Job " + job.getJobName().orElse("n/a") + ":" +
                job.getJobId().orElse("n/a") + " is " + job.getStatus().orElse("n/a"));
    }

    /**
     * Example on how to call MonitorJobs waitForJobOutputStatus method.
     * waitForJobOutputStatus accepts a jobName and jobId values which will
     * monitor the job status until it reaches OUTPUT status or times out if not reached.
     *
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void monitorJobsForOutputStatusByJobNameAndId() throws Exception {
        String jclString = "//TESTJOBX JOB (),MSGCLASS=H\r // EXEC PGM=IEFBR14";
        Job job = submitJobs.submitJcl(jclString, null, null);
        zowe.client.sdk.zosjobs.MonitorJobs monitorJobs = new zowe.client.sdk.zosjobs.MonitorJobs(connection);
        job = monitorJobs.waitForJobOutputStatus(
                job.getJobName().orElseThrow(() -> new Exception("job name not specified")),
                job.getJobId().orElseThrow(() -> new Exception("job id not specified")));
        System.out.println("Job status for Job " + job.getJobName().orElse("n/a") + ":" +
                job.getJobId().orElse("n/a") + " is " + job.getStatus().orElse("n/a"));
    }

    /**
     * Example on how to call MonitorJobs waitForJobStatus method.
     * waitForJobStatus accepts a job object and status value which will monitor the
     * job status until it reaches the given status value or times out if not reached.
     *
     * @param statusType given status type to use for monitoring
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void monitorJobsForStatusByJobObject(JobStatus.Type statusType) throws Exception {
        // determine an existing job in your system that is in execute queue and make a Job for it
        Job job = new Job.Builder().jobName("xxx").jobId("xxx").build();
        zowe.client.sdk.zosjobs.MonitorJobs monitorJobs = new zowe.client.sdk.zosjobs.MonitorJobs(connection);
        job = monitorJobs.waitForJobStatus(job, statusType);
        System.out.println("Job status for Job " + job.getJobName().orElse("n/a") + ":" +
                job.getJobId().orElse("n/a") + " is " + job.getStatus().orElse("n/a"));
    }

    /**
     * Example on how to call MonitorJobs waitForJobStatus method.
     * waitForJobStatus accepts a jobName and jobId values and status value which will monitor the
     * job status until it reaches the given status value or times out if not reached.
     *
     * @param statusType given status type to use for monitoring
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void monitorJobsForStatusByJobNameAndId(JobStatus.Type statusType) throws Exception {
        // determine an existing job in your system that is in execute queue and make a Job for it
        Job job = new Job.Builder().jobName("xxx").jobId("xxx").build();
        zowe.client.sdk.zosjobs.MonitorJobs monitorJobs = new zowe.client.sdk.zosjobs.MonitorJobs(connection);
        job = monitorJobs.waitForJobStatus(
                job.getJobName().orElseThrow(() -> new Exception("job name not specified")),
                job.getJobId().orElseThrow(() -> new Exception("job id not specified")), statusType);
        System.out.println("Job status for Job " + job.getJobName().orElse("n/a") + ":" +
                job.getJobId().orElse("n/a") + " is " + job.getStatus().orElse("n/a"));
    }

    /**
     * Example on how to call MonitorJobs tstMonitorWaitForJobMessage method.
     * tstMonitorWaitForJobMessage accepts a message value which will monitor the
     * job output until the message is seen or times out if not seen.
     *
     * @param message given message text to monitor job output
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void monitorWaitForJobMessage(String message) throws Exception {
        // determine an existing job in your system that is in execute queue and make a Job for it
        Job job = new Job.Builder().jobName("xxx").jobId("xxx").build();
        zowe.client.sdk.zosjobs.MonitorJobs monitorJobs = new zowe.client.sdk.zosjobs.MonitorJobs(connection);
        System.out.println("Found message = " + monitorJobs.waitForJobMessage(job, message));
    }

}
