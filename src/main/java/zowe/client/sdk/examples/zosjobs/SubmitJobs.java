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
import zowe.client.sdk.zosjobs.response.Job;

/**
 * Class example to showcase SubmitJobs functionality.
 *
 * @author Frank Giordano
 * @version 1.0
 */
public class SubmitJobs extends ZosConnection {

    private static final Logger LOG = LoggerFactory.getLogger(SubmitJobs.class);

    /**
     * Main method defines z/OSMF host and user connection needed to showcase
     * SubmitJobs functionality. Calls SubmitJobs example methods.
     *
     * @param args for main not used
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static void main(String[] args) throws Exception {
        ZOSConnection connection = new ZOSConnection(hostName, zosmfPort, userName, password);
        LOG.info(String.valueOf(SubmitJobs.submitJob(connection, "xxx.xxx.xxx.xxx(xxx)")));

        String jclString = "//TESTJOBX JOB (),MSGCLASS=H\n// EXEC PGM=IEFBR14";
        Job submitJobsTest = SubmitJobs.submitJclJob(connection, jclString);
        // Wait for the job to complete
        zowe.client.sdk.zosjobs.MonitorJobs monitorJobs = new zowe.client.sdk.zosjobs.MonitorJobs(connection);
        submitJobsTest = monitorJobs.waitForJobStatus(submitJobsTest, zowe.client.sdk.zosjobs.types.JobStatus.Type.OUTPUT);
        System.out.println(submitJobsTest);
        // Get the return code
        String retCode = submitJobsTest.getRetCode().orElse("n/a");
        System.out.println("Expected Return Code = CC 0000 [" + retCode + "]");
    }

    /**
     * Example on how to call SubmitJobs submitJcl method.
     * submitJcl is given a jcl string to use to submit it as a job.
     *
     * @param connection ZOSConnection object
     * @param jclString  jcl formatted string
     * @return job document
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static Job submitJclJob(zowe.client.sdk.core.ZOSConnection connection, String jclString) throws Exception {
        zowe.client.sdk.zosjobs.SubmitJobs submitJobs = new zowe.client.sdk.zosjobs.SubmitJobs(connection);
        return submitJobs.submitJcl(jclString, null, null);
    }

    /**
     * Example on how to call SubmitJobs submitJcl method.
     * submitJcl is given a Dataset member value to use to submit it as a job.
     *
     * @param connection ZOSConnection object
     * @param dsMember   dataset member value
     * @return job document
     * @throws Exception error in processing request
     * @author Frank Giordano
     */
    public static Job submitJob(zowe.client.sdk.core.ZOSConnection connection, String dsMember) throws Exception {
        zowe.client.sdk.zosjobs.SubmitJobs submitJobs = new zowe.client.sdk.zosjobs.SubmitJobs(connection);
        return submitJobs.submitJob(dsMember);
    }

}
