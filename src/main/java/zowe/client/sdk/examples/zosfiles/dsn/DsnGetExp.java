package zowe.client.sdk.examples.zosfiles.dsn;

import org.apache.commons.io.IOUtils;
import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.examples.TstZosConnection;
import zowe.client.sdk.rest.exception.ZosmfRequestException;
import zowe.client.sdk.zosfiles.dsn.input.DownloadParams;
import zowe.client.sdk.zosfiles.dsn.methods.DsnGet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Class example to showcase retrieve dataset and member content functionality DsnGet class.
 *
 * @author Leonid Baranov
 * @author Frank Giordano
 * @version 2.0
 */
public class DsnGetExp extends TstZosConnection {

    /**
     * Main method defines z/OSMF host and user connection and other parameters needed to showcase
     * DsnGet class functionality.
     *
     * @param args for main not used
     * @author Leonid Baranov
     */
    public static void main(String[] args) {
        String datasetName = "xxx";
        String datasetSeqName = "xxx";
        String memberName = "xxx";
        DownloadParams params = new DownloadParams.Builder().build();
        ZosConnection connection = new ZosConnection(hostName, zosmfPort, userName, password);
        DsnGetExp.downloadDsnMember(connection, datasetName, memberName, params);
        DsnGetExp.downloadDsnSequential(connection, datasetSeqName, params);
    }

    /**
     * Download a dataset member.
     *
     * @param connection ZosConnection object
     * @param dsName     name of a dataset
     * @param memName    member name that exists within the specified dataset name
     * @param params     download parameters object
     * @author Leonid Baranov
     */
    public static void downloadDsnMember(ZosConnection connection, String dsName, String memName,
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
     * Download a sequential dataset.
     *
     * @param connection ZosConnection object
     * @param dsName     name of a sequential dataset
     * @param params     download parameters object
     * @author Frank Giordano
     */
    public static void downloadDsnSequential(ZosConnection connection, String dsName, DownloadParams params) {
        try (InputStream inputStream = new DsnGet(connection).get(dsName, params)) {
            System.out.println(getTextStreamData(inputStream));
        } catch (ZosmfRequestException e) {
            throw new RuntimeException(getByteResponseStatus(e));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getByteResponseStatus(ZosmfRequestException e) {
        final var byteMsg = (byte[]) e.getResponse().getResponsePhrase().get();
        final var errorStream = new ByteArrayInputStream(byteMsg);
        String errMsg;
        try {
            errMsg = getTextStreamData(errorStream);
        } catch (IOException ex) {
            errMsg = "error processing response";
        }
        return errMsg;
    }

    public static String getTextStreamData(final InputStream inputStream) throws IOException {
        if (inputStream != null) {
            final var writer = new StringWriter();
            IOUtils.copy(inputStream, writer, "UTF8");
            inputStream.close();
            return writer.toString();
        }
        return null;
    }

}
