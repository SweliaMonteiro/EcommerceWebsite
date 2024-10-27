package com.ecommerce.orderservice.exceptions;

public class InvalidOrderStatusException extends Exception {
    public InvalidOrderStatusException(String message) {
        super(message);
    }
}
