package com.ecommerce.orderservice.exceptions;

public class InvalidPaymentModeException extends Exception {
    public InvalidPaymentModeException(String message) {
        super(message);
    }
}
