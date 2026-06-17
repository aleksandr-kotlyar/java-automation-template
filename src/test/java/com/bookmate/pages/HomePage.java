package com.bookmate.pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    public HomePage open(String baseUrl) {
        Selenide.open(baseUrl);
        return this;
    }

    public AuthDialog openAuthDialog() {
        $("[data-test-id='login-button']").shouldBe(clickable).click();
        return new AuthDialog();
    }
}
