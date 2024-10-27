package com.example.orderservice.exceptions;

public class InvalidPaymentStatusException extends Exception {
    public InvalidPaymentStatusException(String message) {
        super(message);
    }
}
