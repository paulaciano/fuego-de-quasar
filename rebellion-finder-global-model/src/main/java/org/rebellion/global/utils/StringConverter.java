package org.rebellion.global.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringConverter {

    private static String EMPTY_STRING_TEXT = "THE_NOTHING";

    public static String getStringListAsString(List<String> list) {
        Collections.replaceAll(list, "", EMPTY_STRING_TEXT);
        return String.join(" ", list);
    }

    public static List<String> getBackTheStringList(String message) {
        List<String> response = Arrays.asList(message.split(" "));
        Collections.replaceAll(response, EMPTY_STRING_TEXT, "");
        return response;
    }
}
