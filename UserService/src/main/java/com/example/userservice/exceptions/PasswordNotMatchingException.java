package com.example.userservice.exceptions;

public class PasswordNotMatchingException extends Exception {
    public PasswordNotMatchingException(String message) {
        super(message);
    }
}
