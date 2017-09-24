package com.maxbet.registration.webdriver;

import com.williamhill.whgtf.browsers.BrowserTypes;
import com.williamhill.whgtf.webdriver.WebDriverProperties;
import com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


@Component
public class WebDriverFactory {

	private static final Logger logger = LogManager.getLogger( com.williamhill.whgtf.webdriver.drivers.WebDriverFactory.class);
	
	private WebDriverProperties webDriverProperties;
	
	// Hub URL
    private URL configuredGridHubUrl;
    private static final String DEFAULT_GRID_HUB_URL = WebDriverProperties.getSeleniumPropertiesPropFile().getProperty("default.hub.url");

    /**
     * Constructor
     *
     * @param webDriverProperties WebDriverProperties
     */
    public WebDriverFactory(WebDriverProperties webDriverProperties) {
        this.webDriverProperties = webDriverProperties;
    }

    /**
     * Use this method to build a Web Driver for given type and capabilities
     * 
     * @param type
     * @param caps
     * @return
     * @throws Exception 
     */
	public WebDriver buildDriver(BrowserTypes type, GeneratedDesiredCapabilities caps) throws Exception {
		logger.debug("Building Web Driver local session for browser "+ type);
		
		switch(type) {
			case FIREFOX:
			case FIREFOX_MOB:
				return getFirefoxDriver(caps);
			case CHROME:
			case CHROME_MOB:
				return getChromeDriver(caps);
			case IE:
				return getIEDriver(caps);
			case SAFARI:
				return getSafariDriver(caps);
			case PHANTOM:
				return getPhantomDriver(caps);
			default:
				throw new Exception("Unknown browser type "+ type +" to build web driver instance");
		}
	}

	/**
	 * Use this method to build a Remote Web Driver for given capabilities
	 * 
	 * @param caps
	 * @return
	 * @throws Exception 
	 */
	public RemoteWebDriver buildRemoteDriver(final GeneratedDesiredCapabilities caps) throws Exception {
		return buildRemoteDriver(configuredGridHubUrl, webDriverProperties.getRemotePlatformName(), webDriverProperties.getRemoteBrowserVersion(), caps);
	}
	
	/**
	 * Use this method to build a Remote Web Driver for given remote URL and capabilities
	 * 
	 * @param remoteURL
	 * @param platform
	 * @param browserVersion
	 * @param caps
	 * @return
	 * @throws Exception 
	 */
	public RemoteWebDriver buildRemoteDriver(URL remoteURL, final String platform, final String browserVersion, GeneratedDesiredCapabilities caps) throws Exception {
		
		// first, set browser selection capabilities for server
		caps.setCapabilityBrowserName(BrowserTypes.CHROME);
		caps.setCapabilityBrowserVersion(browserVersion);
		caps.setCapabilityPlatform(platform);
		
		// finally, configure server URL
		if(remoteURL != null) {
			configuredGridHubUrl = remoteURL;
		}else {
			// otherwise, using specific ones
			try {
				String hubUrl = webDriverProperties.getHubUrl();
				if (hubUrl != null && !hubUrl.isEmpty()) {
					// if not, hub URL
					configuredGridHubUrl = new URL(hubUrl);
				}else {
					// otherwise, default one
					configuredGridHubUrl = new URL(DEFAULT_GRID_HUB_URL);
				}
			} catch (MalformedURLException e) {
				logger.error(e);
			}
		}
		
		logger.debug("Building Web Driver remote session for "+ configuredGridHubUrl.toString() +" and given capabilities " + caps);
		
	    return new RemoteWebDriver(configuredGridHubUrl, caps.getDesiredCapabilities());
	}
	
    private WebDriver getFirefoxDriver() {
    	return new FirefoxDriver();
    }

    private WebDriver getFirefoxDriver(GeneratedDesiredCapabilities caps) throws Exception {
    	if(caps == null) {
    		throw new Exception("Generated Desired Capabilities is null");
    	}
    	
        return new FirefoxDriver(caps.getDesiredCapabilities());
    }

    private WebDriver getFirefoxDriver(FirefoxProfile profile) throws Exception {
    	if(profile == null) {
    		throw new Exception("Firefox profile is null");
    	}
    	
        return new FirefoxDriver(profile);
    }

    private WebDriver getChromeDriver(ChromeDriverService service, Capabilities caps) {
        return new ChromeDriver(service, caps);
    }

    private WebDriver getChromeDriver(GeneratedDesiredCapabilities caps) {
    	// starting a Chrome driver service - performance reasons
        ChromeDriverService.Builder builder = new ChromeDriverService.Builder();
        builder.usingDriverExecutable(new File(webDriverProperties.setChromeBinaryProperty(null)));
        builder.withSilent(true);
        builder.usingAnyFreePort();
        ChromeDriverService service = builder.build();
        
        return new ChromeDriver(service, caps.getDesiredCapabilities());
    }

    private WebDriver getIEDriver(GeneratedDesiredCapabilities caps) {

        if (!Platform.getCurrent().is(Platform.WINDOWS))
        	throw new RuntimeException("The current platform is not supported: " + Platform.getCurrent().family());
        
        // set binary version
        webDriverProperties.setIEBinaryProperty(webDriverProperties.getIEBinaryVersion());
        
        return new InternetExplorerDriver(caps.getDesiredCapabilities());
    }

    private WebDriver getSafariDriver(GeneratedDesiredCapabilities caps) {
        return new SafariDriver(caps.getDesiredCapabilities());
    }
    
    private WebDriver getPhantomDriver() {
    	return new PhantomJSDriver();
    }

    private WebDriver getPhantomDriver(GeneratedDesiredCapabilities caps) {
        if(caps != null){
            return new PhantomJSDriver(caps.getDesiredCapabilities());
        } else{
            return new PhantomJSDriver();
        }

    }
}