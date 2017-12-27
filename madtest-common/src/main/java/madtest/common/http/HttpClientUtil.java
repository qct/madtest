package madtest.common.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qct on 2016/12/16.
 */
public class HttpClientUtil {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);


    /**
     * HTTP GET request.
     *
     * @param url url
     * @return response
     * @throws Exception Exception
     */
    public static String sendGet(String url) throws Exception {

        HttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);

        HttpResponse response = client.execute(request);

        logger.debug("\nSending 'GET' request to URL : " + url);
        logger.debug("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
            new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        rd.close();
        logger.debug(result.toString());
        return result.toString();
    }

    /**
     * HTTP POST request.
     *
     * @param url url
     * @param param param
     * @return response
     * @throws Exception Exception
     */
    public static String sendPost(String url, Map<String, Object> param) throws Exception {
        HttpPost post = new HttpPost(url);
        // add header
        post.setHeader("User-Agent", USER_AGENT);
        List<NameValuePair> urlParameters = new ArrayList<>();
        param.forEach((key, val) -> urlParameters
            .add(new BasicNameValuePair(key, val == null ? "" : val.toString())));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(post);
        logger.debug("\nSending 'POST' request to URL : " + url);
        logger.debug("Post parameters : " + post.getEntity());
        logger.debug("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
            new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        rd.close();
        logger.debug(result.toString());
        return result.toString();
    }


    /**
     * HTTP POST request.
     *
     * @param url url
     * @param param param
     * @param contentType contentType
     * @return response
     * @throws Exception Exception
     */
    public static String sendPost(String url, Map<String, Object> param, String contentType)
        throws Exception {
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Content-Type", contentType);
        List<NameValuePair> urlParameters = new ArrayList<>();
        param.forEach((key, val) -> urlParameters
            .add(new BasicNameValuePair(key, val == null ? "" : val.toString())));

        post.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));

        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(post);
        logger.debug("\nSending 'POST' request to URL : " + url);
        logger.debug("Post parameters : " + post.getEntity());
        logger.debug("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
            new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        rd.close();
        logger.debug(result.toString());
        return result.toString();
    }

    /**
     * send Post.
     *
     * @param url url
     * @param body raw body
     * @return response
     * @throws Exception Exception
     */
    public static String sendPost(String url, String body) throws Exception {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);
        StringEntity entity = new StringEntity(body, "UTF-8");
        post.setEntity(entity);

        HttpResponse response = client.execute(post);
        logger.debug("\nSending 'POST' request to URL : " + url);
        logger.debug("Post parameters : " + post.getEntity());
        logger.debug("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
            new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        rd.close();
        logger.debug(result.toString());
        return result.toString();
    }

    /**
     * post json.
     *
     * @param url url
     * @param param param
     * @return response
     */
    public static String postJson(String url, String param) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        // add header
        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Content-Type", "application/json");
        BufferedReader rd = null;
        try {
            post.setEntity(new StringEntity(param, "UTF-8"));
            CloseableHttpResponse response = client.execute(post);
            logger.debug("Sending 'POST' request to URL : " + url);
            logger.debug("Post parameters : " + param);
            logger.debug("Response Code : " + response.getStatusLine().getStatusCode());

            rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if (rd != null) {
                    rd.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * post Json With Https.
     *
     * @param url url
     * @param param param
     * @return response
     */
    public static String postJsonWithHttps(String url, String param) {
        HttpPost post = new HttpPost(url);
        // add header
        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Content-Type", "application/json");
        BufferedReader rd = null;
        try {
            CloseableHttpClient client = HttpClients
                .custom()
                .setSSLContext(
                    new SSLContextBuilder().loadTrustMaterial(null, (certificate, authType) -> true)
                        .build())
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build();
            post.setEntity(new StringEntity(param, "UTF-8"));
            CloseableHttpResponse response = client.execute(post);
            logger.debug("Sending 'POST' request to URL : " + url);
            logger.debug("Post parameters : " + param);
            logger.debug("Response Code : " + response.getStatusLine().getStatusCode());

            rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if (rd != null) {
                    rd.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * get Cookies.
     *
     * @param httpResponse response
     * @return cookies map
     */
    public static Map<String, String> getCookies(HttpResponse httpResponse) {
        Map<String, String> cookies = new HashMap<>();
        Header[] headers = httpResponse.getHeaders("Set-Cookie");
        if (headers == null) {
            return cookies;
        }

        for (Header header : headers) {
            String cookie = header.getValue();
            String[] cookieValues = cookie.split(";");
            for (String cook : cookieValues) {
                String[] keyPair = cook.split("=");
                String key = keyPair[0].trim();
                String value = keyPair.length > 1 ? keyPair[1].trim() : "";
                cookies.put(key, value);
            }
        }
        return cookies;
    }
}