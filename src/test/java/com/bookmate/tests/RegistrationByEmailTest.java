package com.bookmate.tests;

import com.bookmate.categories.ProductionTest;
import com.bookmate.pages.HomePage;
import com.bookmate.pages.RegistrationForm;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(ProductionTest.class)
public class RegistrationByEmailTest extends BaseWebTest {
    private static final String EMAIL = "ttest@btest.test";
    private static final String PASSWORD = "test12";

    private final HomePage homePage = new HomePage();

    @Test
    public void shouldShowSubmitButtonAfterRequiredFieldsAreFilled() {
        RegistrationForm registrationForm = steps.arrange("Open registration form", this::openRegistrationForm);

        steps.act("Fill necessary registration fields", () -> {
            registrationForm.fillRequiredFields(EMAIL, PASSWORD);
        });

        steps.assertion("Assert credential-submit button is visible", () ->
                registrationForm.shouldShowSubmitButton());
    }

    private RegistrationForm openRegistrationForm() {
        return homePage.open(baseUrl())
                .openAuthDialog()
                .chooseEmailRegistration();
    }
}
