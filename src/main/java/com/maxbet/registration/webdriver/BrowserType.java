package com.maxbet.registration.webdriver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Atanas Kanchev
 *         Supported browsers
 */
@Component
public enum BrowserType {

    ANY("any"),
    FIREFOX("firefox"),
    FIREFOX_MOB("firefox_mob"),
    CHROME("chrome"),
    CHROME_MOB("chrome_mob"),
    OPERA("opera"),
    IE("ie"),
    SAFARI("safari"),
    PHANTOM("phantomjs");

    private final String name;

    /**
     * @param browser
     */
    BrowserType(@Value("${browser}")final String browser) {
        this.name = browser;
    }

    /**
     * @return browser
     */
    public String getBrowserName() {
        return name;
    }

    public boolean isChrome() {
    	return name.equals(CHROME) || name.equals(CHROME_MOB);
    }
    
    public boolean isFirefox() {
    	return name.equals(FIREFOX) || name.equals(FIREFOX_MOB);
    }
    
    public boolean isSafari() {
    	return name.equals(SAFARI);
    }
    
    public boolean isIE() {
    	return name.equals(IE);
    }
    
    public boolean isOpera() {
    	return name.equals(OPERA);
    }
    
    public boolean isPhantomJS() {
    	return name.equals(PHANTOM);
    }
    
    /**
     * Get enum instance
     *
     * @param browser String
     * @return BrowserType
     */
    public static BrowserType getBrowserEnum(String browser) {

        try {
            return BrowserType.valueOf(browser.toUpperCase());
        } catch (Exception ex) {
            return null;
        }
    }

}
