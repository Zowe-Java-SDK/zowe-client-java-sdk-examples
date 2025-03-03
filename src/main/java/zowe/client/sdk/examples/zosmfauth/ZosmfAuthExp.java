package zowe.client.sdk.examples.zosmfauth;

import kong.unirest.core.Cookie;
import kong.unirest.core.Cookies;
import org.apache.commons.io.IOUtils;
import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.rest.Response;
import zowe.client.sdk.rest.exception.ZosmfRequestException;
import zowe.client.sdk.zosfiles.dsn.input.DownloadParams;
import zowe.client.sdk.zosfiles.dsn.methods.DsnGet;
import zowe.client.sdk.zosmfauth.input.PasswordParams;
import zowe.client.sdk.zosmfauth.methods.ZosmfLogin;
import zowe.client.sdk.zosmfauth.methods.ZosmfLogout;
import zowe.client.sdk.zosmfauth.methods.ZosmfPassword;
import zowe.client.sdk.zosmfauth.response.ZosmfLoginResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Class example to showcase z/OSMF AUTH APIs functionality.
 *
 * @author Frank Giordano
 * @version 3.0
 */
public class ZosmfAuthExp extends TstZosConnection {

    /**
     * Main method defines z/OSMF host and user connection and showcases examples
     * calling the zosmfauth APIs.
     * <p>
     * In addition, the examples showcases the usage of token authentication rather than
     * basic authentication.
     *
     * @param args for main not used
     * @author Frank Giordano
     */
    public static void main(String[] args) throws ZosmfRequestException {
        ZosConnection connection = new ZosConnection(hostName, zosmfPort, userName, password);
        ZosmfLogin login = new ZosmfLogin(connection);

        // request to log into server and obtain authentication tokens
        ZosmfLoginResponse loginResponse = login.login();
        // display response
        System.out.println(loginResponse);

        Cookies cookies = loginResponse.getCookies();
        Cookie jwtToken = cookies.get(0);
        Cookie ltpaToken = cookies.get(1);
        // display jwtToken
        System.out.println(jwtToken);
        // display LtpaToken
        System.out.println(ltpaToken);

        // Perform an API call with token authentication used
        String datasetName = "xxx.xxx.xxx";
        String memberName = "xxx";
        DownloadParams params = new DownloadParams.Builder().build();

        // redefine connection object with no username and password specified
        connection = new ZosConnection(hostName, zosmfPort, "", "");

        // use jwtToken as cookie authentication
        downloadDsnMemberWithToken(connection, jwtToken, datasetName, memberName, params);
        // now use LtpaToken as cookie authentication
        downloadDsnMemberWithToken(connection, ltpaToken, datasetName, memberName, params);

        // redefine connection object with username and password specified
        connection = new ZosConnection(hostName, zosmfPort, userName, password);

        // perform the same request without token
        downloadDsnMember(connection, datasetName, memberName, params);

        // set the token in connection cookie field
        connection.setCookie(jwtToken);

        // use jwtToken as cookie token authentication
        downloadDsnMemberWithToken(connection, jwtToken, datasetName, memberName, params);
        // now use LtpaToken as cookie token authentication
        downloadDsnMemberWithToken(connection, ltpaToken, datasetName, memberName, params);

        // perform the same request without token authentication using the default basic authentication instead
        connection.setCookie(null);
        downloadDsnMember(connection, datasetName, memberName, params);

        // request to log out of server and delete jwtToken authentication token
        ZosmfLogout logout = new ZosmfLogout(connection);
        Response response = logout.logout(jwtToken);
        // display response
        System.out.println(response);
        // delete LtpaToken authentication token
        response = logout.logout(ltpaToken);
        // display response
        System.out.println(response);

        // use deleted token for authentication for next request
        connection.setCookie(jwtToken);
        // this request should fail
        try {
            downloadDsnMemberWithToken(connection, jwtToken, datasetName, memberName, params);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // disable token authentication for the next API call.
        connection.setCookie(null);
        // change z/OSMF user's password
        ZosmfPassword zosmfPassword = new ZosmfPassword(connection);
        PasswordParams passwordParams =
                new PasswordParams(connection.getUser(), connection.getPassword(), "xxx");
        response = zosmfPassword.changePassword(passwordParams);
        System.out.println(response);
    }

    /**
     * Download a dataset member using token cookie authentication.
     *
     * @param connection ZosConnection object
     * @param dsName     name of a dataset
     * @param memName    member name that exists within the specified dataset name
     * @param params     download parameters object
     * @author Leonid Baranov
     */
    private static void downloadDsnMember(ZosConnection connection, String dsName, String memName,
                                          DownloadParams params) {
        try (InputStream inputStream = new DsnGet(connection).get(String.format("%s(%s)", dsName, memName), params)) {
            System.out.println(getTextStreamData(inputStream));
        } catch (ZosmfRequestException e) {
            throw new RuntimeException(getByteResponseStatus(e));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Download a dataset member using basic authentication.
     *
     * @param connection ZosConnection object
     * @param dsName     name of a dataset
     * @param memName    member name that exists within the specified dataset name
     * @param params     download parameters object
     * @author Leonid Baranov
     */
    private static void downloadDsnMemberWithToken(ZosConnection connection, Cookie cookie, String dsName, String memName,
                                                   DownloadParams params) {
        // set the token in connection cookie field
        connection.setCookie(cookie);
        try (InputStream inputStream = new DsnGet(connection).get(String.format("%s(%s)", dsName, memName), params)) {
            System.out.println(getTextStreamData(inputStream));
        } catch (ZosmfRequestException e) {
            throw new RuntimeException(getByteResponseStatus(e));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convert exception message's byte stream of data into a string
     *
     * @param e ZosmfRequestException object
     * @return string value
     * @author Frank Giordano
     */
    private static String getByteResponseStatus(ZosmfRequestException e) {
        byte[] byteMsg = (byte[]) e.getResponse().getResponsePhrase().get();
        ByteArrayInputStream errorStream = new ByteArrayInputStream(byteMsg);
        String errMsg;
        try {
            errMsg = getTextStreamData(errorStream);
            if (errMsg.isEmpty()) {
                errMsg = e.getMessage();
            }
        } catch (IOException ex) {
            errMsg = "error processing response";
        }
        return errMsg;
    }

    /**
     * Convert a byte stream of data into a string
     *
     * @param inputStream byte stream od data
     * @return string value
     * @throws IOException error processing byte stream
     * @author Frank Giordano
     */
    private static String getTextStreamData(final InputStream inputStream) throws IOException {
        if (inputStream != null) {
            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer, "UTF8");
            inputStream.close();
            return writer.toString();
        }
        return null;
    }

}
