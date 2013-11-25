package com.deepu.automation.eventapis.common;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * *****************************
 * Author : Rahul Kumar
 * ******************************
 */

public class GenerateNonce {
    public static String NONCE;

    public GenerateNonce(int length) {
        NONCE = RandomStringUtils.randomAlphanumeric(length);
    }

    public String toString() {
        return NONCE;
    }
}
