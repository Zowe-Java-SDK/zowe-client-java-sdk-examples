package zowe.client.sdk.examples.zosjobs;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.zosjobs.input.CommonJobParams;
import zowe.client.sdk.zosjobs.input.GetJobParams;
import zowe.client.sdk.zosjobs.input.JobFile;
import zowe.client.sdk.zosjobs.methods.JobGet;
import zowe.client.sdk.zosjobs.response.Job;

import java.util.Arrays;
import java.util.List;

/**
 * Class example to showcase JobGet class functionality.
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class GetJobTst extends TstZosConnection {

    private static JobGet getJob;

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * JobGet class functionality.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void main(String[] args) throws Exception {
        String prefix = "xxx";
        String owner = "xxx";
        String jobId = "xxx";

        ZosConnection connection = new ZosConnection(hostName, zosmfPort, userName, password);
        getJob = new JobGet(connection);

        GetJobTst.getCommon(prefix);
        GetJobTst.getSpoolFiles(prefix);
        GetJobTst.getSpoolFilesForJob(prefix);
        GetJobTst.getByOwner(owner);
        GetJobTst.getSpoolContent(prefix);
        GetJobTst.getAll();
        GetJobTst.getByPrefix(prefix);
        GetJobTst.getByOwnerAndPrefix("*", prefix);
        GetJobTst.getById(prefix);
        GetJobTst.nonExistentGetJob(jobId);
        GetJobTst.getStatusCommon(prefix);
        GetJobTst.getStatus(prefix);
        GetJobTst.getStatusByJob(prefix);
        GetJobTst.getJcl(prefix);
        GetJobTst.getJclByJob(prefix);
        GetJobTst.getJclCommon(prefix);
    }

    /**
     * Example on how to call JobGet getJclCommon method.
     * The getJclCommon method is given CommonJobParams object filled with information on the given job to
     * use for retrieval of its JCL content.
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getJclCommon(String prefix) throws Exception {
        List<Job> jobs = getJob.getByPrefix(prefix);
        System.out.println(getJob.getJclCommon(
                new CommonJobParams(jobs.get(0).getJobId().orElseThrow(() -> new Exception("job id not specified")),
                        jobs.get(0).getJobName().orElseThrow(() -> new Exception("job name not specified")))));
    }

    /**
     * Example on how to call JobGet getJclByJob method.
     * The getJclByJob method is given a job object to use for retrieval of its JCL content.
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getJclByJob(String prefix) throws Exception {
        List<Job> jobs = getJob.getByPrefix(prefix);
        System.out.println(getJob.getJclByJob(jobs.get(0)));
    }

    /**
     * Example on how to call JobGet getJcl method.
     * The getJcl method is given a jobName and jobId values to use for retrieval of its JCL content.
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getJcl(String prefix) throws Exception {
        List<Job> jobs = getJob.getByPrefix(prefix);
        System.out.println(
                getJob.getJcl(jobs.get(0).getJobName().orElseThrow(() -> new Exception("job name not specified")),
                        jobs.get(0).getJobId().orElseThrow(() -> new Exception("job id not specified"))));
    }

    /**
     * Example on how to call JobGet getStatusByJob method.
     * The getStatusByJob method is given a job object to use for retrieval of its status.
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getStatusByJob(String prefix) throws Exception {
        List<Job> jobs = getJob.getByPrefix(prefix);
        try {
            Job job = getJob.getStatusByJob(jobs.get(0));
            System.out.println(job);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Example on how to call JobGet getStatusCommon method with StepData flag.
     * The getStatusCommon method is given a jobName and jobId value to use for retrieval
     * of its status with StepData flag set to true.
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getStatusCommon(String prefix) throws Exception {
        List<Job> jobs = getJob.getByPrefix(prefix);
        try {
            Job job = getJob.getStatusCommon(
                    new CommonJobParams(jobs.get(0).getJobId().orElseThrow(() -> new Exception("job id not specified")),
                            jobs.get(0).getJobName().orElseThrow(() -> new Exception("job name not specified")), true));
            System.out.println(job.toString());
            Arrays.stream(job.getStepData().orElseThrow(() -> new Exception("no step data found"))).forEach(i -> System.out.println(i.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Example on how to call JobGet getStatus method.
     * The getStatus method is given a jobName and jobId value to use for retrieval of its status.
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getStatus(String prefix) throws Exception {
        List<Job> jobs = getJob.getByPrefix(prefix);
        try {
            Job job = getJob.getStatus(
                    jobs.get(0).getJobName().orElseThrow(() -> new Exception("job name not specified")),
                    jobs.get(0).getJobId().orElseThrow(() -> new Exception("job id not specified")));
            System.out.println(job.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Example on how to call JobGet getById method.
     * The getById method is given a jobId value for a non-existing job which will return an exception.
     *
     * @param jobId jobId value
     * @author Frank Giordano
     */
    public static void nonExistentGetJob(String jobId) {
        try {
            getJob.getById(jobId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Example on how to call JobGet getById method.
     * The getById method is given a jobId value which will return a job document/object.
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getById(String prefix) throws Exception {
        List<Job> jobs = getJob.getByPrefix(prefix);
        String jobId = jobs.get(0).getJobId().orElseThrow(() -> new Exception("job id not specified"));
        try {
            Job job = getJob.getById(jobId);
            System.out.println(job.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Example on how to call JobGet getByOwnerAndPrefix method.
     * The getByOwnerAndPrefix method is given an owner and prefix values which will return a
     * list of common job document/object.
     *
     * @param owner  owner value
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getByOwnerAndPrefix(String owner, String prefix) throws Exception {
        List<Job> jobs = getJob.getByOwnerAndPrefix(owner, prefix);
        jobs.forEach(i -> System.out.println(i.toString()));
    }

    /**
     * Example on how to call JobGet getByPrefix method.
     * The getByPrefix method is given a prefix value which will return a list of common job document/object.
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getByPrefix(String prefix) throws Exception {
        List<Job> jobs = getJob.getByPrefix(prefix);
        jobs.forEach(i -> System.out.println(i.toString()));
    }

    /**
     * Example on how to call JobGet getAll method.
     * The getAll method returns a list of all jobs available for the logged-in user.
     *
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getAll() throws Exception {
        // get any jobs out there for the logged-in user
        List<Job> jobs = getJob.getAll();
        jobs.forEach(i -> System.out.println(i.toString()));
    }

    /**
     * Example on how to call JobGet getSpoolContent method.
     * The getSpoolContent method is given a job spool file name to retrieve its content.
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getSpoolContent(String prefix) throws Exception {
        GetJobParams params = new GetJobParams.Builder("*").prefix(prefix).build();
        List<Job> jobs = getJob.getCommon(params);
        List<JobFile> files = getJob.getSpoolFilesByJob(jobs.get(0));
        String[] output = getJob.getSpoolContent(files.get(0)).split("\n");
        // get last 10 lines of output
        for (int i = output.length - 10; i < output.length; i++)
            System.out.println(output[i]);
    }

    /**
     * Example on how to call JobGet getByOwner method.
     * The getByOwner method is given an owner value to use retrieve a list of its available jobs.
     *
     * @param owner owner value
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getByOwner(String owner) throws Exception {
        List<Job> jobs = getJob.getByOwner(owner);
        jobs.forEach(i -> System.out.println(i.toString()));
    }

    /**
     * Example on how to call JobGet getSpoolFilesByJob method.
     * The getSpoolFilesByJob method is given a job document/object retrieve a list of all its spool names.
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getSpoolFilesForJob(String prefix) throws Exception {
        GetJobParams params = new GetJobParams.Builder("*").prefix(prefix).build();
        List<Job> jobs = getJob.getCommon(params);
        List<JobFile> files = getJob.getSpoolFilesByJob(jobs.get(0));
        files.forEach(i -> System.out.println(i.toString()));
    }

    /**
     * Example on how to call JobGet getSpoolFiles method.
     * The getSpoolFiles method is given a jobName and jobId values to retrieve a list of all its spool file names.
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getSpoolFiles(String prefix) throws Exception {
        GetJobParams params = new GetJobParams.Builder("*").prefix(prefix).build();
        List<Job> jobs = getJob.getCommon(params);
        List<JobFile> files =
                getJob.getSpoolFiles(
                        jobs.get(0).getJobName().orElseThrow(() -> new Exception("job name not specified")),
                        jobs.get(0).getJobId().orElseThrow(() -> new Exception("job id not specified")));
        files.forEach(i -> System.out.println(i.toString()));
    }

    /**
     * Example on how to call JobGet getCommon method.
     * The getCommon method is given a GetJobParams object filled with search parameters which will
     * retrieve a list of all jobs.
     *
     * @param prefix partial or full job name to use for searching
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void getCommon(String prefix) throws Exception {
        GetJobParams params = new GetJobParams.Builder("*").prefix(prefix).build();
        List<Job> jobs = getJob.getCommon(params);
        jobs.forEach(i -> System.out.println(i.toString()));
    }

}
