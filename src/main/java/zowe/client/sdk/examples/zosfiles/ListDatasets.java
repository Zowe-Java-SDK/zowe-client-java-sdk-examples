/*
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Copyright Contributors to the Zowe Project.
 */
package zowe.client.sdk.examples.zosfiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zowe.client.sdk.core.ZOSConnection;
import zowe.client.sdk.examples.ZosConnection;
import zowe.client.sdk.zosfiles.ZosDsnList;
import zowe.client.sdk.zosfiles.input.ListParams;
import zowe.client.sdk.zosfiles.response.Dataset;
import zowe.client.sdk.zosfiles.response.Member;
import zowe.client.sdk.zosfiles.types.AttributeType;

import java.util.List;

/**
 * Class example to showcase ListDatasets functionality.
 *
 * @author Leonid Baranov
 * @version 1.0
 */
public class ListDatasets extends ZosConnection {

    private static final Logger LOG = LoggerFactory.getLogger(ListDatasets.class);

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * ListDatasets functionality. Calls ListDatasets example methods.
     *
     * @param args for main not used
     * @throws Exception error processing request
     * @author Leonid Baranov
     */
    public static void main(String[] args) throws Exception {
        String dataSetMask = "XXX";
        String dataSetName = "XXX";

        ZOSConnection connection = new ZOSConnection(hostName, zosmfPort, userName, password);
        ListDatasets.listDsn(connection, dataSetMask);
        ListDatasets.listDsnVol(connection, dataSetMask);
        ListDatasets.listMembers(connection, dataSetName);
    }

    /**
     * List out all members of the given data set
     *
     * @param connection  ZOSConnection object
     * @param dataSetName data set name
     * @throws Exception error processing request
     * @author Leonid Baranov
     */
    public static void listMembers(zowe.client.sdk.core.ZOSConnection connection, String dataSetName) throws Exception {
        ListParams params = new ListParams.Builder().attribute(AttributeType.BASE).build();
        ZosDsnList zosDsnList = new ZosDsnList(connection);
        List<Member> datasets = zosDsnList.listDsnMembers(dataSetName, params);
        datasets.forEach(m -> m.toString());
    }

    /**
     * List out all data sets of the given data set. Each dataset returned will contain all of its properties.
     *
     * @param connection  ZOSConnection object
     * @param dataSetName data set name
     * @throws Exception error processing request
     * @author Leonid Baranov
     */
    public static void listDsn(zowe.client.sdk.core.ZOSConnection connection, String dataSetName) throws Exception {
        ListParams params = new ListParams.Builder().attribute(AttributeType.BASE).build();
        ZosDsnList zosDsnList = new ZosDsnList(connection);
        List<Dataset> datasets = zosDsnList.listDsn(dataSetName, params);
        datasets.forEach(i -> LOG.info(String.valueOf(i)));
    }

    /**
     * List out all data sets of the given data set. Each dataset returned will contain its volume property.
     *
     * @param connection  ZOSConnection object
     * @param dataSetName data set name
     * @throws Exception error processing request
     * @author Frank Giordano
     */
    public static void listDsnVol(zowe.client.sdk.core.ZOSConnection connection, String dataSetName) throws Exception {
        ListParams params = new ListParams.Builder().attribute(AttributeType.VOL).build();
        ZosDsnList zosDsnList = new ZosDsnList(connection);
        List<Dataset> datasets = zosDsnList.listDsn(dataSetName, params);
        datasets.forEach(i -> LOG.info(String.valueOf(i)));
    }

}
