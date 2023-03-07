package com.example.demo.exception;

public class AlreadyUsedEmailException extends RuntimeException {
    public AlreadyUsedEmailException(String message) {
        super(message);
    }
}
