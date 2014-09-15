package pl.jug.torun.meetup.api;

import org.apache.http.client.utils.URIBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.Map;

public class MeetupPath {

    final private String baseUrl = "https://api.meetup.com";
    final private String key = "1e413cad6b174f1a4475362e1d22";

    public String getUrl(String path) throws URISyntaxException {
        return getUrl(path, Collections.<String, String>emptyMap());
    }

    public String getUrl(String path, Map<String, String> params) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(baseUrl);
        uriBuilder.setPath(String.format("/%s", path));
        uriBuilder.addParameter("key", key);

        params.entrySet().stream().forEach((entry) -> {
            uriBuilder.addParameter(entry.getKey(), entry.getValue());
        });

        try {
            return URLDecoder.decode(uriBuilder.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
