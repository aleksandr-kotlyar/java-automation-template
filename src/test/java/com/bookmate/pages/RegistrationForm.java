package com.bookmate.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationForm {
    public RegistrationForm fillRequiredFields(String email, String password) {
        $("[data-test-id='credential-input-email']").setValue(email);
        $("[data-test-id='credential-input-password']").sendKeys(password);
        $("[for='signup-checkboxes-legal']").click();
        $("[for='signup-checkboxes-age']").click();
        return this;
    }

    public void shouldShowSubmitButton() {
        $("[data-test-id='credential-submit']").shouldBe(visible);
    }
}
