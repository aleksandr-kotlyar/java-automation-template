package com.bookmate.pages;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;

public class AuthDialog {
    public RegistrationForm chooseEmailRegistration() {
        $("[data-test-id='auth-with-email']").shouldBe(clickable).click();
        return new RegistrationForm();
    }
}
