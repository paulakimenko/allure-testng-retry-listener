package com.github.paulakimenko.alluretestng.retrylistener;

import org.testng.ITestResult;

/**
 * Simple implementation of <code>IAllureRetryAnalyzer</code>.
 * <p>
 * Uses <code>MAX_RETRY_COUNT</code> from System properties.
 * <p>
 * Property : alluretestng.retrylistener.retryCount
 * <p>
 * If this property exist, or is null(empty) retry count will be equals 0.
 * Has ability to work with <code>DataProvider</code>.
 */
public class SimpleRetryAnalyzer extends AbstractAllureRetryAnalyzer {
    public static final String RETRY_COUNT_PROPERTY_KEY = "alluretestng.retrylistener.retryCount";

    private static final int MAX_RETRY_COUNT;
    static {
        String retryCountInString = System.getProperty(RETRY_COUNT_PROPERTY_KEY);
        if (retryCountInString == null || retryCountInString.equals("")) {
            MAX_RETRY_COUNT = 0;
        } else {
            MAX_RETRY_COUNT = Integer.parseInt(retryCountInString);
        }
    }

    private int resultHash;
    private int retryCount;

    @Override
    public boolean retry(ITestResult result, boolean getRetryAbilityOnly) {
        int currentResultHash = RetryUtils.getResultHash(result);

        if (resultHash != currentResultHash) {
            resultHash = currentResultHash;
            retryCount = 0;
        }

        if (retryCount < MAX_RETRY_COUNT) {
            if (!getRetryAbilityOnly)
                retryCount++;
            return true;
        }

        return false;
    }
}
