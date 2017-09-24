package com.maxbet.registration;

import com.maxbet.registration.config.TestConfig;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by rodicad on 19/09/2017.
 */
@PropertySource( "classpath:src/test/resources/selenium.properties" )
@ContextConfiguration( classes = TestConfig.class )

public class Main {



    public static void main(String[] args) {


    }


}
