/*
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Copyright Contributors to the Zowe Project.
 */
package zowe.client.sdk.examples.zosjobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.ZosConnection;
import zowe.client.sdk.rest.Response;
import zowe.client.sdk.zosjobs.input.ModifyJobParams;
import zowe.client.sdk.zosjobs.response.Job;

/**
 * Class example to showcase DeleteJobs functionality.
 *
 * @author Leonid Baranov
 * @version 1.0
 */
public class DeleteJobs extends ZosConnection {

    private static final Logger LOG = LoggerFactory.getLogger(DeleteJobs.class);

    private static ZOSConnection connection;
    private static String jobName;
    private static String jobId;

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * DeleteJobs functionality. Calls DeleteJobs example methods.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Leonid Baranov
     */
    public static void main(String[] args) throws Exception {
        connection = new ZOSConnection(hostName, zosmfPort, userName, password);

        LOG.info(String.valueOf(deleteJobsCommonWithVersion("2.0")));
        LOG.info(String.valueOf(deleteJobsCommon()));
        LOG.info(String.valueOf(deleteJobForJob()));
        LOG.info(String.valueOf(deleteJob()));
    }

    /**
     * Example on how to call DeleteJobs deleteJobCommon method.
     * deleteJobCommon accepts a DeleteJobParams object with parameters filled needed to delete a given job and
     * the version to indicate 1.0 for async or 2.0 for sync processing of the request
     *
     * @param version value to indicate sync or async request processing
     * @return response http response object
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static Response deleteJobsCommonWithVersion(String version) throws Exception {
        jobId = "XXX";
        jobName = "XXX";
        ModifyJobParams params = new ModifyJobParams.Builder(jobName, jobId).version(version).build();
        return new zowe.client.sdk.zosjobs.DeleteJobs(connection).deleteJobCommon(params);
    }

    /**
     * Example on how to call DeleteJobs deleteJobCommon method.
     * deleteJobCommon accepts a DeleteJobParams object with parameters filled needed to delete a given job.
     *
     * @return response http response object
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static Response deleteJobsCommon() throws Exception {
        jobId = "XXX";
        jobName = "XXX";
        ModifyJobParams params = new ModifyJobParams.Builder(jobName, jobId).build();
        return new zowe.client.sdk.zosjobs.DeleteJobs(connection).deleteJobCommon(params);
    }

    /**
     * Example on how to call DeleteJobs deleteJobForJob method.
     * deleteJobForJob accepts a jobName and jobId values which will be used to delete its job.
     *
     * @return response http response object
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static Response deleteJobForJob() throws Exception {
        jobId = "XXX";
        jobName = "XXX";
        return new zowe.client.sdk.zosjobs.DeleteJobs(connection).deleteJobForJob(
                new Job.Builder().jobName(jobName).jobId(jobId).build(), null);
    }

    /**
     * Example on how to call DeleteJobs deleteJob method.
     * deleteJob accepts a jobName and jobId values which will be used to delete its job.
     *
     * @return response http response object
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static Response deleteJob() throws Exception {
        jobId = "XXX";
        jobName = "XXX";
        return new zowe.client.sdk.zosjobs.DeleteJobs(connection).deleteJob(jobName, jobId, null);
    }

}
