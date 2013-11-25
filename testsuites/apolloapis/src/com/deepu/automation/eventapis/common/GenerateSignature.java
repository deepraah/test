package com.deepu.automation.eventapis.common;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * *****************************
 * Author : Rahul Kumar
 * ******************************
 */

public class GenerateSignature {

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private static final String HEADERS_SEPARATOR = "|";
    private static final String HEADER_NAME_SEPARATOR = ":";

    private static Mac mac;

    public static void main(String[] args) {
        System.out.println(generateSignature(ApiList.PROVISIONING_EVENT_GETEVENTUPDATE, new GenerateNonce(32).toString().toLowerCase(), new GenerateTimeStamp().toString(), "NoSuchData"));
    }

    public static String generateSignature(String api, String nonce, String timestamp, String message) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(RequiredParams.API_SECRET_KEY.getBytes(), HMAC_SHA1_ALGORITHM);
        try {
            mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("No such algorithm SHA1 ", ex);
        }
        try {
            mac.init(secretKeySpec);
        } catch (InvalidKeyException ex) {
            throw new RuntimeException("No such key exists ", ex);
        }

        byte[] finalSign = mac.doFinal(createSignatureParam(api, nonce, timestamp, message).getBytes());
        return Hex.encodeHexString(finalSign);
    }

    public static String createSignatureParam(String api, String nonce, String timestamp, String message) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(api).append(HEADERS_SEPARATOR).append(RequiredParams.APPLICATION_ID_NAME).
                append(HEADER_NAME_SEPARATOR).append(RequiredParams.APPLICATION_ID_NAME).append(HEADER_NAME_SEPARATOR).
                append(RequiredParams.NONCE_NAME).append(HEADER_NAME_SEPARATOR).append(nonce).append(HEADER_NAME_SEPARATOR).
                append(RequiredParams.TIMESTAMP_NAME).append(HEADER_NAME_SEPARATOR).append(timestamp).append(HEADERS_SEPARATOR).
                append(message);

        return stringBuilder.toString();
    }
}