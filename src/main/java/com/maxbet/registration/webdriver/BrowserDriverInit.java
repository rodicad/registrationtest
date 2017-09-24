package com.maxbet.registration.webdriver;

import com.williamhill.whgtf.browsers.BrowserTypes;
import com.williamhill.whgtf.browsers.firefox.FirefoxBrowser;
import com.williamhill.whgtf.exceptions.CustomExceptions;
import com.williamhill.whgtf.selenium.grid.SeleniumGridHub;
import com.williamhill.whgtf.webdriver.MultiThreadedDriverFactory;
import com.williamhill.whgtf.webdriver.WebDriverProperties;
import com.williamhill.whgtf.webdriver.binaries.DriverBinaryDownloader;
import com.williamhill.whgtf.webdriver.caps.DesiredCapabilitiesFactory;
import com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities;
import com.williamhill.whgtf.webdriver.drivers.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public final class BrowserDriverInit {

    private final static Logger logger = LogManager.getLogger( com.williamhill.whgtf.webdriver.BrowserDriverInit.class);
	
    private WebDriver driverOut;
    private WebDriverProperties webDriverProperties;
    private MultiThreadedDriverFactory multiThreadedDriverFactory;
    private DesiredCapabilitiesFactory capsFactory;

    private Proxy proxy;
	private ChromeOptions chromeOptions;
	private Map<String, Object> chromeOptionsMap;
	private FirefoxProfile firefoxProfile;

    /**
     * Default constructor
     */
    public BrowserDriverInit() {

        System.setProperty("wdm.targetPath", "src/test/resources/selenium");
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "error");
        System.setProperty("wdm.gitHubTokenName", "WHToken");
        System.setProperty("wdm.gitHubTokenSecret", "4664616dc1790bc979d56f2c989686d1ed62df6c");

        webDriverProperties = new WebDriverProperties();
        multiThreadedDriverFactory = new MultiThreadedDriverFactory();
        capsFactory = new DesiredCapabilitiesFactory(webDriverProperties);
    }
    
//    /**
//     * Get WebDriverProperties
//     *
//     * @return webDriverProperties instance
//     */
//    public WebDriverProperties getWebDriverProperties() {
//        return webDriverProperties;
//    }

    /**
     * Get MultiThreadedDriverFactory
     *
     * @return multiThreadedDriverFactory instance
     */
    public MultiThreadedDriverFactory getMultiThreadedDriverFactory() {
        return multiThreadedDriverFactory;
    }

//    /**
//     * Get DesiredCapabilitiesFactory
//     *
//     * @return capsFactory instance
//     */
//    public DesiredCapabilitiesFactory getDesiredCapabilitiesFactory() {
//        return capsFactory;
//    }

    /**
     * Get the generated driver
     *
     * @return WebDriver instance
     */
    public WebDriver getDriverOut() {
        return driverOut;
    }
    
    public String getBrowserType() {
    	return webDriverProperties.getBrowserType();
    }
    
    public void setBrowserType(final String type) {
    	setBrowserType(BrowserTypes.getBrowserEnum(type));
    }

    public void setBrowserType(final BrowserTypes type) {
    	webDriverProperties.setBrowserType(type);
    }

//    public getCustomCapabilities() {
//    	
//    }
    
	public void setCustomCapabilities(HashMap<String, Object> caps) {
		// TODO Auto-generated method stub
	}
    
    public ChromeOptions getCustomChromeOptions() {
    	return chromeOptions;
    }
    
	public void setCustomChromeOptions(final ChromeOptions chromeOptions) {
		this.chromeOptions = chromeOptions;
	}
	
	public void setCustomChromeOptions(final Map<String, Object> mapOptions) {
		this.chromeOptionsMap = mapOptions;
	}
	
	public FirefoxProfile getCustomFirefoxProfile() {
		return firefoxProfile;
	}
	
	public void setFirefoxProfileFromSystem(final String profileName) throws Exception {
		if(profileName == null) {
			throw new Exception("Firefox Profile name is null");
		}
		
		FirefoxProfile profile = FirefoxBrowser.ProfileManager.readProfileFromSystem(profileName);
		setCustomFirefoxProfile(profile);
	}
	
	public void setCustomFirefoxProfile(final FirefoxProfile profile) throws Exception {
		if(profile == null) {
			throw new Exception("Firefox Profile is null");
		}
		this.firefoxProfile = profile;
	}
	
    public String getUserAgent() {
    	return webDriverProperties.getUserAgent();
    }
    
    public void setUserAgent(final String userAgent) {
    	webDriverProperties.setUserAgent(userAgent);
    }
    
    public Proxy getProxy() {
    	return proxy;
    }
    
    public void setProxy(final Proxy server) {
    	this.proxy = server;
    }
    
    public int getImplicitWait() {
    	return webDriverProperties.getImplicitlyWait();
    }
    
    public void setImplicitWait(final int wait) {
    	webDriverProperties.setImplicitlyWait(wait);
    }
    
    public int getPageLoadTimeout() {
    	return webDriverProperties.getPageLoadTimeout();
    }
    
    public void setPageLoadTimeout(final int timeout) {
    	webDriverProperties.setPageLoadTimeout(timeout);
    }
    
    public boolean isGridExecution() {
        return webDriverProperties.isGridExecution();
    }
    
    public void setGridExecution(final boolean isGridExecution) {
    	webDriverProperties.setGridExecution(isGridExecution);
    }
    
    public String setGridHubUrl() {
    	return webDriverProperties.getGridHubUrl();
    }
    
    /**
     * Set Selenium Grid Hub URL
     *
     * @param hubUrl String
     */
    public void setGridHubUrl(final String gridHubUrl) {
        webDriverProperties.setGridHubUrl(gridHubUrl);
    }
    
    /**
     * Method that relieves the remote browser version
     *
     * @return remote browser version
     */
    public String getRemoteBrowserVersion() {
        return webDriverProperties.getRemoteBrowserVersion();
    }
    
    /**
     * Use this method to set remote browser version
     * 
     * @param version
     * @param caps
     */
    public void setRemoteBrowserVersion(final String version) {
    	webDriverProperties.setRemoteBrowserVersion(version);
    }

    /**
     * Method that retrieves to the remote browser name
     *
     * @return remote browser name
     */
    public String getRemoteBrowserName() {
    	return webDriverProperties.getRemoteBrowserName();
    }

    /**
     * Method that retrieves to the remote platform name
     *
     * @return remote browser name
     */
    public String getRemotePlatformName() {
    	return webDriverProperties.getRemotePlatformName();
    }
    
    public void setRemotePlatformName(final String platform) {
    	webDriverProperties.setRemotePlatformName(platform);
    }
    
    /**
     * Use this method to get remote server node IP address
     * 
     * @param driver RemoteWebDriver
     * @return ip of the execution node
     */
    public String getRemoteServerNodeIPAddress(RemoteWebDriver driver) {
    	SeleniumGridHub hub = new SeleniumGridHub(driver);
    	return hub.getIpAddress();
    }
    
    // Methods

    /**
     * Instantiate and configure the WebDriver to a specific browser,
     * set the timeouts and generate default desired browser capabilities.
     *
     * @return WebDriver instance
     * @throws Exception 
     */
    public WebDriver initBrowser() throws Exception {
    	
        if (webDriverProperties.getBrowserType() != null) {

            final String browser = webDriverProperties.getBrowserType().toLowerCase().trim();
            
            logger.debug("Configuring browser for " + browser);

            BrowserTypes type = BrowserTypes.getBrowserEnum(browser);
            if (type != null) {

            	DriverBinaryDownloader.downloadDriver(BrowserTypes.getBrowserEnum(browser), webDriverProperties.getCurrentOs());
            	
            	GeneratedDesiredCapabilities caps = null;
                switch (type) {

                    case FIREFOX:
                	case FIREFOX_MOB:
                		if(firefoxProfile != null) {
                			caps = capsFactory.buildCustomFirefoxProfileCaps(firefoxProfile, proxy); // TOOD add type
                		}else {
                			// default one
                			caps = capsFactory.buildDefaultCaps(type, proxy);
                		}
                        caps.setCustomJVMCapabilities();
                        driverOut = generateWebDriver(type, caps);
                        break;
                    case CHROME:
                    case CHROME_MOB:
                    	if(chromeOptions != null) {
                    		caps = capsFactory.buildCustomChromeOptionsCaps(chromeOptions, proxy); // TODO add type
                    	}else if(chromeOptionsMap != null) {
                    		caps = capsFactory.buildCustomChromeOptionsCaps(chromeOptionsMap, proxy); // TODO add type
                    	}else {
                    		// default one
                    		caps = capsFactory.buildDefaultCaps(type, proxy);
                    	}
                    	caps.setCustomJVMCapabilities();                        
                    	caps.setCustomSwitchesOptions();
                    	caps.setCustomDisablePlugins();
                        driverOut = generateWebDriver(type, caps);
                        break;
                    case IE:
                    	caps = capsFactory.buildDefaultCaps(BrowserTypes.IE, proxy);
                    	caps.setCustomJVMCapabilities();
                        driverOut = generateWebDriver(BrowserTypes.IE, caps);
                        break;
                    case SAFARI:
                    	caps = capsFactory.buildDefaultCaps(BrowserTypes.SAFARI, proxy);
                    	caps.setCustomJVMCapabilities();
                        driverOut = generateWebDriver(BrowserTypes.SAFARI, caps);
                        break;
                    case PHANTOM:
                    	caps = capsFactory.buildDefaultCaps(BrowserTypes.PHANTOM, proxy);
                    	caps.setCustomJVMCapabilities();
                        driverOut = generateWebDriver(BrowserTypes.PHANTOM, caps);
                        break;
                    default:
                    	throw new Exception("Unknown browser type "+ type +" to generate and init");
                }

            } else
                throw new CustomExceptions.NullArgumentException("Invalid value for variable com.williamhill.whgtf.webdriver.WebDriverProperties.browserType");

        } else {
            throw new CustomExceptions.NullArgumentException("Null variable com.williamhill.whgtf.webdriver.WebDriverProperties.browserType");
        }
        
        // finally, save into current drivers map
        multiThreadedDriverFactory.setMultiThreadedDriver(driverOut);
        return driverOut;
    }

    /**
     * Use this method to generate WebDriver for given type
     * <br/>
     * NOTE: - WebDriver generated can be local or remote one
     * 
     * @param type
     * @return
     * @throws Exception 
     */
    private WebDriver generateWebDriver(final BrowserTypes type, final GeneratedDesiredCapabilities caps) throws Exception {
    	
    	WebDriver driver = null;
    	
    	WebDriverFactory factory = new WebDriverFactory(webDriverProperties);
    	if(!webDriverProperties.isGridExecution()) {
    		// first, generate web driver instance based on type and caps
    		driver = factory.buildDriver(type, caps);
        	
        	// then, configure
        	configureWebDriver(driver);
    	}else {
    		// first, generate remote web driver instance based on caps
    		driver = factory.buildRemoteDriver(caps);
    		
    		// then, configure
    		configureRemoteWebDriver((RemoteWebDriver) driver);
    	}
    	return driver;
    }
    
    /**
     * Use this method to configure given WebDriver instance
     * 
     * @param driver
     * @return
     * @throws Exception 
     */
    private void configureWebDriver(WebDriver driver) throws Exception {

        configureTimeouts(driver);
        configureResolution(driver);

        // node data
        logger.debug("WebDriver executing on Local IP: " + webDriverProperties.setLocalExecutionIP());
        logger.debug("WebDriver executing on OS: " + webDriverProperties.getCurrentOs());
        logger.debug("WebDriver executing on Architecture: " + webDriverProperties.getCurrentArchitecture());
    }

    /**
     * Use this method to configure given RemoteWebDriver instance
     * 
     * @param driver
     * @return
     * @throws Exception 
     */
    private void configureRemoteWebDriver(final RemoteWebDriver driver) throws Exception {
        
    	configureTimeouts(driver);
    	configureResolution(driver);
    	
        // node data
        logger.debug("WebDriver executing against Remote Server Node IP: " + getRemoteServerNodeIPAddress(driver));
        logger.debug("WebDriver executing on Remote Platform: " + getRemotePlatformName());
                
        // browser data
        logger.debug("WebDriver executing on Remote Browser Name: " + getRemoteBrowserName());
        logger.debug("WebDriver executing on Remote Browser Version: " + getRemoteBrowserVersion());
    }
    
    /**
     * Use this method to configure timeouts
     *
     * @param driver WebDriver
     */
    private void configureTimeouts(final WebDriver driver) {
    	logger.debug("Setting implicitly wait to " + webDriverProperties.getImplicitlyWait() + " sec.");
    	driver.manage().timeouts().implicitlyWait(webDriverProperties.getImplicitlyWait(), TimeUnit.SECONDS);
        
        logger.debug("Setting page load timeout to " + webDriverProperties.getPageLoadTimeout() + " sec.");
        driver.manage().timeouts().pageLoadTimeout(webDriverProperties.getPageLoadTimeout(), TimeUnit.SECONDS);
    }
    
    /**
     * Use this method to configure resolution, if so
     * 
     * @param driver
     * @throws Exception 
     */
    private void configureResolution(final WebDriver driver) throws Exception {
    
        if (webDriverProperties.getBrowserResolution() != null)
        	setCustomBrowserResolution(webDriverProperties.getBrowserResolutionArray(), driver);
        else
            driver.manage().window().maximize();
    }
    
    /**
     * Set custom browser window resolution
     *
     * @param resolution Integer[]
     * @param driver     WebDriver
     */
    private void setCustomBrowserResolution(final Integer[] resolution, final WebDriver driver) {

        if (resolution != null && driver != null) {
            if (resolution.length == 2) {
                setCustomBrowserResolution(resolution[0], resolution[1], driver);
            }else {
            	throw new IllegalArgumentException("Resolution array must have size 2 for only two dimensions: width and height");
            }
        }
    }

    /**
     * Set custom browser window resolution
     *
     * @param width  Integer
     * @param height Integer
     * @param driver WebDriver
     */
    private void setCustomBrowserResolution(final int width, final int height, final WebDriver driver) {
        logger.debug("Setting custom browser window resolution to " + width + "x" + height);

        if (width > 0 && height > 0 && driver != null)
            driver.manage().window().setSize(new Dimension(width, height));
    }
   
}