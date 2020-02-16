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
import java.util.Properties;

import static org.assertj.core.api.Assertions.fail;

public class ConfigurationWebDriver extends CustomLogger {
    private static final String FIREFOX = "firefox";
    private static final String CHROME = "chrome";

    public void createWebDriver() {
        WebDriver driver = null;
        String driverName = "";

        String webDriver = driverName.isEmpty() ? getDriverName() : driverName;

        if (FIREFOX.equalsIgnoreCase(webDriver)) {
            driver = getFirefoxDriver();
            info("-----------------USING FIREFOX DRIVER-----------------");
        } else if (CHROME.equalsIgnoreCase(webDriver)) {
            driver = getHeadlessChromeDriver();
            info("-----------------USING CHROME DRIVER-----------------");
        } else
            fail("No Driver was set, go to ConfigurationWebDriver.class and add your browser or Set property 'test.webdriver' in pom.xml to 'chrome' or another browser");
        WebDriverRunner.setWebDriver(driver);
        Configuration.baseUrl = getBaseUrl();
        Configuration.timeout = 4000;
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
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--window-size=1280,1000");
        return new ChromeDriver(chromeOptions);
    }

    private String getDriverName() {
        Properties properties = new Properties();
        try {
            properties.load(ConfigurationWebDriver.class.getClassLoader().getResourceAsStream("ConfigurationWebDriver.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("test.webdriver");
    }

    public static String getBaseUrl() {
        Properties properties = new Properties();
        try {
            properties.load(ConfigurationWebDriver.class.getClassLoader().getResourceAsStream("ConfigurationWebDriver.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
        return properties.getProperty("server");
    }
}
