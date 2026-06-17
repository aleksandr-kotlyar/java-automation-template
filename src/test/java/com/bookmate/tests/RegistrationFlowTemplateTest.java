package com.bookmate.tests;

import com.bookmate.pages.HomePage;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RegistrationFlowTemplateTest extends BaseWebTest {
    private static final String EMAIL = "template@example.test";
    private static final String PASSWORD = "test12";

    private final HomePage homePage = new HomePage();

    @Test
    public void shouldShowSubmitButtonAfterRequiredFieldsAreFilled() {
        homePage.open(templateRegistrationPage())
                .openAuthDialog()
                .chooseEmailRegistration()
                .fillRequiredFields(EMAIL, PASSWORD)
                .shouldShowSubmitButton();
    }

    private static String templateRegistrationPage() {
        String html = """
                <!doctype html>
                <html lang="en">
                  <head>
                    <meta charset="utf-8">
                    <title>Registration template</title>
                  </head>
                  <body>
                    <button data-test-id="login-button" onclick="document.getElementById('auth').hidden = false">Log in</button>

                    <section id="auth" hidden>
                      <button data-test-id="auth-with-email" onclick="document.getElementById('registration').hidden = false">Email</button>
                    </section>

                    <section id="registration" hidden>
                      <input data-test-id="credential-input-email" type="email" oninput="updateSubmit()">
                      <input data-test-id="credential-input-password" type="password" oninput="updateSubmit()">

                      <input id="signup-checkboxes-legal" type="checkbox" onchange="updateSubmit()">
                      <label for="signup-checkboxes-legal">Legal terms</label>

                      <input id="signup-checkboxes-age" type="checkbox" onchange="updateSubmit()">
                      <label for="signup-checkboxes-age">Age confirmation</label>

                      <button data-test-id="credential-submit" hidden>Submit</button>
                    </section>

                    <script>
                      function updateSubmit() {
                        const email = document.querySelector('[data-test-id="credential-input-email"]').value;
                        const password = document.querySelector('[data-test-id="credential-input-password"]').value;
                        const legal = document.getElementById('signup-checkboxes-legal').checked;
                        const age = document.getElementById('signup-checkboxes-age').checked;
                        document.querySelector('[data-test-id="credential-submit"]').hidden =
                          !(email && password && legal && age);
                      }
                    </script>
                  </body>
                </html>
                """;
        return "data:text/html;base64," + Base64.getEncoder().encodeToString(html.getBytes(StandardCharsets.UTF_8));
    }
}
