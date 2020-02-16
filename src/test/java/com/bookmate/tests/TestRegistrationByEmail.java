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
        $("#login-button").click();
        sleep(200);

        $("#auth-with-email").click();
        $("#credential-input-email").setValue("ttest@btest.test");
        $("#credential-input-password").sendKeys("test");
        $("[for='signup-checkboxes-legal']").click();
        $("[for='signup-checkboxes-age']").click();

        $("#credential-submit").shouldBe(Condition.visible);
    }

    /** Log AAA + KISS */
    @Test
    public void testRegisterButtonActivation1() {
        arrange("Open login page", () -> {
            Selenide.open(getBaseUrl());
            $("#login-button").click();
            sleep(200);
        });

        act("Fill necessary registration fields", () -> {
            $("#auth-with-email").click();
            $("#credential-input-email").setValue("ttest@btest.test");
            $("#credential-input-password").sendKeys("test");
            $("[for='signup-checkboxes-legal']").click();
            $("[for='signup-checkboxes-age']").click();
        });

        assertion("Assert credential-submit button is visible", () ->
                $("#credential-submit").shouldBe(Condition.visible));
    }

    /** Log start/end + Log AAA + KISS */
    @Test
    public void testRegisterButtonActivation2() {
        infoTestStatus("1", "START");

        arrange("Open login page", () -> {
            Selenide.open(getBaseUrl());
            $("#login-button").click();
            sleep(200);
        });

        act("Fill necessary registration fields", () -> {
            $("#auth-with-email").click();
            $("#credential-input-email").setValue("ttestt@ttest.test");
            $("#credential-input-password").sendKeys("test");
            $("[for='signup-checkboxes-legal']").click();
            $("[for='signup-checkboxes-age']").click();
        });

        assertion("Assert credential-submit button is visible", () ->
                $("#credential-submit").shouldBe(Condition.visible));

        infoTestStatus("1", "END");
    }
}