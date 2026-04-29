# Java UI Automation Template

Small Java + Selenide template for UI tests with optional Allure attachments.

## Requirements

- JDK 17+ (CI runs on JDK 17 and 21)
- Maven 3.9+

## Run locally

```bash
mvn -B clean test
```

## Configuration

Driver and base URL come from test resources:

- `src/test/resources/ConfigurationWebDriver.properties`
- `test.webdriver` supports `chrome` (default) and `firefox`
- `server` sets the base URL used by tests

## CI

GitHub Actions workflow includes:

- `build` job: compile/package on JDK 17 and 21
- `ui-smoke` job: headless Chrome smoke test (`TestTriageSO`)
