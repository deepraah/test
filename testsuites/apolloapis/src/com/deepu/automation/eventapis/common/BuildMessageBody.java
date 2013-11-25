package com.deepu.automation.eventapis.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * *****************************
 * Author : Rahul Kumar
 * ******************************
 */

public class BuildMessageBody {

    BufferedReader bufferedReader;
    StringBuilder message;

    public BuildMessageBody(String api) {
        String messageFileName = "message.file.location";
        Properties sysProp = System.getProperties();

        String MESSAGE_FILE = getFileName(api, sysProp.getProperty(messageFileName));

        try {
            String readLine;
            bufferedReader = new BufferedReader(new FileReader(MESSAGE_FILE));
            message = new StringBuilder();
            while ((readLine = bufferedReader.readLine()) != null) {
                message.append(readLine);
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Message file to be sent is not found ", ex);
        } catch (IOException ex) {
            throw new RuntimeException("Unable to read message file ", ex);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw new RuntimeException("Unable to close message file ", ex);
                }
            }
        }

    }

    private String getFileName(String api, String fileLocation) {
        String fileName = api;
        if (!fileLocation.endsWith("/") && fileLocation.length() != 0)
            fileLocation = fileLocation + "/";
        if (api.contains(".")) {
            String[] fileNameArray = api.split("\\.");
            fileName = fileNameArray[2];
        }
        fileLocation = fileLocation + "src/com/deepu/automation/eventapis/resources/messages/";
        return fileLocation + fileName.toLowerCase() + ".json";
    }

    public String toString() {
        return message.toString();
    }
}