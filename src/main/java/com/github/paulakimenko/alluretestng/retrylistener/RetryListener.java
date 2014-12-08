package com.github.paulakimenko.alluretestng.retrylistener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.events.TestCaseFinishedEvent;
import ru.yandex.qatools.allure.events.TestCasePendingEvent;

/**
 * Implementation of <code>ITestListener</code> to allow retry functionality for
 * combination of <code>Allure Framework</code> and <code>TestNG Framework</code>.
 */
public class RetryListener implements ITestListener {
    Allure lifecycle = Allure.LIFECYCLE;

    /**
     * Invoked each time before a test will be invoked.
     * The <code>ITestResult</code> is only partially filled with the references to
     * class, method, start millis and status.
     *
     * @param result the partially filled <code>ITestResult</code>
     * @see ITestResult#STARTED
     */
    @Override
    public void onTestStart(ITestResult result) {
        // ignored
    }

    /**
     * Invoked each time a test succeeds.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        // ignored
    }

    /**
     * Invoked each time a test fails.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#FAILURE
     */
    @Override
    public void onTestFailure(ITestResult result) {
        fireRetryTest("The test has been failed then retried.", result);
    }

    /**
     * Invoked each time a test is skipped.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SKIP
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        // ignored
    }

    /**
     * Invoked each time a method fails but has been annotated with
     * successPercentage and this failure still keeps it within the
     * success percentage requested.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        fireRetryTest("The test has been failed (within success percentage) then retried.", result);
    }

    /**
     * Invoked after the test class is instantiated and before
     * any configuration method is called.
     */
    @Override
    public void onStart(ITestContext context) {
        // ignored
    }

    /**
     * Invoked after all the tests have run and all their
     * Configuration methods have been called.
     */
    @Override
    public void onFinish(ITestContext context) {
        // ignored
    }

    /**
     * Fires Retry test if it allowed by <code>RetryAnalyzer</code>
     * and set 'Pending' status for test result.
     * @param message <code>String</code> message which will thrown like explanation
     * @param result <code>ITestResult</code> containing information about the run test
     */
    protected void fireRetryTest(String message, ITestResult result) {
        if (((IAllureRetryAnalyzer) result.getMethod().getRetryAnalyzer()).retry(result, true)) {
            Throwable throwable = new RetryException(message, result.getThrowable());
            getLifecycle().fire(new TestCasePendingEvent().withThrowable(throwable));
            getLifecycle().fire(new TestCaseFinishedEvent());
        }
    }

    /**
     * Get current Allure lifecycle
     * @return current Allure lifecycle
     */
    protected Allure getLifecycle() {
        return lifecycle;
    }
}
