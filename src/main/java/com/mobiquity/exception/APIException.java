package com.mobiquity.exception;

/**
 * Defines exception which occurs when there is some incorrect situations are present in the system:
 * 1) File is not present by provided filePath
 * 2) Lines in the file are not valid
 * 3) One of constraints (max package weight, max amount of items, max of item cost and max item weight) is failed
 *
 * @author igor.sila (isila)
 */
public class APIException extends Exception {

    /**
     * Constructor with exception message and cause of exception
     *
     * @param message the exception message
     * @param e       the cause of current exception e.g. hte parent exception
     */
    public APIException(String message, Exception e) {
        super(message, e);
    }

    /**
     * Constructor with exception message
     *
     * @param message the exception message
     */
    public APIException(String message) {
        super(message);
    }
}
