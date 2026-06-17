package com.bookmate.tests;

import com.bookmate.config.WebDriverConfig;
import com.bookmate.support.AllureAttachments;
import com.bookmate.support.StepLogger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.screenshot;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public abstract class BaseWebTest {
    private final WebDriverConfig webDriverConfig = new WebDriverConfig();
    private final AllureAttachments allureAttachments = new AllureAttachments();

    protected final StepLogger steps = new StepLogger();

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            if (hasWebDriverStarted()) {
                screenshot(description.getClassName() + "_"
                        + description.getMethodName() + "_"
                        + System.currentTimeMillis());
                allureAttachments.attachPageSourceAndScreenshot();
            }
        }

        @Override
        protected void finished(Description description) {
            closeWebDriver();
        }
    };

    @Before
    public void setUp() {
        webDriverConfig.configure();
    }

    protected String baseUrl() {
        return WebDriverConfig.getBaseUrl();
    }
}
