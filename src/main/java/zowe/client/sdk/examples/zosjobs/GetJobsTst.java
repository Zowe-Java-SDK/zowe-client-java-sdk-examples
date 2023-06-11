package zowe.client.sdk.examples.zosjobs;

import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.zosjobs.input.CommonJobParams;
import zowe.client.sdk.zosjobs.input.GetJobParams;
import zowe.client.sdk.zosjobs.input.JobFile;
import zowe.client.sdk.zosjobs.methods.JobGet;
import zowe.client.sdk.zosjobs.response.Job;

import java.util.Arrays;
import java.util.List;

/**
 * Class example to showcase GetJobs functionality.
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class GetJobsTst extends TstZosConnection {

    private static JobGet getJobs;

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * GetJobs functionality. Calls GetJobs example methods.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void main(String[] args) throws Exception {
        String prefix = "xxx";
        String owner = "xxx";
        String jobId = "xxx";

        ZOSConnection connection = new ZOSConnection(hostName, zosmfPort, userName, password);
        getJobs = new JobGet(connection);

        GetJobsTst.getJobsCommon(prefix);
        GetJobsTst.getSpoolFiles(prefix);
        GetJobsTst.getSpoolFilesForJob(prefix);
        GetJobsTst.getJobsByOwner(owner);
        GetJobsTst.getSpoolContent(prefix);
        GetJobsTst.getJobs();
        GetJobsTst.getJobsByPrefix(prefix);
        GetJobsTst.getJobsByOwnerAndPrefix("*", prefix);
        GetJobsTst.getJob(prefix);
        GetJobsTst.nonExistentGetJob(jobId);
        GetJobsTst.getStatusCommon(prefix);
        GetJobsTst.getStatus(prefix);
        GetJobsTst.getStatusForJob(prefix);
        GetJobsTst.getJcl(prefix);
        GetJobsTst.getJclForJob(prefix);
        GetJobsTst.getJclCommon(prefix);
    }

    /**
     * Example on how to call GetJobs getJclCommon method.
     * getJclCommon is given CommonJobParams object filled with information on the given job to
     * use for retrieval of its JCL content
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getJclCommon(String prefix) throws Exception {
        List<Job> jobs = getJobs.getJobsByPrefix(prefix);
        System.out.println(getJobs.getJclCommon(
                new CommonJobParams(jobs.get(0).getJobId().orElseThrow(() -> new Exception("job id not specified")),
                        jobs.get(0).getJobName().orElseThrow(() -> new Exception("job name not specified")))));
    }

    /**
     * Example on how to call GetJobs getJclForJob method.
     * getJclForJob is given a job object to use for retrieval of its JCL content
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getJclForJob(String prefix) throws Exception {
        List<Job> jobs = getJobs.getJobsByPrefix(prefix);
        System.out.println(getJobs.getJclForJob(jobs.get(0)));
    }

    /**
     * Example on how to call GetJobs getJcl method.
     * getJcl is given a jobName and jobId values to use for retrieval of its JCL content
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getJcl(String prefix) throws Exception {
        List<Job> jobs = getJobs.getJobsByPrefix(prefix);
        System.out.println(
                getJobs.getJcl(jobs.get(0).getJobName().orElseThrow(() -> new Exception("job name not specified")),
                        jobs.get(0).getJobId().orElseThrow(() -> new Exception("job id not specified"))));
    }

    /**
     * Example on how to call GetJobs getStatusForJob method.
     * getStatusForJob is given a job object to use for retrieval of its status
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getStatusForJob(String prefix) throws Exception {
        List<Job> jobs = getJobs.getJobsByPrefix(prefix);
        try {
            Job job = getJobs.getStatusForJob(jobs.get(0));
            System.out.println(job);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Example on how to call GetJobs getStatus method with StepData flag.
     * getStatus is given a jobName and jobId value to use for retrieval of its status with StepData flag set to true
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getStatusCommon(String prefix) throws Exception {
        List<Job> jobs = getJobs.getJobsByPrefix(prefix);
        try {
            Job job = getJobs.getStatusCommon(
                    new CommonJobParams(jobs.get(0).getJobId().orElseThrow(() -> new Exception("job id not specified")),
                            jobs.get(0).getJobName().orElseThrow(() -> new Exception("job name not specified")), true));
            System.out.println(job.toString());
            Arrays.stream(job.getStepData().orElseThrow(() -> new Exception("no step data found"))).forEach(i -> System.out.println(i.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Example on how to call GetJobs getStatus method.
     * getStatus is given a jobName and jobId value to use for retrieval of its status
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getStatus(String prefix) throws Exception {
        List<Job> jobs = getJobs.getJobsByPrefix(prefix);
        try {
            Job job = getJobs.getStatus(
                    jobs.get(0).getJobName().orElseThrow(() -> new Exception("job name not specified")),
                    jobs.get(0).getJobId().orElseThrow(() -> new Exception("job id not specified")));
            System.out.println(job.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Example on how to call GetJobs getJob method.
     * getJob is given a jobId value for a non-existing job which will return an exception
     *
     * @param jobId jobId value
     * @author Frank Giordano
     */
    public static void nonExistentGetJob(String jobId) {
        try {
            getJobs.getJob(jobId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Example on how to call GetJobs getJob method.
     * getJob is given a jobId value which will return a job document/object
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getJob(String prefix) throws Exception {
        List<Job> jobs = getJobs.getJobsByPrefix(prefix);
        String jobId = jobs.get(0).getJobId().orElseThrow(() -> new Exception("job id not specified"));
        try {
            Job job = getJobs.getJob(jobId);
            System.out.println(job.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Example on how to call GetJobs getJobsByOwnerAndPrefix method.
     * getJobsByOwnerAndPrefix is given an owner and prefix values which will return a
     * list of common job document/object
     *
     * @param owner  owner value
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getJobsByOwnerAndPrefix(String owner, String prefix) throws Exception {
        List<Job> jobs = getJobs.getJobsByOwnerAndPrefix(owner, prefix);
        jobs.forEach(i -> System.out.println(i.toString()));
    }

    /**
     * Example on how to call GetJobs getJobsByPrefix method.
     * getJobsByPrefix is given a prefix value which will return a
     * list of common job document/object
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getJobsByPrefix(String prefix) throws Exception {
        List<Job> jobs = getJobs.getJobsByPrefix(prefix);
        jobs.forEach(i -> System.out.println(i.toString()));
    }

    /**
     * Example on how to call GetJobs' getJobs method. It returns a list of all
     * jobs available for the logged-in user.
     *
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getJobs() throws Exception {
        // get any jobs out there for the logged-in user
        List<Job> jobs = getJobs.getJobs();
        jobs.forEach(i -> System.out.println(i.toString()));
    }

    /**
     * Example on how to call GetJobs getSpoolContent method.
     * getSpoolContent is given a job spool file name to retrieve its content.
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getSpoolContent(String prefix) throws Exception {
        GetJobParams params = new GetJobParams.Builder("*").prefix(prefix).build();
        List<Job> jobs = getJobs.getJobsCommon(params);
        List<JobFile> files = getJobs.getSpoolFilesForJob(jobs.get(0));
        String[] output = getJobs.getSpoolContent(files.get(0)).split("\n");
        // get last 10 lines of output
        for (int i = output.length - 10; i < output.length; i++)
            System.out.println(output[i]);
    }

    /**
     * Example on how to call GetJobs getJobsByOwner method.
     * getJobsByOwner is given an owner value to use retrieve a list of its available jobs.
     *
     * @param owner owner value
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getJobsByOwner(String owner) throws Exception {
        List<Job> jobs = getJobs.getJobsByOwner(owner);
        jobs.forEach(i -> System.out.println(i.toString()));
    }

    /**
     * Example on how to call GetJobs getSpoolFilesForJob method.
     * getSpoolFilesForJob is given a job document/object retrieve a list of all its spool names.
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getSpoolFilesForJob(String prefix) throws Exception {
        GetJobParams params = new GetJobParams.Builder("*").prefix(prefix).build();
        List<Job> jobs = getJobs.getJobsCommon(params);
        List<JobFile> files = getJobs.getSpoolFilesForJob(jobs.get(0));
        files.forEach(i -> System.out.println(i.toString()));
    }

    /**
     * Example on how to call GetJobs getSpoolFiles method.
     * getSpoolFiles is given a jobName and jobId values to retrieve a list of all its spool file names.
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getSpoolFiles(String prefix) throws Exception {
        GetJobParams params = new GetJobParams.Builder("*").prefix(prefix).build();
        List<Job> jobs = getJobs.getJobsCommon(params);
        List<JobFile> files =
                getJobs.getSpoolFiles(
                        jobs.get(0).getJobName().orElseThrow(() -> new Exception("job name not specified")),
                        jobs.get(0).getJobId().orElseThrow(() -> new Exception("job id not specified")));
        files.forEach(i -> System.out.println(i.toString()));
    }

    /**
     * Example on how to call GetJobs getJobsCommon method.
     * getJobsCommon is given a GetJobParams object filled with search parameters which will retrieve a list of all jobs.
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getJobsCommon(String prefix) throws Exception {
        GetJobParams params = new GetJobParams.Builder("*").prefix(prefix).build();
        List<Job> jobs = getJobs.getJobsCommon(params);
        jobs.forEach(i -> System.out.println(i.toString()));
    }

}
