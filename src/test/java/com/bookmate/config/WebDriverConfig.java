package com.bookmate.config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class WebDriverConfig {
    private static final Logger LOGGER = Logger.getLogger(WebDriverConfig.class.getName());
    private static final String CONFIG_FILE = "webdriver.properties";
    private static final String FIREFOX = "firefox";
    private static final String CHROME = "chrome";

    public void configure() {
        String browser = getDriverName();
        validateBrowser(browser);

        Configuration.browser = browser.toLowerCase();
        Configuration.headless = true;
        Configuration.browserSize = "1280x1000";
        Configuration.baseUrl = getBaseUrl();
        Configuration.timeout = 6000;
        Configuration.screenshots = true;

        if (CHROME.equalsIgnoreCase(browser)) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            Configuration.browserCapabilities = chromeOptions;
        }

        LOGGER.info("-----------------USING " + browser.toUpperCase() + " DRIVER-----------------");
    }

    public static String getBaseUrl() {
        return getProperty("server", "https://bookmate.com");
    }

    private static String getDriverName() {
        return getProperty("test.webdriver", CHROME);
    }

    private static void validateBrowser(String browser) {
        if (!CHROME.equalsIgnoreCase(browser) && !FIREFOX.equalsIgnoreCase(browser)) {
            throw new IllegalArgumentException(
                    "Unsupported test.webdriver value: " + browser + ". Expected 'chrome' or 'firefox'.");
        }
    }

    private static String getProperty(String name, String defaultValue) {
        String systemValue = System.getProperty(name);
        if (isUsableValue(systemValue)) {
            return systemValue;
        }

        String fileValue = loadProperties().getProperty(name);
        if (isUsableValue(fileValue)) {
            return fileValue;
        }

        return defaultValue;
    }

    private static boolean isUsableValue(String value) {
        return value != null && !value.isBlank() && !value.startsWith("${");
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = WebDriverConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new IllegalStateException(CONFIG_FILE + " not found in test resources");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load " + CONFIG_FILE, e);
        }
        return properties;
    }
}
