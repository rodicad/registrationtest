package com.maxbet.registration.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rodicad on 20/09/2017.
 */
public class Log {
    private static final Logger LOG = LoggerFactory.getLogger( Log.class );

    public static void startTestCase(String testcase) {
        LOG.info( "Starting test case: {}", testcase );
    }

    public static void endTestCase(String testcase) {
        LOG.info( "Finished test case: {}", testcase );
    }


}
