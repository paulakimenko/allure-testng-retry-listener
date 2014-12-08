package com.github.paulakimenko.alluretestng.retrylistener;

import org.testng.ITestResult;

import java.util.Arrays;

/**
 * Utils methods for <code>RetryListener</code>
 */
public final class RetryUtils {
    private RetryUtils() {}

    /**
     * Generate unique hashcode from given <code>ITestResult</code>. Has ability to work with
     * parametrized tests with <code>DataProvider</code>.
     * <p>
     * But for now there are some issues with random generated data!
     * It's recommended not to use <code>RetryAnalyzer</code> in this cases.
     * @param result <code>ITestResult</code> containing information about the run test.
     * @return unique hashcode for given <code>ITestResult</code>.
     */
    public static int getResultHash(ITestResult result) {
        int id = result.getTestClass().getName().hashCode();
        id = 31 * id + result.getMethod().getMethodName().hashCode();
        id = 31 * id + (result.getParameters() != null ? Arrays.hashCode(result.getParameters()) : 0);
        return id;
    }
}
