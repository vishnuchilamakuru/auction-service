package com.foobar.exception;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class FooBarException extends Exception {
    private String errorCode;

    private String message;

    private Integer statusCode;

    public FooBarException(final String errorCode, final String message, final Integer statusCode) {
        super(message);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }

}
