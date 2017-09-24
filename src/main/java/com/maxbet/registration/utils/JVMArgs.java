package com.maxbet.registration.utils;

/**
 * @author Atanas Kanchev
 *         JVM arguments
 */
public enum JVMArgs {

    BROWSER_TYPE("browser"),
    BROWSER_RESOLUTION("resolution"),

    // Grid
    BROWSER_VERSION("browser.version"),
    PLATFORM_NAME("platform.name"),
    IS_GRID_EXECUTION("grid"),
    HUB_URL("hub.url"),

    // Custom capabilities
    CUSTOM_CAPABILITY("capability"),
    // Custom switches options
    CUSTOM_OPTIONS("options"),
    // Custom switches options
    CUSTOM_DISABLE_PLUGINS("disablePlugins"),
    
    // Mobile
    USER_AGENT("user.agent"),

    // Binary versions
    CHROME_BINARY_VERSION("chrome.bin.version"),
    IE_BINARY_VERSION("ie.bin.version"),
    PHANTOM_BINARY_VERSION("phantom.bin.version"),

    // Screen shots
    SCREEN_SHOTS_DIR("webdriver.screenshot.dir");



    private final String name;

    JVMArgs(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Get JVM argument
     *
     * @param arg JVMArgs enum
     * @return String
     */
    public static String getJMVArg(JVMArgs arg) {
        return System.getProperty(arg.getName());
    }

    /**
     * Set JVM argument
     *
     * @param arg   JVMArgs enum
     * @param value String
     */
    public static void setJMVArg(JVMArgs arg, String value) {
        System.setProperty(arg.getName(), value);
    }
}
