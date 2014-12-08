package com.github.paulakimenko.alluretestng.retrylistener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Extends <code>IRetryAnalyzer</code> functionality
 */
public interface IAllureRetryAnalyzer extends IRetryAnalyzer {
    /**
     * Returns true if the test method has to be retried, false otherwise, but doesn't
     * affect counters or another retry logic if <code>getRetryAbilityOnly</code> is true.
     * <p>
     * With <code>getRetryAbilityOnly</code> - false must work like <code>retry</code> method
     * from <code>IRetryAnalyzer</code>.
     * @param getRetryAbilityOnly if it's true, method invocation doesn't affect retry logic.
     * @param result The result of the test method that just ran.
     * @return true if the test method has to be retried, false otherwise.
     */
    boolean retry(ITestResult result, boolean getRetryAbilityOnly);
}
