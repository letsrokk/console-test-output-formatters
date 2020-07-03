package com.github.letsrokk.testng;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IConfigurationListener2;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

public class TestNGConsoleProgressListener implements ITestListener, IConfigurationListener2 {

    final private Logger logger = LogManager.getLogger(TestNGConsoleProgressListener.class);

    @Override
    public void onTestStart(ITestResult tr) {
        logger.debug("=========================================================");
        logger.debug("=== TESTNG: ON TEST START: " + tr.toString());
        logger.debug("=========================================================");
        int testCounter = increaseTestCounter();
        logger.info("START #" + testCounter + ": " + generateMethodSignature(tr));
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        logger.debug("=========================================================");
        logger.debug("=== TESTNG: ON TEST SUCCESS: " + tr.toString());
        logger.debug("=========================================================");
        int testCounter = increaseTestFinishedCounter();
        logger.info("SUCCESS #" + testCounter + ": " + generateMethodSignature(tr));
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        logTestFailure(tr);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {
        logTestFailure(tr);
    }

    private void logTestFailure(ITestResult tr) {
        logger.debug("=========================================================");
        logger.debug("=== TESTNG: ON TEST FAILURE: " + tr.toString());
        logger.debug("=========================================================");
        int testCounter = increaseTestFinishedCounter();
        String error_msg = Functions.extractMessageError(tr.getThrowable());
        logger.error("FAIL  #" + testCounter + ": " + generateMethodSignature(tr) + ": " + error_msg);
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        logger.debug("=========================================================");
        logger.debug("=== TESTNG: ON TEST SKIPPED: " + tr.toString());
        logger.debug("=========================================================");
        int testCounter = increaseTestFinishedCounter();
        logger.warn("SKIP  #" + testCounter + ": " + generateMethodSignature(tr));
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        logger.debug("=========================================================");
        logger.debug("=== TESTNG: ON START CALL");
        long startTimer = System.currentTimeMillis();
        long seconds = TimeUnit.SECONDS.convert(System.currentTimeMillis() - startTimer, TimeUnit.MILLISECONDS);
        logger.debug("=== " + seconds + "s");
        logger.debug("=========================================================");
    }

    @Override
    public void onFinish(ITestContext testContext) {
        logger.debug("=========================================================");
        logger.debug("=== TESTNG: ON FINISH CALL");
        long startTimer = System.currentTimeMillis();
        long seconds = TimeUnit.SECONDS.convert(System.currentTimeMillis() - startTimer, TimeUnit.MILLISECONDS);
        logger.debug("=== " + seconds + "s");
        logger.debug("=========================================================");
    }

    private static int test_counter = 0;

    private static synchronized int increaseTestCounter() {
        test_counter++;
        return test_counter;
    }

    private static int test_finished_counter = 0;

    private static synchronized int increaseTestFinishedCounter() {
        test_finished_counter++;
        return test_finished_counter;
    }

    private String generateMethodSignature(ITestResult testResult) {
        return testResult.getMethod().getMethodName() + " (" + Functions.getTestParametersString(testResult) + ")";
    }

    @Override
    public void beforeConfiguration(ITestResult tr) {
        logger.debug("=========================================================");
        logger.debug("=== TESTNG: BEFORE CONFIGURATION: " + tr.toString());
        logger.debug("=========================================================");
    }

    @Override
    public void onConfigurationSuccess(ITestResult tr) {
        logger.debug("=========================================================");
        logger.debug("=== TESTNG: CONFIGURATION SUCCESS: " + tr.toString());
        logger.debug("=========================================================");
    }

    @Override
    public void onConfigurationFailure(ITestResult tr) {
        logger.error("CONFIGURATION FAILURE: " + tr.getName() + ": " + tr.getThrowable().getMessage());
        logger.debug("=========================================================");
        logger.debug("=== TESTNG: CONFIGURATION FAILURE: " + tr.toString());
        logger.debug("=========================================================");
    }

    @Override
    public void onConfigurationSkip(ITestResult tr) {
        logger.warn("CONFIGURATION SKIPPED: " + tr.getName());
        logger.debug("=========================================================");
        logger.debug("=== TESTNG: CONFIGURATION SKIP: " + tr.toString());
        logger.debug("=========================================================");
    }
}