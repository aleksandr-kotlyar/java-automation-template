package com.bookmate.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Test;


public class TestTriageSO extends AbstractWebTest {

    /**
     * Triage StackOverFlow question:
     * https://stackoverflow.com/questions/64335955/how-to-check-page-url-with-selenide
     * How to check page url in selenide ?
     * For example I should check if url() is www.google.pl
     * Thanks
     */
    @Test
    public void testNavigationUrlIsProper() {
        String url = "https://www.google.pl/";
        Selenide.open(url);
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        org.junit.Assert.assertEquals("not equal", url, currentUrl);
    }
}
