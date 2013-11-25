package com.deepu.automation.eventapis.common;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: rahulk
 * Date: 2/9/13
 * Time: 3:01 PM
 * To change this template use File | Settings | File Templates.
 */

public class ApiExecution {

    private static HttpClient httpClient;
    private HttpPost httpPost;
    private HttpGet httpGet;
    private StringEntity stringEntity;
    private GenerateHeaders generateHeaders;
    private String message;

    Logger logger = LoggerFactory.getLogger(ApiExecution.class);
    static String propertiesFileName = "properties.file.name";
    static Properties sysProp = System.getProperties();

    private static final String BUILD_PROPERTIES = sysProp.getProperty(propertiesFileName);

    public ApiExecution() {
        if (BUILD_PROPERTIES == null) logger.error("No properties file loaded");
    }

    public HttpClient createConnection() {
        httpClient = new DefaultHttpClient();
        return httpClient;
    }

    public HttpResponse sendPostrequest(String uri) {
        httpPost = new HttpPost(uri);
        try {
            return createConnection().execute(httpPost);
        } catch (IOException ex) {
            throw new RuntimeException("Unable to send POST request to the server ", ex);
        } finally {
            releaseConnection();
        }
    }

    public HttpResponse sendPostrequest(String api, String contentType) {
        httpPost = new HttpPost(new BuildUrl(BUILD_PROPERTIES, api).toString());
        generateHeaders = new GenerateHeaders();
        message = new BuildMessageBody(api).toString();
        try {
            stringEntity = new StringEntity(message);
            httpPost.setEntity(stringEntity);
            for (NameValuePair s : generateHeaders.addAllHeaders(api, message)) {
                System.out.println("Printing all headers : " + s.getName() + ":" + s.getValue());
                httpPost.addHeader(s.getName(), s.getValue());
            }
            httpPost.addHeader("Content-Type", contentType);
            return createConnection().execute(httpPost);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Unable to set message body for POST request", ex);
        } catch (IOException ex) {
            throw new RuntimeException("Unable to send POST request with message body to the server ", ex);
        } finally {
            releaseConnection();
        }
    }

    public HttpResponse sendGetRequest(String uri) {
        httpGet = new HttpGet(uri);
        try {
            return createConnection().execute(httpGet);
        } catch (IOException ex) {
            throw new RuntimeException("Unable to send GET request to the server ", ex);
        } finally {
            releaseConnection();
        }
    }

    public void releaseConnection() {
        httpClient.getConnectionManager().shutdown();
    }
}