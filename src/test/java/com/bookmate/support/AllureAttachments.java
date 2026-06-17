package com.bookmate.support;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.SelenideElement;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AllureAttachments {
    private static final Logger LOGGER = Logger.getLogger(AllureAttachments.class.getName());

    @Attachment(value = "AllureTextReport", type = "text/plain")
    public static String attachText(String text) {
        return text;
    }

    @Attachment(value = "element screenshot")
    public static byte[] takeScreenshot(SelenideElement elem) {
        return elem.getScreenshotAs(OutputType.BYTES);
    }

    public void attachPageSourceAndScreenshot() {
        try {
            getHtmlSource();
            saveScreenshot();
        } catch (IOException | RuntimeException e) {
            LOGGER.warning("Failed to attach page source or screenshot to Allure: " + e.getMessage());
        }
    }

    @Attachment(value = "html source", type = "text/html")
    private byte[] getHtmlSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "image screenshot", type = "image/png")
    private byte[] saveScreenshot() throws IOException {
        File file = Screenshots.takeScreenShotAsFile();
        return Files.toByteArray(file);
    }
}
