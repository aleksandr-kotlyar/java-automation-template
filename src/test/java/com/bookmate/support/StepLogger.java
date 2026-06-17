package com.bookmate.support;

import io.qameta.allure.Step;

import java.util.logging.Logger;
import java.util.function.Supplier;

public class StepLogger {
    private static final Logger LOGGER = Logger.getLogger(StepLogger.class.getName());

    @Step
    public void arrange(String arrange, Runnable action) {
        runStep("Arrange", arrange, action);
    }

    @Step
    public <T> T arrange(String arrange, Supplier<T> action) {
        return runStep("Arrange", arrange, action);
    }

    @Step
    public void act(String act, Runnable action) {
        runStep("Act", act, action);
    }

    @Step
    public <T> T act(String act, Supplier<T> action) {
        return runStep("Act", act, action);
    }

    @Step
    public void assertion(String assertion, Runnable action) {
        runStep("Assertion", assertion, action);
    }

    @Step
    public <T> T assertion(String assertion, Supplier<T> action) {
        return runStep("Assertion", assertion, action);
    }

    public void infoTestStatus(String testNumber, String status) {
        StackTraceElement caller = new Throwable().getStackTrace()[1];
        info("TEST " + testNumber + " " + caller.getMethodName() + "() "
                + status + " " + caller.getClassName() + "|" + caller.getLineNumber() + "|");
    }

    public void info(String logMessage) {
        LOGGER.info(logMessage);
    }

    public void warning(String logMessage) {
        LOGGER.warning(logMessage);
    }

    private void runStep(String type, String description, Runnable action) {
        runStep(type, description, () -> {
            action.run();
            return null;
        });
    }

    private <T> T runStep(String type, String description, Supplier<T> action) {
        try {
            T result = action.get();
            info(type + " PASSED: " + description);
            return result;
        } catch (Throwable t) {
            info(type + " FAILED: " + description + "\n" + t);
            throw t;
        }
    }
}
