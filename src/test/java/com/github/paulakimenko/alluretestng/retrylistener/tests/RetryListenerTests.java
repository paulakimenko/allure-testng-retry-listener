package com.github.paulakimenko.alluretestng.retrylistener.tests;

import com.github.paulakimenko.alluretestng.retrylistener.AbstractAllureRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class RetryListenerTests {
    private static final int MAX_RETRY_COUNT = 1;

    @Test
    public void test() {
        // TBD
    }

    public static class TestRetryAnalyzer extends AbstractAllureRetryAnalyzer {
        private int retryCount;

        @Override
        public boolean retry(ITestResult result, boolean getRetryAbilityOnly) {
            if (retryCount < MAX_RETRY_COUNT) {
                if (!getRetryAbilityOnly)
                    retryCount++;
                return true;
            }

            return false;
        }
    }
}
