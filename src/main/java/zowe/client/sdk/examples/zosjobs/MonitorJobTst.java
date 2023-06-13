package zowe.client.sdk.examples.zosjobs;

import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.zosjobs.input.MonitorJobWaitForParams;
import zowe.client.sdk.zosjobs.methods.JobMonitor;
import zowe.client.sdk.zosjobs.methods.JobSubmit;
import zowe.client.sdk.zosjobs.response.Job;
import zowe.client.sdk.zosjobs.types.JobStatus;

/**
 * Class example to showcase JobMonitor class functionality.
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class MonitorJobTst extends TstZosConnection {

    private static JobSubmit submitJob;
    private static ZOSConnection connection;

    /**
     * Main method defines z/OSMF host and user connection needed to showcase
     * JobMonitor functionality.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void main(String[] args) throws Exception {
        connection = new ZOSConnection(hostName, zosmfPort, userName, password);
        submitJob = new JobSubmit(connection);
        MonitorJobTst.monitorJobForOutputStatusByJobObject();
        MonitorJobTst.monitorJobForOutputStatusByJobNameAndId();
        MonitorJobTst.monitorJobForStatusByJobObject(JobStatus.Type.INPUT);
        MonitorJobTst.monitorJobForStatusByJobNameAndId(JobStatus.Type.ACTIVE);
        MonitorJobTst.monitorWaitForJobMessage("xxx");
        monitorIsJobRunning();
    }

    /**
     * Example on how to call JobMonitor isJobRunning method.
     * The isJobRunning method accepts a MonitorJobWaitForParams object.
     *
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void monitorIsJobRunning() throws Exception {
        JobMonitor jobMonitor = new JobMonitor(connection);
        MonitorJobWaitForParams monitorParams = new MonitorJobWaitForParams.Builder("XXX", "XXX").build();
        System.out.println(jobMonitor.isRunning(monitorParams));
    }

    /**
     * Example on how to call JobMonitor waitByOutputStatus method.
     * The waitByOutputStatus method accepts a job object which will monitor the job status
     * until it reaches OUTPUT status or times out if not reached.
     *
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void monitorJobForOutputStatusByJobObject() throws Exception {
        String jclString = "//TESTJOBX JOB (),MSGCLASS=H\r // EXEC PGM=IEFBR14";
        Job job = submitJob.submitByJcl(jclString, null, null);
        JobMonitor jobMonitor = new JobMonitor(connection);
        job = jobMonitor.waitByOutputStatus(job);
        System.out.println("Job status for Job " + job.getJobName().orElse("n/a") + ":" +
                job.getJobId().orElse("n/a") + " is " + job.getStatus().orElse("n/a"));
    }

    /**
     * Example on how to call JobMonitor waitByOutputStatus method.
     * The waitByOutputStatus method accepts a jobName and jobId values which will
     * monitor the job status until it reaches OUTPUT status or times out if not reached.
     *
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void monitorJobForOutputStatusByJobNameAndId() throws Exception {
        String jclString = "//TESTJOBX JOB (),MSGCLASS=H\r // EXEC PGM=IEFBR14";
        Job job = submitJob.submitByJcl(jclString, null, null);
        JobMonitor jobMonitor = new JobMonitor(connection);
        job = jobMonitor.waitByOutputStatus(
                job.getJobName().orElseThrow(() -> new Exception("job name not specified")),
                job.getJobId().orElseThrow(() -> new Exception("job id not specified")));
        System.out.println("Job status for Job " + job.getJobName().orElse("n/a") + ":" +
                job.getJobId().orElse("n/a") + " is " + job.getStatus().orElse("n/a"));
    }

    /**
     * Example on how to call JobMonitor waitByStatus method.
     * The waitByStatus method accepts a job object and status value which will monitor the
     * job status until it reaches the given status value or times out if not reached.
     *
     * @param statusType given status type to use for monitoring
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void monitorJobForStatusByJobObject(JobStatus.Type statusType) throws Exception {
        // determine an existing job in your system that is in execute queue and make a Job for it
        Job job = new Job.Builder().jobName("xxx").jobId("xxx").build();
        JobMonitor jobMonitor = new JobMonitor(connection);
        job = jobMonitor.waitByStatus(job, statusType);
        System.out.println("Job status for Job " + job.getJobName().orElse("n/a") + ":" +
                job.getJobId().orElse("n/a") + " is " + job.getStatus().orElse("n/a"));
    }

    /**
     * Example on how to call JobMonitor waitByStatus method.
     * The waitByStatus method accepts a jobName and jobId values and status value which will monitor the
     * job status until it reaches the given status value or times out if not reached.
     *
     * @param statusType given status type to use for monitoring
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void monitorJobForStatusByJobNameAndId(JobStatus.Type statusType) throws Exception {
        // determine an existing job in your system that is in execute queue and make a Job for it
        Job job = new Job.Builder().jobName("xxx").jobId("xxx").build();
        JobMonitor jobMonitor = new JobMonitor(connection);
        job = jobMonitor.waitByStatus(
                job.getJobName().orElseThrow(() -> new Exception("job name not specified")),
                job.getJobId().orElseThrow(() -> new Exception("job id not specified")), statusType);
        System.out.println("Job status for Job " + job.getJobName().orElse("n/a") + ":" +
                job.getJobId().orElse("n/a") + " is " + job.getStatus().orElse("n/a"));
    }

    /**
     * Example on how to call JobMonitor waitByMessage method.
     * The waitByMessage method accepts a message value which will monitor the job output until
     * the message is seen or times out if not seen.
     *
     * @param message given message text to monitor job output
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void monitorWaitForJobMessage(String message) throws Exception {
        // determine an existing job in your system that is in execute queue and make a Job for it
        Job job = new Job.Builder().jobName("xxx").jobId("xxx").build();
        JobMonitor jobMonitor = new JobMonitor(connection);
        System.out.println("Found message = " + jobMonitor.waitByMessage(job, message));
    }

}
