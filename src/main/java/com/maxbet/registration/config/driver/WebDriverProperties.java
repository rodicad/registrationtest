package com.maxbet.registration.config.driver;

import com.williamhill.whgtf.browsers.BrowserTypes;
import com.williamhill.whgtf.utils.JVMArgs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

@Component
@PropertySource("classpath:selenium/selenium.properties")
public class WebDriverProperties {

    private final static Logger logger = LogManager.getLogger(com.williamhill.whgtf.webdriver.WebDriverProperties.class);

    private static final int DEFAULT_IMPLICIT_WAIT = 5;
    private static final int DEFAULT_PAGE_LOAD_TIMEOUT = 30;

//    private static final String REMOTE_BROWSER_VERSION_BY_DEFAULT = "ANY";

    //////////////
    // BROWSER //
    /////////////



    private String browserType;
    private Dimension browserResolution;

    ////////////
    // WAITS //
    ///////////

    private int implicitlyWait;
    private int pageLoadTimeout;

    ////////////////////////
    // SELENIUM BINARIES //
    ///////////////////////

    // Binary files path
    private static final String BIN_ROOT = "src/test/resources/selenium/";
    private static final String CHROME_PATH = BIN_ROOT.concat("chromedriver/chromedriver.exe");
    private static final String IE_PATH = BIN_ROOT.concat("IEDriverServer");

    // Selenium System property names
    private static final String CHROME_BINARY_SYS_PROP_NAME = "webdriver.chrome.driver";
    private static final String IE_BINARY_PROP_SYS_PROP_NAME = "webdriver.ie.driver";


    ////////////////////
    // Property files //
    ////////////////////

    private static final Properties SELENIUM_PROPERTIES = new Properties();
    private static final String SELENIUM_PROPS_FILE_PATH = "selenium/selenium.properties";

//    //////////////////////
//    // OS ARCHITECTURE //
//    /////////////////////
//
//    private final String endpointOSName;
//    private final String endpointOSArch;
//    private final String endpointIP;
//
//    // Remote specific
//    private boolean isGridExecution;
//    private String platformName;
//    private String browserVersion;
//	private String gridHubUrl;
//	private String hubUrl;

    ///////////////////
    // SCREEN SHOTS //
    //////////////////

    private static final String DEFAULT_SCREENSHOTS_DIR = "./screenshots/";
    private static final String[] SCREENSHOT_RESOLUTION_OPTIONS = {"high", "medium", "low"};
    private static final String DEFAULT_SCREENSHOT_RESOLUTION = SCREENSHOT_RESOLUTION_OPTIONS[0];

    private String screenshotResolution;

    @Autowired
    public WebDriverProperties(
            @Value("${browserType}") final String browserType,
            @Value("${browser.resolution}") final String browserResolution) throws Exception {

        // browser specific
        this.browserType = browserType;
        Integer[] resolutionArray = getBrowserResolutionArray(browserResolution);
        this.browserResolution =  new Dimension(resolutionArray[0], resolutionArray[1]);

        configureTimeouts();
        configureScreenshotResolution();
    }

    /////////////////////////
    // STATIC INITIALIZERS //
    ////////////////////////

    static {

        InputStream inputStream = com.williamhill.whgtf.webdriver.WebDriverProperties.class.getClassLoader().getResourceAsStream(SELENIUM_PROPS_FILE_PATH);

        if (inputStream == null)
            logger.debug("selenium.properties file not found in path " + SELENIUM_PROPS_FILE_PATH);
        else {
            try {
                SELENIUM_PROPERTIES.load(inputStream);
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }


    /////////////
    // SETTERS //
    /////////////

    /**
     * Set browserType
     *
     * @param browser String
     */
    public void setBrowserType(BrowserTypes browser) {
        browserType = browser.getBrowserName();
    }




    /**
     * Set Chrome binary path based on current system architecture
     *
     * @return path String
     */
    public void setChromeBinaryProperty() {
        System.setProperty(CHROME_BINARY_SYS_PROP_NAME, CHROME_PATH);
    }

    /**
     * Sets the ie.driver.version Selenium system property with the path to the
     * respective IE driver according to the current OS Architecture
     *
     * @return ieBinaryPath
     */
    public void setIEBinaryProperty() {

        System.setProperty(IE_BINARY_PROP_SYS_PROP_NAME, IE_PATH);

    }


    /**
     * Configure implicitlyWait and pageLoadTimeout using selenium/selenium.properties props file
     * If no properties are defined in selenium/selenium.properties file use
     * {@link com.williamhill.whgtf.webdriver.WebDriverProperties#DEFAULT_IMPLICIT_WAIT} and {@link com.williamhill.whgtf.webdriver.WebDriverProperties#DEFAULT_PAGE_LOAD_TIMEOUT}
     */
    public void configureTimeouts() {

        String implicitlyWaitProp = SELENIUM_PROPERTIES.getProperty("wait.time.elements");
        if (implicitlyWaitProp == null || implicitlyWaitProp.equals(""))
            implicitlyWait = DEFAULT_IMPLICIT_WAIT;
        else
            implicitlyWait = Integer.parseInt(implicitlyWaitProp);

        String pageLoadTimeoutProp = SELENIUM_PROPERTIES.getProperty("page.timeout");
        if (pageLoadTimeoutProp == null || pageLoadTimeoutProp.equals(""))
            pageLoadTimeout = DEFAULT_PAGE_LOAD_TIMEOUT;
        else
            pageLoadTimeout = Integer.parseInt(pageLoadTimeoutProp);

        logger.debug("Configuring implicitly wait to " + implicitlyWait + " sec.");
        logger.debug("Configuring page load timeout to " + pageLoadTimeout + " sec.");
    }

    /**
     * Set implicitlyWait
     *
     * @param wait seconds
     */
    public void setImplicitlyWait(int wait) {
        implicitlyWait = wait;
    }

    /**
     * Set pageLoadTimeout
     *
     * @param timeout seconds
     */
    public void setPageLoadTimeout(int timeout) {
        pageLoadTimeout = timeout;
    }


    /**
     * Configure screenshot resolution
     * If no properties are defined in selenium/selenium.properties file use
     * {@link com.williamhill.whgtf.webdriver.WebDriverProperties#DEFAULT_SCREENSHOT_RESOLUTION}
     */
    public void configureScreenshotResolution() {
        String resolution = SELENIUM_PROPERTIES.getProperty("screenshot.resolution", "");

        if (resolution.isEmpty()) {
            screenshotResolution = DEFAULT_SCREENSHOT_RESOLUTION;
            return;
        }

        if (Arrays.asList(SCREENSHOT_RESOLUTION_OPTIONS).contains(resolution.toLowerCase())) {
            screenshotResolution = resolution.toLowerCase();
            return;
        } else {
            screenshotResolution = DEFAULT_SCREENSHOT_RESOLUTION;
        }

        logger.debug("Configuring screenshot resolution to " + screenshotResolution);
    }

    /////////////
    // GETTERS //
    /////////////


    /**
     * Method that gives access to selenium.version property file
     *
     * @return SELENIUM_PROPERTIES Properties
     */
    public static Properties getSeleniumPropertiesPropFile() {
        return SELENIUM_PROPERTIES;
    }

    /**
     * Method to return the value of the runtime JVM arg -Dbrowser
     *
     * @return browserType
     */
    public String getBrowserType() {
        return browserType;
    }

    /**
     * Method to return the value of SCREENSHOTS_DIR
     *
     * @return SCREENSHOTS_DIR or DEFAULT_SCREENSHOTS_DIR if so
     */
    public String getScreenshotsDir() {

        String screenshotsDir = SELENIUM_PROPERTIES.getProperty(JVMArgs.SCREEN_SHOTS_DIR.getName(), DEFAULT_SCREENSHOTS_DIR);

        File outputFolder = new File(screenshotsDir);
        if (!outputFolder.exists()) {
            // if not, trying to create
            try {
                outputFolder.mkdir();
            } catch (Exception e) {
                logger.error("Error when trying to create directory " + screenshotsDir, e);
                logger.error("So using " + DEFAULT_SCREENSHOTS_DIR + " instead of");
                return DEFAULT_SCREENSHOTS_DIR;
            }
        }
        return screenshotsDir;
    }

    /**
     * @return screenshotResolution String
     */
    public String getScreenshotResolution() {
        return screenshotResolution;
    }


    /**
     * @return implicitlyWait Integer
     */
    public int getImplicitlyWait() {
        return implicitlyWait;
    }

    /**
     * @return pageLoadTimeout Integer
     */
    public int getPageLoadTimeout() {
        return pageLoadTimeout;
    }

    /**
     * @return DEFAULT_IMPLICIT_WAIT Integer
     */
    public static int getDefaultImplicitWait() {
        return DEFAULT_IMPLICIT_WAIT;
    }

    /**
     * @return DEFAULT_PAGE_LOAD_TIMEOUT Integer
     */
    public static int getDefaultPageLoadTimeout() {
        return DEFAULT_PAGE_LOAD_TIMEOUT;
    }


    /**
     * Get the browser resolution
     *
     * @return browserResolution Dimension
     */
    public Dimension getBrowserResolution() {
        return browserResolution;
    }

    /**
     * Get dimension (width and height) by parsing the supplied argument
     *
     * @return Integer[] ex. 1024x768
     * @throws Exception
     */
    public Integer[] getBrowserResolutionArray(String browserResolution) throws Exception {
        if (browserResolution == null || browserResolution.isEmpty()) {
            throw new Exception("Browser resolution property is null or empty");
        }

        if (!browserResolution.contains("x"))
            throw new RuntimeException("Invalid resolution format for " + browserResolution);
        else {
            Integer[] dimensions = new Integer[2];

            try {
                String[] dims = browserResolution.split("x");
                dimensions = new Integer[]{Integer.valueOf(dims[0]), Integer.valueOf(dims[1])};
            } catch (NumberFormatException e) {
                logger.error("Error parsing dimensions from " + browserResolution, e);
            }
            return dimensions;
        }
    }


    @Override
    public String toString() {
        return "WebDriverProperties [browserType=" + browserType + ", browserResolution="
                + browserResolution + ", implicitlyWait=" + implicitlyWait + ", pageLoadTimeout="
                + pageLoadTimeout + "]";
    }

}