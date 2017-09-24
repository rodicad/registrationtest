package com.maxbet.registration.webdriver;

import com.williamhill.whgtf.browsers.BrowserTypes;
import com.williamhill.whgtf.exceptions.CustomExceptions;
import com.williamhill.whgtf.utils.JVMArgs;
import com.williamhill.whgtf.webdriver.binaries.DriverBinaryDownloader;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author Atanas Kanchev
 *         <p/>
 *         WebDriver properties
 */
public class WebDriverProperties {

    private final static Logger logger = LogManager.getLogger( com.williamhill.whgtf.webdriver.WebDriverProperties.class);

    private static final int DEFAULT_IMPLICIT_WAIT = 5;
    private static final int DEFAULT_PAGE_LOAD_TIMEOUT = 30;

    private static final String REMOTE_BROWSER_VERSION_BY_DEFAULT = "ANY";
    
    //////////////
    // BROWSER //
    /////////////

    private String browserType;
    private String browserResolution;

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
    private static final String CHROME_PATH = BIN_ROOT.concat("chromedriver");
    private static final String PHANTOM_PATH = BIN_ROOT.concat("phantom");
    private static final String IE_PATH = BIN_ROOT.concat("IEDriverServer");

    // Selenium System property names
    private static final String CHROME_BINARY_SYS_PROP_NAME = "webdriver.chrome.driver";
    private static final String IE_BINARY_PROP_SYS_PROP_NAME = "webdriver.ie.driver";
    private static final String PHANTOM_BINARY_PROP_SYS_PROP_NAME = "webdriver.phantom.driver";

    // Prop file keys
    private static final String CHROME_BIN_PROP_KEY = "chrome.bin.version";
    private static final String IE_BIN_PROP_KEY = "ie.bin.version";
    private static final String PHANTOM_BIN_PROP_KEY = "phantom.bin.version";

    // JVM args - chrome & IE binaries version
    private String jvmChromeBinaryVersion;
    private String jvmIeBinaryVersion;

    ///////////////////
    // Mobile Props //
    //////////////////

    private String userAgent;

    ////////////////////
    // Property files //
    ////////////////////

    private static final Properties USER_AGENT_PROPERTIES = new Properties();
    private static final Properties SELENIUM_PROPERTIES = new Properties();
    private static final String SELENIUM_PROPS_FILE_PATH = "selenium/selenium.properties";
    private static final String USER_AGENTS_PROPS_FILE_PATH = "selenium/useragents.properties";

    //////////////////////
    // OS ARCHITECTURE //
    /////////////////////

    private final String endpointOSName;
    private final String endpointOSArch;
    private final String endpointIP;
    
    // Remote specific
    private boolean isGridExecution;
    private String platformName;
    private String browserVersion;
	private String gridHubUrl;
	private String hubUrl;

    ///////////////////
    // SCREEN SHOTS //
    //////////////////

	private static final String DEFAULT_SCREENSHOTS_DIR = "./screenshots/";
    private static final String[] SCREENSHOT_RESOLUTION_OPTIONS = {"high", "medium", "low"};
    private static final String DEFAULT_SCREENSHOT_RESOLUTION = SCREENSHOT_RESOLUTION_OPTIONS[0];
    
    private String screenshotResolution;

    public WebDriverProperties() {
    	
    	// browser specific
    	browserType = System.getProperty(JVMArgs.BROWSER_TYPE.getName());
        browserResolution = System.getProperty(JVMArgs.BROWSER_RESOLUTION.getName());
        configureTimeouts();
        userAgent = System.getProperty(JVMArgs.USER_AGENT.getName());
        
        // driver specific
        jvmChromeBinaryVersion = System.getProperty(JVMArgs.CHROME_BINARY_VERSION.getName());
        jvmIeBinaryVersion = System.getProperty(JVMArgs.IE_BINARY_VERSION.getName());
        
        // Local specific
        endpointOSName = System.getProperties().getProperty("os.name");
        endpointOSArch = System.getProperties().getProperty("os.arch");
        endpointIP = setLocalExecutionIP();
        
        // Remote specific
        isGridExecution = Boolean.valueOf(System.getProperty(JVMArgs.IS_GRID_EXECUTION.getName()));
        browserVersion = System.getProperty(JVMArgs.BROWSER_VERSION.getName());
        platformName = System.getProperty(JVMArgs.PLATFORM_NAME.getName());
        hubUrl = System.getProperty(JVMArgs.HUB_URL.getName());
        
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

    static {

        InputStream inputStream = com.williamhill.whgtf.webdriver.WebDriverProperties.class.getClassLoader().getResourceAsStream(USER_AGENTS_PROPS_FILE_PATH);

        if (inputStream == null)
            logger.debug("useragents.properties file not found in path " + USER_AGENTS_PROPS_FILE_PATH);
        else {
            try {
                USER_AGENT_PROPERTIES.load(inputStream);
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
     * Sets the webdriver.chrome.driver system property with the path to the
     * respective chromeDriver according to the current OS Architecture
     *
     * @param binaryVersion String
     * @return chromeBinaryPath String
     */
    public String setChromeBinaryProperty(String binaryVersion) {

        String version = setChromeBinVersion(binaryVersion);
        String path = setChromeBinPath(version);

        System.setProperty(CHROME_BINARY_SYS_PROP_NAME, path);

        return path;
    }

    /**
     * Configure Chrome binary version based on JVM arg,
     * property file parametrisation or method param
     *
     * @param binVersion String
     * @return version String
     */
    public String setChromeBinVersion(final String binVersion) {

        String version;

        if (this.jvmChromeBinaryVersion != null) {
            logger.debug("Using JVM arg to configure the binary version");
            version = this.jvmChromeBinaryVersion;
        } else if (binVersion == null || binVersion.isEmpty()) {
            logger.debug("Using selenium.properties file to configure the binary version");
            version = getSeleniumPropertiesPropFile().getProperty(CHROME_BIN_PROP_KEY);
            if (version == null)
                throw new NullPointerException(CHROME_BIN_PROP_KEY + " key is not set in " + BIN_ROOT
                        + "selenium.properties - ex. " + CHROME_BIN_PROP_KEY + "=2.13");
        } else
            version = binVersion.trim();

        logger.debug("Configured Chrome binary version: " + version);

        return version;
    }

    /**
     * Set Chrome binary path based on current system architecture
     *
     * @param version String
     * @return path String
     */
    public String setChromeBinPath(final String version) {

        String path = null;
        if(getSeleniumPropertiesPropFile().getProperty("chromeDriverPath") != null && !getSeleniumPropertiesPropFile().getProperty("chromeDriverPath").isEmpty()){
           path = getSeleniumPropertiesPropFile().getProperty("chromeDriverPath");
        }else {


            if (version == null || version.isEmpty())
                throw new IllegalArgumentException("Null or empty argument supplied!");
            else {
                try {
                    path = new File(new File(CHROME_PATH).listFiles()[0] + "/" + version).listFiles()[0].getPath();
                } catch (NullPointerException e) {
                    for (File file : new File(BIN_ROOT).listFiles()) {
                        if (file.isDirectory()) {
                            try {
                                FileUtils.deleteDirectory(file);
                            } catch (IOException e1) {

                            }
                        }
                    }
                    DriverBinaryDownloader.downloadDrivers(getCurrentOs());
                    path = new File(new File(CHROME_PATH).listFiles()[0] + "/" + version).listFiles()[0].getPath();
                }
                logger.debug("Configured Chrome binary path: " + path);
            }
        }
        return path;
    }

    /**
     * Sets the ie.driver.version Selenium system property with the path to the
     * respective IE driver according to the current OS Architecture
     *
     * @param binaryVersion IE binary version
     * @return ieBinaryPath
     */
    public String setIEBinaryProperty(String binaryVersion) {

        String version = setIEBinVersion(binaryVersion);
        String path = setIEBinPath(version);

        System.setProperty(IE_BINARY_PROP_SYS_PROP_NAME, path);

        return path;
    }

    /**
     * Set IE binary version
     *
     * @param binaryVersion String
     * @return version String
     */
    public String setIEBinVersion(String binaryVersion) {

        String version;
        String JVM_IE_BINARY_VERSION = System.getProperty(JVMArgs.IE_BINARY_VERSION.getName());

        if (JVM_IE_BINARY_VERSION != null) {
            logger.debug("Using JVM arg to configure the binary version");
            version = JVM_IE_BINARY_VERSION;
        } else if (binaryVersion == null || binaryVersion.isEmpty()) {
            logger.debug("Using selenium.properties file to configure the binary version");
            version = getSeleniumPropertiesPropFile().getProperty(IE_BIN_PROP_KEY);
            if (version == null)
                throw new NullPointerException(IE_BIN_PROP_KEY + " key is not set in " + BIN_ROOT
                        + "selenium.properties - ex. " + IE_BIN_PROP_KEY + "=2.44.0");
        } else
            version = binaryVersion.trim();

        logger.debug("Configured IE binary version: " + version);

        return version;
    }

    /**
     * Set IE Binary path
     *
     * @param version String
     * @return path String
     */
    public String setIEBinPath(final String version) {

        String path = new File(IE_PATH).listFiles()[0]+"/" +version + "/IEDriverServer.exe";
        logger.debug("Configured IE binary path: " + path);
        return path;
    }

    /**
     * Set WebDriver System property
     *
     * @param propName  name
     * @param propValue value
     */
    @Deprecated
    public void setWebDriverSystemProperty(final String propName, final String propValue) {

        if (propName == null || propValue == null)
            throw new NullPointerException("Null argument is not permitted");
        else if (propName.isEmpty() || propValue.isEmpty())
            throw new IllegalArgumentException("Empty argument is not permitted");
        else
            System.setProperty(propName, propValue);
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
     * Set userAgent variable
     *
     * @param userAgent String
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * If the JVM arg user.agent is supplied and such key exists in the prop file then return its value
     * Else return the JVM arg as user agent value
     *
     * @return user agent
     */
    public String configureUserAgent() {

        String userAgent;

        if (this.userAgent != null && USER_AGENT_PROPERTIES.getProperty(this.userAgent) != null)
            userAgent = USER_AGENT_PROPERTIES.getProperty(this.userAgent).trim();
        else
            userAgent = this.userAgent;

        if (userAgent == null) throw new CustomExceptions.NullArgumentException("Null argument userAgent");

        logger.debug("Configured user agent: " + userAgent);

        return userAgent;
    }
    
    /**
     * Execution IP address
     *
     * @return executionIP String
     */
    public String setLocalExecutionIP() {

        String ip = null;

        try {
            InetAddress IP = InetAddress.getLocalHost();
            ip = IP.getHostAddress();
        } catch (UnknownHostException e) {
            logger.error(e);
        }

        return ip;

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
        
        if(Arrays.asList(SCREENSHOT_RESOLUTION_OPTIONS).contains(resolution.toLowerCase())) {
        	screenshotResolution = resolution.toLowerCase();
        	return;
        }else {
        	screenshotResolution = DEFAULT_SCREENSHOT_RESOLUTION;
        }
        
        logger.debug("Configuring screenshot resolution to " + screenshotResolution);        
    }
    
    /////////////
    // GETTERS //
    /////////////

    /**
     * Method that gives access to useragents.property file
     *
     * @return USER_AGENT_PROPERTIES Properties
     */
    public Properties getUserAgentProperty() {
        return USER_AGENT_PROPERTIES;
    }

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
            	logger.error("Error when trying to create directory "+ screenshotsDir, e);
            	logger.error("So using "+ DEFAULT_SCREENSHOTS_DIR +" instead of");
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
     * Method that gives access to the value of the runtime JVM arg -DchromeDriverVersion
     *
     * @return jvmChromeBinaryVersion String
     */
    protected String getJvmChromeBinaryVersion() {
        return jvmChromeBinaryVersion;
    }

    /**
     * Method that gives access to the value of the runtime JVM arg -DieDriverVersion
     *
     * @return jvmIeBinaryVersion String
     */
    public String getIEBinaryVersion() {
        return jvmIeBinaryVersion;
    }
   
    /**
     * @return endpointOSName String
     */
    public String getCurrentOs() {
        return endpointOSName;
    }

    /**
     * @return endpointOSArch String
     */
    public String getCurrentArchitecture() {
        return endpointOSArch;
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
     * Get user agent runtime JVM arg
     *
     * @return userAgent String
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Get the browser resolution JVM arg
     *
     * @return browserResolution String
     */
    public String getBrowserResolution() {
        return browserResolution;
    }
    
    /**
     * Get dimension (width and height) by parsing the supplied argument
     *
     * @return Integer[] ex. 1024x768
     * @throws Exception 
     */
    public Integer[] getBrowserResolutionArray() throws Exception {

    	if(browserResolution == null || browserResolution.isEmpty()) {
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
                logger.error("Error parsing dimensions from "+ browserResolution, e);
            }
            return dimensions;
        }
    }

    /**
     * Get local execution IP address
     *
     * @return endpointIP String
     */
    public String getEndpointIP() {
        return endpointIP;
    }

    public boolean isGridExecution() {
    	return isGridExecution;
    }
    
    public void setGridExecution(final boolean isGridExecution) {
    	this.isGridExecution = isGridExecution;
    }
    
    public String getRemoteBrowserName() {
    	return browserType;
    }
    
    public String getRemoteBrowserVersion() {
    	return browserVersion;
    }
    
	public void setRemoteBrowserVersion(final String browserVersion) {
		if(browserVersion == null) {
			this.browserVersion = REMOTE_BROWSER_VERSION_BY_DEFAULT;
			return;
		}
		this.browserVersion = browserVersion;
	}
    
    public String getRemotePlatformName() {
    	return platformName;
    }
    
	public void setRemotePlatformName(final String platformName) {
		this.platformName = platformName;
	}

	public String getGridHubUrl() {
		return gridHubUrl;
	}

	public void setGridHubUrl(String gridHubUrl) {
		// TODO check if valid URL
		this.gridHubUrl = gridHubUrl;		
	}

	public String getHubUrl() {
		return hubUrl;
	}

	@Override
	public String toString() {
		return "WebDriverProperties [browserType=" + browserType + ", browserResolution="
				+ browserResolution + ", implicitlyWait=" + implicitlyWait + ", pageLoadTimeout="
				+ pageLoadTimeout + ", jvmChromeBinaryVersion=" + jvmChromeBinaryVersion
				+ ", jvmIeBinaryVersion=" + jvmIeBinaryVersion + ", userAgent=" + userAgent
				+ ", endpointOSName=" + endpointOSName + ", endpointOSArch=" + endpointOSArch
				+ ", endpointIP=" + endpointIP + ", isGridExecution=" + isGridExecution
				+ ", platformName=" + platformName + ", browserVersion=" + browserVersion
				+ ", gridHubUrl=" + gridHubUrl + ", hubUrl=" + hubUrl + "]";
	}
	
}