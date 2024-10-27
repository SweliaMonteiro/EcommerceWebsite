package com.ecommerce.orderservice.exceptions;

public class InvalidPaymentStatusException extends Exception {
    public InvalidPaymentStatusException(String message) {
        super(message);
    }
}
