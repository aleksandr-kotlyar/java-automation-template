package com.bookmate.tests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.screenshot;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class AbstractWebTest extends ConfigurationWebDriver {

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        AllureHelpers allure = new AllureHelpers();

        @Override
        protected void failed(Throwable e, Description description) {
            if (hasWebDriverStarted()) {
                screenshot(description.getClassName() + "_"
                        + description.getMethodName() + "_"
                        + System.currentTimeMillis());
                allure.takeScreenShots();
            }
        }

        @Override
        protected void finished(Description description) { closeWebDriver();
        }
    };

    @Before
    public void setUp() throws Exception {
        createWebDriver();
    }

}
