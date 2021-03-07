package com.bookmate.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class TestRegistrationByEmail extends AbstractWebTest {

    /** KISS */
    @Test
    public void testRegisterButtonActivation0() {
        Selenide.open(getBaseUrl());
        $("[data-test-id='login-button']").click();
        sleep(200);

        $("[data-test-id='auth-with-email']").click();
        $("[data-test-id='credential-input-email']").setValue("ttest@btest.test");
        $("[data-test-id='credential-input-password']").sendKeys("test12");
        $("[for='signup-checkboxes-legal']").click();
        $("[for='signup-checkboxes-age']").click();

        $("[data-test-id='credential-submit']").shouldBe(Condition.visible);
    }

    /** Log AAA + KISS */
    @Test
    public void testRegisterButtonActivation1() {
        arrange("Open login page", () -> {
            Selenide.open(getBaseUrl());
            $("[data-test-id='login-button']").click();
            sleep(200);
        });

        act("Fill necessary registration fields", () -> {
            $("[data-test-id='auth-with-email']").click();
            $("[data-test-id='credential-input-email']").setValue("ttest@btest.test");
            $("[data-test-id='credential-input-password']").sendKeys("test12");
            $("[for='signup-checkboxes-legal']").click();
            $("[for='signup-checkboxes-age']").click();
        });

        assertion("Assert credential-submit button is visible", () ->
                $("[data-test-id='credential-submit']").shouldBe(Condition.visible));
    }

    /** Log start/end + Log AAA + KISS */
    @Test
    public void testRegisterButtonActivation2() {
        infoTestStatus("1", "START");

        arrange("Open login page", () -> {
            Selenide.open(getBaseUrl());
            $("[data-test-id='login-button']").click();
            sleep(200);
        });

        act("Fill necessary registration fields", () -> {
            $("[data-test-id='auth-with-email']").click();
            $("[data-test-id='credential-input-email']").setValue("ttest@btest.test");
            $("[data-test-id='credential-input-password']").sendKeys("test12");
            $("[for='signup-checkboxes-legal']").click();
            $("[for='signup-checkboxes-age']").click();
        });

        assertion("Assert credential-submit button is visible", () ->
                $("[data-test-id='credential-submit']").shouldBe(Condition.visible));

        infoTestStatus("1", "END");
    }
}