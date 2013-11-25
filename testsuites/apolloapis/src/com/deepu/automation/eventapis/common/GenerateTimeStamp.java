package com.deepu.automation.eventapis.common;

import java.util.Date;

/**
 * *****************************
 * Author : Rahul Kumar
 * ******************************
 */

public class GenerateTimeStamp {
    public static String timeStamp;

    public GenerateTimeStamp() {
        Date date = new Date();
        timeStamp = String.valueOf(date.getTime());
    }

    public String toString() {
        return timeStamp;
    }
}
