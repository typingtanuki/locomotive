package com.github.typingtanuki.locomotive.utils;

public class ProcessFailedException extends Exception {
    public ProcessFailedException(String message) {
        super(message);
    }

    public ProcessFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
