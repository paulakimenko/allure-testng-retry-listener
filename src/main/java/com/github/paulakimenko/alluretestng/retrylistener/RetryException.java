package com.github.paulakimenko.alluretestng.retrylistener;

import org.testng.SkipException;

import java.io.Serializable;

/**
 * Extends <code>SkipException</code> for retry.
 */
public class RetryException extends SkipException implements Serializable {
    private static final long serialVersionUID = 6543627546732546750L;

    public RetryException(String message) {
        super(message);
    }

    public RetryException(String message, Throwable cause) {
        super(message, cause);
        reduceStackTrace();
    }
}
