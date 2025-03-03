package zowe.client.sdk.examples.utility;

import zowe.client.sdk.rest.Response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class containing helper method(s).
 *
 * @author Frank Giordano
 * @version 3.0
 */
public class Util {

    /**
     * Extract response phrase string value if any from Response object.
     *
     * @param response object
     * @return string value
     * @author Frank Giordano
     */
    public static String getResponsePhrase(Response response) {
        if (response == null || response.getResponsePhrase().isEmpty()) {
            return null;
        }

        final String responsePhrase = response.getResponsePhrase().get().toString();
        final String pattern = "\\{}";
        final Pattern regex = Pattern.compile(pattern);
        final Matcher matcher = regex.matcher(responsePhrase);

        if (matcher.find()) {
            return response.getStatusText().orElse(null);
        } else {
            return response.getResponsePhrase().orElse(null).toString();
        }
    }

}
