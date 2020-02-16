package com.bookmate.tests;

import com.codeborne.selenide.Screenshots;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class AbstractWebTest extends ConfigurationWebDriver {

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        AllureHelpers allure = new AllureHelpers();

        @Override
        protected void failed(Throwable e, Description description) {
            if (hasWebDriverStarted()) {
                Screenshots.takeScreenShot(description.getClassName() + "_"
                        + description.getMethodName() + "_"
                        + System.currentTimeMillis());
                allure.takeScreenShots();
            }
        }

        @Override
        protected void finished(Description description) { close();
        }
    };

    @Before
    public void setUp() throws Exception {
        createWebDriver();
    }

}
