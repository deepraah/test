package com.deepu.automation.eventapis.common;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rahulk
 * Date: 4/9/13
 * Time: 12:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class GenerateHeaders {

    private List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
    private String nonce;
    private String timestamp;

    public List<NameValuePair> addAllHeaders(String api, String message) {
        nonce = new GenerateNonce(32).toString().toLowerCase();
        timestamp = new GenerateTimeStamp().toString();
        nameValuePairList.add(new BasicNameValuePair(RequiredParams.APPLICATION_ID_NAME, RequiredParams.APPLICATION_ID_VALUE));
        nameValuePairList.add(new BasicNameValuePair(RequiredParams.NONCE_NAME, nonce));
        nameValuePairList.add(new BasicNameValuePair(RequiredParams.TIMESTAMP_NAME, timestamp));
        nameValuePairList.add(new BasicNameValuePair(RequiredParams.SIGNATURE_NAME, GenerateSignature.
                generateSignature(api, nonce, timestamp, message)));

        return nameValuePairList;
    }
}
