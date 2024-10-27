package com.ecommerce.paymentservice.exceptions;

public class PaymentNotFoundException extends Exception {
    public PaymentNotFoundException(String message) {
        super(message);
    }
}
