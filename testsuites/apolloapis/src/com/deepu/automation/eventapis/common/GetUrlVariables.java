package com.deepu.automation.eventapis.common;

/**
 * Created with IntelliJ IDEA.
 * User: rahulk
 * Date: 2/9/13
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class GetUrlVariables {

    private String hostName;
    private String basePath;
    private boolean sslEnabled;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public boolean isSslEnabled() {
        return sslEnabled;
    }

    public void setSslEnabled(boolean sslEnabled) {
        this.sslEnabled = sslEnabled;
    }
}
