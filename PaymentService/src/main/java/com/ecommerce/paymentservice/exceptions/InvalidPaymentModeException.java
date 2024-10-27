package com.ecommerce.paymentservice.exceptions;

public class InvalidPaymentModeException extends Exception {
    public InvalidPaymentModeException(String message) {
        super(message);
    }
}
