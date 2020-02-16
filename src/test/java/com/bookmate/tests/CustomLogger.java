package com.bookmate.tests;

import io.qameta.allure.Step;

import java.util.logging.Logger;

public class CustomLogger {


    private static final Logger LOGGER = Logger.getLogger(AbstractWebTest.class.getName());

    public void step(String step, Runnable r) {
        try {
            r.run();
            info("STEP PASSED: " + step);
        } catch (Throwable t) {
            info("STEP FAILED: " + step + t);
            throw t;
        }
    }

    @Step
    public void assertion(String assertion, Runnable r) {
        try {
            r.run();
            info("Assertion PASSED: " + assertion);
        } catch (Throwable t) {
            info("Assertion FAILED: " + assertion + "\n" + t);
            throw t;
        }
    }

    @Step
    public void act(String act, Runnable r) {
        try {
            r.run();
            info("Act PASSED: " + act);
        } catch (Throwable t) {
            info("Act FAILED: " + act + "\n" + t);
            throw t;
        }
    }

    @Step
    public void arrange(String arrange, Runnable r) {
        try {
            r.run();
            info("Arrange PASSED: " + arrange);
        } catch (Throwable t) {
            info("Arrange FAILED: " + arrange + "\n" + t);
            throw t;
        }
    }

    public void fine(String logMessage) {
        getLoggerInstance().fine(logMessage);
    }

    public void info(String logMessage) {
        getLoggerInstance().info(logMessage);
    }

    public void warning(String logMessage) {
        getLoggerInstance().warning(logMessage);
    }

    public Logger getLoggerInstance() {
        return LOGGER;
    }

    /**
     * Sweet method to get test method status to info.
     * Stack depth should be more than one, because wanted element is under index 1 of element's array.
     * Element with 0 index is current method, e.a. infoTestLog().
     */
    public void infoTestStatus(String testNumber, String status) {

        Throwable t = new Throwable();
        StackTraceElement[] trace = t.getStackTrace();
        if (status.equals("START")) {
            if (trace.length > 1) {
                StackTraceElement element = trace[1];

                // TEST #### test1() START |##| com.project...ClassNameTest.java

                info("TEST " + testNumber + " " + element.getMethodName() + "() "
                        + "START " + " " + element.getClassName() + "|" + element.getLineNumber() + "|");
            } else {
                StackTraceElement element = trace[1];
                info("TEST " + testNumber + " " + element.getMethodName() + "() " + "FAILED!");
            }
        }
        if (status.equals("END")) {
            if (trace.length > 1) {
                StackTraceElement element = trace[1];

                // TEST #### test1() PASSED |##| com.project...ClassNameTest.java

                info("TEST " + testNumber + " " + element.getMethodName() + "() "
                        + "PASSED " + " " + element.getClassName() + "|" + element.getLineNumber() + "|");
            } else {
                StackTraceElement element = trace[1];
                info("TEST " + testNumber + " " + element.getMethodName() + "() " + "FAILED!");
            }
        }
    }
}
