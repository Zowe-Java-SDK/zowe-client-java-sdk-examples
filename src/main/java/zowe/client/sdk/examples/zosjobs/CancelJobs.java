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
 * Class example to showcase CancelJobs functionality.
 *
 * @author Leonid Baranov
 * @version 1.0
 */
public class CancelJobs extends ZosConnection {

    private static final Logger LOG = LoggerFactory.getLogger(CancelJobs.class);

    private static zowe.client.sdk.core.ZOSConnection connection;
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

        LOG.info(String.valueOf(cancelJobsCommonWithVersion("2.0")));
        LOG.info(String.valueOf(cancelJobsCommon()));
        LOG.info(String.valueOf(cancelJobForJob()));
        LOG.info(String.valueOf(cancelJob()));
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
        jobId = "XXX";
        jobName = "XXX";
        ModifyJobParams params = new ModifyJobParams.Builder(jobName, jobId).version(version).build();
        return new zowe.client.sdk.zosjobs.CancelJobs(connection).cancelJobsCommon(params);
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
        jobId = "XXX";
        jobName = "XXX";
        ModifyJobParams params = new ModifyJobParams.Builder(jobName, jobId).build();
        return new zowe.client.sdk.zosjobs.CancelJobs(connection).cancelJobsCommon(params);
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
        jobId = "XXX";
        jobName = "XXX";
        return new zowe.client.sdk.zosjobs.CancelJobs(connection).cancelJobForJob(
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
        jobId = "XXX";
        jobName = "XXX";
        return new zowe.client.sdk.zosjobs.CancelJobs(connection).cancelJob(jobName, jobId, null);
    }

}
