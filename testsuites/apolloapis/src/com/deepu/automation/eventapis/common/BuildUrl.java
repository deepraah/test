package com.deepu.automation.eventapis.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: rahulk
 * Date: 2/9/13
 * Time: 3:49 PM
 * To change this template use File | Settings | File Templates.
 */

public class BuildUrl {

    private static final String HOSTNAME = "hostname";
    private static final String BASEPATH = "basepath";
    private static final String SSLENABLED = "sslenabled";

    private String finalUrl;

    private StringBuilder urlBuilder;
    private Properties properties;
    private GetUrlVariables getUrlVariables;

    public BuildUrl(String fileName, String api) {
        urlBuilder = new StringBuilder();
        properties = new Properties();
        getUrlVariables = new GetUrlVariables();

        try {
            properties.load(new FileInputStream(fileName));
        } catch (IOException ex) {
            throw new RuntimeException("No properties file provided ", ex);
        }
        String HTTP_REQUEST_SCHEME = !(Boolean.parseBoolean(properties.getProperty(SSLENABLED))) ? "http://" : "https://";
        getUrlVariables.setHostName(properties.getProperty(HOSTNAME));
        getUrlVariables.setBasePath(properties.getProperty(BASEPATH));

        if (!properties.getProperty(HOSTNAME).isEmpty())
            finalUrl = String.valueOf(urlBuilder.append(HTTP_REQUEST_SCHEME).append(getUrlVariables.getHostName()).
                    append("/").append(getUrlVariables.getBasePath()).append("/").append(api));
    }

    public BuildUrl(String fileName, String api, String queryParam) {
        urlBuilder = new StringBuilder();
        properties = new Properties();
        getUrlVariables = new GetUrlVariables();

        try {
            properties.load(new FileInputStream(fileName));
        } catch (IOException ex) {
            throw new RuntimeException("No properties file provided ", ex);
        }
        String HTTP_REQUEST_SCHEME = !(Boolean.parseBoolean(properties.getProperty(SSLENABLED))) ? "http://" : "https://";
        getUrlVariables.setHostName(properties.getProperty(HOSTNAME));
        getUrlVariables.setBasePath(properties.getProperty(BASEPATH));

        if (!properties.getProperty(HOSTNAME).isEmpty())
            finalUrl = String.valueOf(urlBuilder.append(HTTP_REQUEST_SCHEME).append(getUrlVariables.getHostName()).
                    append("/").append(getUrlVariables.getBasePath()).append("/").append(api).append("?").append(queryParam));
    }

    public String toString() {
        return finalUrl;
    }

}