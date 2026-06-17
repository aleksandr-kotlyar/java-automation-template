# Java UI Automation Template

Small Java + Selenide template for UI tests with optional Allure attachments.

## Requirements

- JDK 21+ (CI runs on JDK 21 and 25)
- Maven 3.9+

Oracle currently lists JDK 26 as the latest Java SE release and JDK 25 as the
latest LTS. This project targets Java 21 bytecode for compatibility while
checking the build on Java 25 as the fresh LTS runtime.

## Run locally

Run the stable non-production test set:

```bash
mvn -B -ntp test
```

Run all tests, including production/live UI tests:

```bash
mvn -B -ntp -Pall-tests test
```

Run only production/live UI tests:

```bash
mvn -B -ntp -Pproduction-tests test
```

## Configuration

Driver and base URL come from test resources:

- `src/test/resources/webdriver.properties`
- `test.webdriver` supports `chrome` (default) and `firefox`
- `server` sets the base URL used by tests
- System properties override `webdriver.properties`, for example
  `mvn -Dserver=https://example.test test`

If local Chrome is newer than Selenium's bundled CDP support, Selenium may print
a non-blocking CDP version warning. Prefer updating Selenide/Selenium when a
compatible release is available instead of pinning a machine-specific DevTools
artifact in the template.

## Test Categories

JUnit 4 categories are used to separate stable project checks from tests that hit
external production-like services.

- `com.bookmate.categories.ProductionTest`: tests against live external
  services, such as Bookmate production pages. These tests can be blocked by
  rate limits, bot protection, or production UI changes.

Production tests are excluded by default so the template stays stable in local
development and CI. Use `-Pproduction-tests` or `-Pall-tests` when you explicitly
want to include live external checks.

## Test Structure

- `com.bookmate.tests`: test classes and shared test lifecycle
- `com.bookmate.pages`: page objects and UI flows
- `com.bookmate.config`: Selenide WebDriver configuration
- `com.bookmate.support`: reporting, attachments, and step logging helpers

## CI

GitHub Actions workflow includes:

- `build` job: compile/package on JDK 21 and 25
- `ui-non-production` job: headless Chrome tests with default non-production profile
