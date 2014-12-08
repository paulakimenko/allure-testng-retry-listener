package com.github.paulakimenko.alluretestng.retrylistener;

import org.testng.ITestResult;

/**
 * Abstract implementation of <code>IAllureRetryAnalyzer</code> to
 * implement <code>retry(ITestResult result)</code> method.
 */
public abstract class AbstractAllureRetryAnalyzer implements IAllureRetryAnalyzer {
    @Override
    public boolean retry(ITestResult result) {
        return retry(result, false);
    }
}
