package com.maxbet.registration.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by Rodicad on 26/09/2017.
 */
public class StringUtils {
    public static String generateRandomString (int count) {
        return RandomStringUtils.randomAlphabetic(count);
    }

    public static String generateRandomNumberAsString (int count) {
        return RandomStringUtils.randomNumeric(count);
    }


}
