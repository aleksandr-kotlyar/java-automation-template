package com.bookmate.tests;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.SelenideElement;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AllureHelpers {

    @Attachment(value = "AllureTextReport", type = "text/plain")
    public static String attachText(String text) {
        return text;
    }

    @Attachment(value = "html source", type = "text/html")
    private byte[] getHtmlSource() throws IOException {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "image screenshot", type = "image/png")
    private byte[] saveScreenshot() throws IOException {
        File file = Screenshots.takeScreenShotAsFile();
        return Files.toByteArray(file);
    }

    @Attachment(value = "element screenshot")
    public static byte[] takeScreenshot(SelenideElement elem) {
        return elem.getScreenshotAs(OutputType.BYTES);
    }

    protected void takeScreenShots() {
        try {
            getHtmlSource();
            saveScreenshot();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
