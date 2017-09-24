package com.maxbet.registration.webdriver.caps;

import com.williamhill.whgtf.browsers.BrowserTypes;
import com.williamhill.whgtf.browsers.firefox.FirefoxBrowser;
import com.williamhill.whgtf.selenium.mvn.SeleniumDependency;
import com.williamhill.whgtf.webdriver.WebDriverProperties;
import com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Atanas Kanchev
 *         <p/>
 *         WebDriver DesiredCabilities Factory
 */
public final class DesiredCapabilitiesFactory {

	private final static Logger logger = LogManager.getLogger( com.williamhill.whgtf.webdriver.caps.DesiredCapabilitiesFactory.class);

	private static final String FIREFOX_PREFERENCE_BLOCK_ACTIVE_CONTENT = "security.mixed_content.block_active_content";
	private static final String FIREFOX_PREFERENCE_BLOCK_DISPLAY_CONTENT = "security.mixed_content.block_display_content";

	private static final String CHROME_DRIVER_BINARY_PROPERTY = "webdriver.chrome.driver";
	private static final String GECKO_DRIVER_BINARY_PROPERTY = "webdriver.gecko.driver";
	private static final String PHANTOMJS_DRIVER_BINARY_PROPERTY = "phantomjs.binary.path";

//	private FirefoxProfile firefoxProfile;

	private WebDriverProperties webDriverProperties;

	/**
	 * Param constructor
	 *
	 * @param webDriverProperties
	 *            WebDriverProperties
	 */
	public DesiredCapabilitiesFactory(WebDriverProperties webDriverProperties) {
		this.webDriverProperties = webDriverProperties;
	}
	
	// Methods
	
	/**
	 * Set the default desired browser capabilities
	 *
	 * @param browser
	 *            BrowsersEnum
	 * @param proxy Proxy by Selenium
	 * @return capabilities DesiredCapabilities
	 * @throws Exception 
	 */
	public com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities buildDefaultCaps(final BrowserTypes type, final Proxy proxy) throws Exception {

		com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities caps = null;
		switch (type) {
			case CHROME:
				caps = buildDefaultChromeCaps();
				break;
			case CHROME_MOB:
				caps = buildDefaultChromeMobileCaps();
				break;
			case FIREFOX:
				caps = buildDefaultFirefoxCaps();
				break;
			case FIREFOX_MOB:
				caps = buildDefaultFirefoxMobileProfile();
				break;
			case IE:
				caps = buildDefaultIECaps();
				break;
			case SAFARI:
				caps = buildDefaultSafariCaps();
				break;
			case PHANTOM:
				caps = buildDefaultPhantomCaps();
				break;
			default:
				throw new Exception("Unknown browser type "+ type +" to build default desired capabilites");
		}

		if(proxy != null) {
			logger.info("Setting Proxy Server with hostname "+ proxy.getHttpProxy() + " and type "+ proxy.getProxyType());
			caps.setCapabilityProxy(proxy);
		}

		return caps;
	}

	private com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities buildDefaultChromeCaps() throws Exception {

		DesiredCapabilities caps = DesiredCapabilities.chrome();

		ChromeOptions options = new ChromeOptions();

		// set default Chrome preferences
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.password_manager_enabled", "false");
		options.setExperimentalOption("prefs", prefs);

		// set default Chrome options
		if(caps.getCapability(ChromeOptions.CAPABILITY) == null){
			options.addArguments("start-maximized");
			// avoid showing any warnings when launching chrome
			options.addArguments("test-type");
			options.addArguments("no-default-browser-check");
			options.addArguments("disable-extensions");

			// TODO why if using getting session error with Service?
//			if(System.getProperty(CHROME_DRIVER_BINARY_PROPERTY) != null) {
//				System.setProperty(CHROME_DRIVER_BINARY_PROPERTY, new File(System.getProperty(CHROME_DRIVER_BINARY_PROPERTY)).getAbsolutePath());
//				options.setBinary(System.getProperty(CHROME_DRIVER_BINARY_PROPERTY));
//			}
			caps.setCapability(ChromeOptions.CAPABILITY, options);
		}

		// set to accept any SSL certificate
		caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

		return new com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities(BrowserTypes.CHROME, caps, options);
	}

	/**
	 * Use this method to get DesiredCapabilities with custom Chrome options
	*
 	 * @param chromeOptions
	 * @param proxy
	 * @return
	 * @throws Exception
	 *
	 */
	public com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities buildCustomChromeOptionsCaps(final ChromeOptions chromeOptions, final Proxy proxy) throws Exception {
		// first, get default capabilities
		com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities caps = buildDefaultChromeCaps();

		if(proxy != null) {
			logger.info("Setting Proxy Server with hostname "+ proxy.getHttpProxy() + " and type "+ proxy.getProxyType());
			caps.setCapabilityProxy(proxy);
		}

		// and then setting custom ones
		caps.setCustomChromeOptions(chromeOptions);
		return caps;
	}

	/**
	 * Use this method to get DesiredCapabilities with custom Chrome options
	 *
	 * @param chromeOptions
	 * @param proxy
	 * @return
	 * @throws Exception
	 *
	 */
	public com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities buildCustomChromeOptionsCaps(final Map<String, Object> chromeOptions, final Proxy proxy) throws Exception {
		// first, get default capabilities
		com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities caps = buildDefaultChromeCaps();

		if(proxy != null) {
			logger.info("Setting Proxy Server with hostname "+ proxy.getHttpProxy() + " and type "+ proxy.getProxyType());
			caps.setCapabilityProxy(proxy);
		}

		// and then setting custom ones
		caps.setCustomChromeOptions(chromeOptions);
		return caps;
	}

	private com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities buildDefaultChromeMobileCaps() throws Exception {

		DesiredCapabilities caps = DesiredCapabilities.chrome();

		ChromeOptions options = new ChromeOptions();

		// set default Chrome options
		options.addArguments("user-agent="
				+ webDriverProperties.configureUserAgent());
		options.addArguments("start-maximized");
		options.addArguments("allow-running-insecure-content");
		options.addArguments("ignore-certificate-errors");
		// avoid showing any warnings when launching chrome
		options.addArguments("test-type");
		options.addArguments("disable-extensions");
		caps.setCapability(ChromeOptions.CAPABILITY, options);

		// set to accept any SSL certificate
		caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

		return new com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities(BrowserTypes.CHROME_MOB, caps, options);
	}

	/**
     * NOTE:<br/>
     * - With Selenium 2, FirefoxDriver does not support Firefox 48+
     * - So either use Marionette or downgrade to 46
     * <br/>
     * - With Selenium 3.0.0, Firefox is only fully supported at version 47.0.1 or earlier<br/>
     * - So support for later versions of firefox is provided by geckodriver<br/>
	 * @param proxy
     *
     * @return
     */
	private com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities buildDefaultFirefoxCaps() throws Exception {

		DesiredCapabilities caps = DesiredCapabilities.firefox();

		// if no firefox profile specified, setting default one
		if (caps.getCapability("firefox_profile") == null) {

			FirefoxProfile profile = new FirefoxProfile();

			// setting default preferences for new FF profile
			profile.setEnableNativeEvents(false);
			profile.setPreference(FIREFOX_PREFERENCE_BLOCK_ACTIVE_CONTENT, false);
			profile.setPreference(FIREFOX_PREFERENCE_BLOCK_DISPLAY_CONTENT, true);

			caps.setCapability(FirefoxDriver.PROFILE, profile);
		}

		// if it's needed to override browser binary (for example, portable version)
		if(System.getProperty(FirefoxDriver.BINARY) != null) {
			logger.info("Using Firefox browser binary: "+ System.getProperty(FirefoxDriver.BINARY));
			caps.setCapability(FirefoxDriver.BINARY, System.getProperty(FirefoxDriver.BINARY));
			//caps.setCapability("marionette", true);
		}

		// any action to ensure compatibility
		configureFirefoxForCompatibility(caps);

		return new com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities(BrowserTypes.FIREFOX, caps);
	}

	/**
	 * Use this method to configure Firefox browser for compability
	 *
	 * @param caps
	 * @throws Exception
	 */
	private void configureFirefoxForCompatibility(final DesiredCapabilities caps) throws Exception {

		boolean isFF46OrEarlier = FirefoxBrowser.is46VersionOrEarlier();
		boolean isFF47 = FirefoxBrowser.is47Version();
		boolean isFF48OrHigher = FirefoxBrowser.is48VersionOrHigher();

		try {
			if(SeleniumDependency.is2Version()) {
				if(isFF46OrEarlier) {
					// nothing to do, Selenium 2 supports Firefox 46 or earlier
				}else if(isFF47 || isFF48OrHigher) {
					logger.error("Firefox 48 or Higher needs to use Mozilla GeckoDriver binary with Marionette");
					logger.error("So Selenium 2 doesn't support Firefox 47 or higher: Migrate to Selenium 3");
				}
			}else if(SeleniumDependency.is3Version()) {
				if(isFF46OrEarlier || isFF47) {
					// for earlier FF versions, it's needed to indicate GeckoDriver binary path
					if(System.getProperty(GECKO_DRIVER_BINARY_PROPERTY) != null) {
						logger.debug("Using GeckoDriver to launch Firefox");
						logger.info("Using GeckoDriver binary path: "+ System.getProperty(GECKO_DRIVER_BINARY_PROPERTY));
					}else {
						logger.error("GeckoDriver binary path needs to be set as system property 'webdriver.gecko.driver");
					}
				}else if(isFF48OrHigher) {
					// nothing to do for Firefox 48 or higher, Selenium 3 supports GeckoDriver and Marionette by default
					logger.debug("Using GeckoDriver to launch Firefox");
				}
			}else {
				logger.info("Skipped Firefox compability configuration for Selenium "+ SeleniumDependency.getSeleniumVersion() +" and Firefox "+ FirefoxBrowser.getVersionInstalled());
			}
		}catch(IOException ex) {
			logger.error("Selenium version couldn't be checked, so assuming last version and nothing to do for compability", ex);
		}
	}

	/**
	 * Use this method to get DesiredCapabilities with custom Firefox profile
	 *
	 * @param profile Firefox profile
	 * @param proxy Selenium proxy instance
	 * @return
	 * @throws Exception
	 *
	 */
	public com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities buildCustomFirefoxProfileCaps(final FirefoxProfile profile, final Proxy proxy) throws Exception {
		// first, get default capabilities
		com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities caps = buildDefaultFirefoxCaps();

		if(proxy != null) {
			logger.info("Setting Proxy Server with hostname "+ proxy.getHttpProxy() + " and type "+ proxy.getProxyType());
			caps.setCapabilityProxy(proxy);
		}

		caps.setCustomFirefoxProfile(profile);
		return caps;
	}

	private com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities buildDefaultFirefoxMobileProfile() throws Exception {

		DesiredCapabilities caps = DesiredCapabilities.firefox();

		// set default Firefox profile
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setPreference("general.useragent.override",
				webDriverProperties.configureUserAgent());
		caps.setCapability(FirefoxDriver.PROFILE, firefoxProfile);

		return new com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities(BrowserTypes.FIREFOX, caps, firefoxProfile);
	}

	private com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities buildDefaultIECaps() throws Exception {

		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();

		// set default IE capabilities
		caps.setCapability(CapabilityType.BROWSER_NAME, "iexplore");
		caps.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
		caps.setCapability("requireWindowFocus", true);
		caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

		// set to accept any SSL certificate, even untrusted
		//caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

		// enable JS
		caps.setJavascriptEnabled(true);

		return new com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities(BrowserTypes.IE, caps);
	}

	private com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities buildDefaultSafariCaps() throws Exception {

		DesiredCapabilities caps = DesiredCapabilities.safari();

		// set default Safari capabilities
		caps.setCapability("safari.cleanSession", true);
		System.setProperty("webdriver.safari.noinstall", "true");

		return new com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities(BrowserTypes.SAFARI, caps);
	}

	private com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities buildDefaultPhantomCaps() throws Exception {

		DesiredCapabilities caps = DesiredCapabilities.phantomjs();

		if(System.getProperty(PHANTOMJS_DRIVER_BINARY_PROPERTY) != null) {
			caps.setCapability(PHANTOMJS_DRIVER_BINARY_PROPERTY, System.getProperty(PHANTOMJS_DRIVER_BINARY_PROPERTY));
		}
		return new GeneratedDesiredCapabilities(BrowserTypes.PHANTOM, caps);
	}
	
}