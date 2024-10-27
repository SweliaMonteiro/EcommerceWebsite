package com.example.orderservice.exceptions;

public class InvalidPaymentModeException extends Exception {
    public InvalidPaymentModeException(String message) {
        super(message);
    }
}
