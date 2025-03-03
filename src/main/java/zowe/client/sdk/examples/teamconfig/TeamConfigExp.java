package zowe.client.sdk.examples.teamconfig;

import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.rest.exception.ZosmfRequestException;
import zowe.client.sdk.teamconfig.TeamConfig;
import zowe.client.sdk.teamconfig.exception.TeamConfigException;
import zowe.client.sdk.teamconfig.keytar.KeyTarImpl;
import zowe.client.sdk.teamconfig.model.ProfileDao;
import zowe.client.sdk.teamconfig.service.KeyTarService;
import zowe.client.sdk.teamconfig.service.TeamConfigService;
import zowe.client.sdk.zosfiles.dsn.input.ListParams;
import zowe.client.sdk.zosfiles.dsn.methods.DsnList;
import zowe.client.sdk.zosfiles.dsn.response.Member;

import java.util.List;

/**
 * Class example to showcase team config functionality via TeamConfig class.
 *
 * @author Frank Giordano
 * @version 3.0
 */
public class TeamConfigExp {

    /**
     * Main method defines TeamConfig object and operation to retrieve the default
     * zosmf profile from Zowe Team Configuration.
     * <p>
     * Zowe Team Configuration contains the connection information for z/OSMF REST API.
     * <p>
     * Moreover, the retrieval of the zosmf profile will retrieve secure Zowe V2 credentials
     * (username/password) entered via the Zowe Global Team Configuration command.
     * <p>
     * Calls TeamConfigExp.listMembers example method.
     *
     * @param args for main not used
     * @throws ZosmfRequestException request error state
     * @author Frank Giordano
     */
    public static void main(String[] args) throws ZosmfRequestException {
        String dataSetName = "CCSQA.ASM.JCL";

        TeamConfig teamConfig;
        try {
            teamConfig = new TeamConfig(new KeyTarService(new KeyTarImpl()), new TeamConfigService());
        } catch (TeamConfigException e) {
            throw new RuntimeException(e.getMessage());
        }
        ProfileDao profile = teamConfig.getDefaultProfileByName("zosmf");
        ZosConnection connection = new ZosConnection(profile.getHost(), profile.getPort(),
                profile.getUser(), profile.getPassword());

        TeamConfigExp.listMembers(connection, dataSetName);
    }

    /**
     * List out all members of the given dataset
     *
     * @param connection  ZOSConnection object
     * @param dataSetName data set name
     * @throws ZosmfRequestException request error state
     * @author Frank Giordano
     */
    public static void listMembers(ZosConnection connection, String dataSetName) throws ZosmfRequestException {
        ListParams params = new ListParams.Builder().build();
        DsnList dsnList = new DsnList(connection);
        List<Member> datasets = dsnList.getMembers(dataSetName, params);
        datasets.forEach(System.out::println);
    }

}
