package com.bookmate.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.assertj.core.api.Assertions.fail;

public class ConfigurationWebDriver extends CustomLogger {
    private static final String FIREFOX = "firefox";
    private static final String CHROME = "chrome";

    public void createWebDriver() {
        WebDriver driver = null;
        String webDriver = getDriverName();

        if (FIREFOX.equalsIgnoreCase(webDriver)) {
            driver = getFirefoxDriver();
            info("-----------------USING FIREFOX DRIVER-----------------");
        } else if (CHROME.equalsIgnoreCase(webDriver)) {
            driver = getHeadlessChromeDriver();
            info("-----------------USING CHROME DRIVER-----------------");
        } else {
            fail("No Driver was set, set property 'test.webdriver' in ConfigurationWebDriver.properties to 'chrome' or 'firefox'");
        }

        WebDriverRunner.setWebDriver(driver);
        Configuration.baseUrl = getBaseUrl();
        Configuration.timeout = 6000;
        Configuration.screenshots = true;
    }

    private WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1280, 1000));
        return driver;
    }

    private WebDriver getHeadlessChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--window-size=1280,1000");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        return new ChromeDriver(chromeOptions);
    }

    private String getDriverName() {
        Properties properties = loadProperties();
        return properties.getProperty("test.webdriver", CHROME);
    }

    public static String getBaseUrl() {
        Properties properties = loadProperties();
        return properties.getProperty("server", "https://bookmate.com");
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = ConfigurationWebDriver.class.getClassLoader()
                .getResourceAsStream("ConfigurationWebDriver.properties")) {
            if (input == null) {
                Assert.fail("ConfigurationWebDriver.properties not found in test resources");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load ConfigurationWebDriver.properties", e);
        }
        return properties;
    }
}
