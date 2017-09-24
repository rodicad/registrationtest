package com.maxbet.registration.webdriver.caps;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.williamhill.whgtf.browsers.BrowserTypes;
import com.williamhill.whgtf.exceptions.CustomExceptions;
import com.williamhill.whgtf.utils.JVMArgs;
import com.williamhill.whgtf.utils.OptionsSplitter;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.*;
import java.util.logging.Level;

public class GeneratedDesiredCapabilities {
	
	private static final Logger logger = LogManager.getLogger( com.williamhill.whgtf.webdriver.caps.GeneratedDesiredCapabilities.class);

	private static final String BROWSER_VERSION_ANY = "ANY";
	
	private DesiredCapabilities caps;
	private LoggingPreferences logPrefs;
	
	// Generic browser specific
	private BrowserTypes type;
	private String browserVersion = BROWSER_VERSION_ANY;
	
	// chrome specific
	private ChromeOptions options;
	
	// firefox specific
	private FirefoxProfile profile;
	
	// custom configuration
	private String customJMVCapabilities;
	private String customSwitchesOptions;
	private String customDisablePlugins;

	public GeneratedDesiredCapabilities(final BrowserTypes type, final DesiredCapabilities caps) throws Exception {
		this(type, caps, null, null);
	}
	
	public GeneratedDesiredCapabilities(final BrowserTypes type, final DesiredCapabilities caps, final ChromeOptions options) throws Exception {
		this(type, caps, options, null);
	}
	
	public GeneratedDesiredCapabilities(final BrowserTypes type, final DesiredCapabilities caps, final FirefoxProfile profile) throws Exception {
		this(type, caps, null, profile);
	}
	
	public GeneratedDesiredCapabilities(final BrowserTypes type, final DesiredCapabilities caps, final ChromeOptions options, final FirefoxProfile profile) throws Exception {
		
		this.type = type;
		if(type.equals(BrowserTypes.CHROME) || type.equals(BrowserTypes.CHROME_MOB)) {
			if(options != null) {
				this.options = options;
			}
		}else if(type.equals(BrowserTypes.FIREFOX) || type.equals(BrowserTypes.FIREFOX_MOB)) {
			if(profile != null) {
				this.profile = profile;
			}
		}
		
		this.caps = caps;
		if(caps == null) {
			throw new Exception("DesiredCapabilities are null or empty");
		}
		
		this.logPrefs = new LoggingPreferences();
		
		// get custom configuration
		customJMVCapabilities = System.getProperty(JVMArgs.CUSTOM_CAPABILITY.getName());
		customSwitchesOptions = System.getProperty(JVMArgs.CUSTOM_OPTIONS.getName());
		customDisablePlugins = System.getProperty(JVMArgs.CUSTOM_DISABLE_PLUGINS.getName());

		// setting browser selection caps for Selenium server
//		setCapabilityBrowserName(type);
		caps.setVersion(browserVersion);
	}
	
	public DesiredCapabilities getDesiredCapabilities() {
		return caps;
	}
	
	public String getCustomJMVCapabilities() {
		return customJMVCapabilities;
	}
		
	public String getCustomSwitchesOptions() {
		return customSwitchesOptions;
	}
	
	public String getCustomDisablePlugins() {
		return customDisablePlugins;
	}
	
	// Methods
	
	/**
	 * Use this method to set capability for browser name
	 * 
	 * @param type
	 */
    public void setCapabilityBrowserName(BrowserTypes type) {

        if (type == null) {
        	throw new CustomExceptions.NullArgumentException("Null argument: BrowserTypes browser");
        }
        	
        String remoteBrowserName = type.getBrowserName().replace("_mob", "");
        
        caps.setBrowserName(remoteBrowserName);            
        logger.debug("Configured browser name capability: " + remoteBrowserName);
    }

    /**
     * Use this method to set capability browser version
     * 
     * @param version
     * @throws Exception 
     */
    public void setCapabilityBrowserVersion(final String browserVersion) {

    	String removeBrowserVersion = browserVersion;
        if (browserVersion == null || browserVersion.isEmpty()) {
        	// if there is no version, then trying with ANY
        	removeBrowserVersion = BROWSER_VERSION_ANY;
        }

        caps.setVersion(removeBrowserVersion);
        logger.debug("Configured browser version capability: " + removeBrowserVersion);
    }

    /**
     * Use this method to set capability platform
     * 
     * @param platformName
     * @throws Exception 
     */
    public void setCapabilityPlatform(final String platformName) throws Exception {

    	if(platformName == null) {
    		throw new Exception("Platform is null or empty");
    	}
        
    	Platform platform = null;
    	switch(platformName) {
    		case "windows":
    			platform = Platform.WINDOWS;
    			break;
    		case "win7":
    			platform = Platform.VISTA;
    			break;
    		case "win8":
    			platform = Platform.WIN8;
    			break;
    		case "win8_1":
    			platform = Platform.WIN8_1;
    			break;
    		case "win10":
    			platform = Platform.WIN10;
    			break;
    		case "linux":
    			platform = Platform.LINUX;
    			break;
    		case "mac":
    			platform = Platform.MAC;
    			break;
    		default: 
    			// TODO add more cases if so
    			break;
    	}
    	
    	caps.setPlatform(platform);
        logger.debug("Configured browser platform capability: " + platform.toString());
    }

    /**
     * Use this method to set capability proxy
     * 
     * @param proxy
     */
	public void setCapabilityProxy(final Proxy proxy) {
		
		if (proxy == null) {
        	throw new CustomExceptions.NullArgumentException("Null argument: Proxy instance");
        }
		
		caps.setCapability(CapabilityType.PROXY, proxy);            
        logger.debug("Configured proxy capability with hostname "+ proxy.getHttpProxy() + " and type "+ proxy.getProxyType());
	}
    
	/**
	 * Use this method to set custom capabilities from -Dcapability
	 * @throws Exception 
	 * 
	 */
	public void setCustomJVMCapabilities() throws Exception {
		
		if ( StringUtils.isNotEmpty(customJMVCapabilities)) {
			setCustomCapabilities(parseJsonCustomCapabilities(customJMVCapabilities));
		}else {
			logger.debug("Skipping setting custom capabilities from -Dcapability since any capability was not indicated");
		}
	}
	
	/**
	 * Use this method to set custom browser capabilities, overriding any previous one
	 * 
	 */
	private void setCustomCapabilities(final HashMap<String, Object> capsMap) throws Exception {

		if (capsMap == null || capsMap.isEmpty()) {
			throw new Exception("Given capabilities map is null or empty");
		}
		
		logger.debug("Setting custom DesiredCapabilities from map");
	
		for (Map.Entry<String, Object> map : capsMap.entrySet()) {
			
			if (map.getKey() == null) {
				throw new CustomExceptions.NullKeyException("Null key");
			} else if (map.getValue() == null) {
				throw new CustomExceptions.NullValueException("Null value");
			} else if (map.getKey().isEmpty()) {
				throw new CustomExceptions.EmptyKeyException("Empty key");
			} else {
				caps.setCapability(map.getKey(), map.getValue());
				logger.debug("Custom capability with key '" + map.getKey() + "' and value '"+ map.getValue() +"'");
			}
		}
	}
		
	/**
	 * Parse custom capability JVM arg
	 *
	 * @return HashMap<StringObject>
	 */
	private HashMap<String, Object> parseJsonCustomCapabilities(final String jsonAsString) {

		HashMap<String, Object> map = new HashMap<>();

		JSONObject jObject = null;
		try {
			jObject = new JSONObject(jsonAsString);
			logger.debug("JSON file parsed: " + jObject);
			
			if (jObject != null) {
				
				Iterator<?> keys = jObject.keys();
				while (keys.hasNext()) {
					String key = (String) keys.next();
					Object value = jObject.get(key);
					if(!key.equals("loggingPrefs")) {
						// nothing to do, or add to specific case more
					}else {
						Map<String, String> jsonMap = new Gson().fromJson(String.valueOf(value), new TypeToken<HashMap<String, String>>() {}.getType());
						setCustomLoggingPrefs(jsonMap);
						value = logPrefs;
					}
					map.put(key, value);
				}
				logger.debug("map : " + map);
			}
			
		} catch (JSONException e) {
			logger.error("Unable to parse JSON " + e);
		}
		
		return map;
	}
	
	/**
	 * Use this method to set custom Chrome options, overriding any previous
	 * @throws Exception 
	 * 
	 */
	public void setCustomChromeOptions(final ChromeOptions chromeOptions) throws Exception {
		
		if (chromeOptions == null) {
			throw new Exception("Custom Chrome Options are null or empty");
		}
		
		if(!type.equals(BrowserTypes.CHROME)) {
			throw new Exception("Custom Chrome Options only can be set in Chrome browser, but browser type is "+ type);
		}
		
		if (caps == null)
			caps = DesiredCapabilities.chrome();
				
		caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		logger.debug("Setting custom Chrome Options: "+ chromeOptions.toJson());
	}
	
	/**
	 * Use this method to set custom Chrome options, overriding any previous
	 * @throws Exception 
	 * 
	 */
	public void setCustomChromeOptions(final Map<String, Object> mapChromeOptions) throws Exception {
		
		if (mapChromeOptions == null || mapChromeOptions.isEmpty()) {
			throw new IllegalArgumentException("Chrome options maps is null or empty");
		}
		
		if(!type.equals(BrowserTypes.CHROME)) {
			throw new Exception("Custom Chrome Options only can be set in Chrome browser, but browser type is "+ type);
		}
		
		if (caps == null)
			caps = DesiredCapabilities.chrome();
		
		caps.setCapability(ChromeOptions.CAPABILITY, mapChromeOptions);
		logger.debug("Setting custom Chrome Options: " + mapChromeOptions.toString());
	}

	/**
	 * Use this method to set custom Firefox profile, overriding current one
	 * @throws Exception 
	 * 
	 */
	public void setCustomFirefoxProfile(final FirefoxProfile firefoxProfile) throws Exception {

		if (firefoxProfile == null) {
			throw new IllegalArgumentException("Firefox Profile is null or empty");
		}
		
		if(!type.equals(BrowserTypes.FIREFOX)) {
			throw new Exception("Firefox Profile only can be set in Firefox browser, but browser type is "+ type);
		}
			
		if (caps == null)
			caps = DesiredCapabilities.firefox();
			
		caps.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
		logger.debug("Setting custom Firefox Profile: " + firefoxProfile.toString());
	}

	/**
	 * Use this method to set a group of custom logging preferences for given map
	 * 
	 * @param logOptions
	 * @return
	 */
	public void setCustomLoggingPrefs(final Map<String, String> mapPrefs) {
		for(String key : mapPrefs.keySet()) {
			setCustomLoggingPrefs(key, Level.parse(mapPrefs.get(key)));
		}
	}

	/**
	 * Use this method to set custom logging preference for given log type and level
	 * <br/>
	 * NOTE:<br/>
	 * - OFF or NONE will disable<br/>
	 * 
	 * @param logType
	 * @param logLevel
	 * @return
	 */
	private void setCustomLoggingPrefs(final String logType, Level logLevel) {
 	
		if(logType == null || logType.isEmpty()) {
			logger.error("Log Type is null or empty");
			return;
		}else if(logLevel == null) {
	    	logger.error("Log Level is null");
	    	return;
	    }

 		if(!"none".equalsIgnoreCase(logLevel.getName()) || logType.equalsIgnoreCase(Level.OFF.getName())) {
	        logger.debug("Log "+ logType.toUpperCase() +" enabled with level "+ logLevel.getName());
	        
	        logPrefs.enable(logType, logLevel);
	    }else {
	    	logger.debug("Log "+ logType.toUpperCase() +" disabled");
	    	
	    	logPrefs.enable(logType, Level.OFF);
	    }
	}
	
	/**
	 * Use this method to set custom disable plugins
	 * @throws Exception 
	 * 
	 */
	public void setCustomDisablePlugins() throws Exception {
		
		if(!type.equals(BrowserTypes.CHROME) && !type.equals(BrowserTypes.CHROME_MOB)) {
			throw new Exception("Custom Disable Plugins can be only set for Chrome browser, but browser is "+ type);
		}
		
		if ( StringUtils.isNotEmpty(customDisablePlugins)) {
			String[] plugins = customDisablePlugins.split(",");
		
			Map<String, Object> preferences = new Hashtable<String, Object>();
			options.setExperimentalOption("prefs", preferences);

			preferences.put("plugins.plugins_disabled", plugins);
		}else {
			logger.debug("Skipping setting custom disable plugins since any plugin was not indicated");
		}
	}
	
	/**
	 * Use this method to set custom Chrome switches options
	 * @throws Exception 
	 * 
	 */
	public void setCustomSwitchesOptions() throws Exception {
		
		if(!type.equals(BrowserTypes.CHROME) && !type.equals(BrowserTypes.CHROME_MOB)) {
			throw new Exception("Custom Switches Options can be only set for Chrome browser, but browser is "+ type);
		}
		
		if ( StringUtils.isNotEmpty(customSwitchesOptions)) {
	        List<String> arguments = OptionsSplitter.split(customSwitchesOptions);
	        options.addArguments(arguments);
	    }else {
	    	logger.debug("Skipping setting custom switches options since any custom switches options was not indicated");
	    }
	}

}