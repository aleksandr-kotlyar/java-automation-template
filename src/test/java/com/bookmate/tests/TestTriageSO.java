package com.bookmate.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Assert;
import org.junit.Test;

public class TestTriageSO extends AbstractWebTest {

    @Test
    public void testNavigationUrlIsProper() {
        String url = "data:text/html,<html><head><title>smoke</title></head><body>ok</body></html>";
        Selenide.open(url);
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assert.assertTrue("Expected current URL to start with data:text/html, but was: " + currentUrl,
                currentUrl.startsWith("data:text/html"));
    }
}
