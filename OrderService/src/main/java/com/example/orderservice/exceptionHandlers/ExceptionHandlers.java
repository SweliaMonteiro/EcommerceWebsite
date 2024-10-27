package com.example.orderservice.exceptionHandlers;

import com.example.orderservice.dtos.ExceptionDto;
import com.example.orderservice.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


// This class handles the exceptions and send appropriate responses to the client

@ControllerAdvice
public class ExceptionHandlers {

    // This method handles CartItemNotFoundException and sends a response with the exception message and status code 404
    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleCartItemNotFoundException(CartItemNotFoundException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }


    // This method handles OrderNotFoundException and sends a response with the exception message and status code 404
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleOrderNotFoundException(OrderNotFoundException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }


    // This method handles UserNotFoundException and sends a response with the exception message and status code 404
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleUserNotFoundException(UserNotFoundException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }


    // This method handles InvalidOrderStatusException and sends a response with the exception message and status code 400
    @ExceptionHandler(InvalidOrderStatusException.class)
    public ResponseEntity<ExceptionDto> handleInvalidOrderStatusException(InvalidOrderStatusException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }


    // This method handles InvalidPaymentStatusException and sends a response with the exception message and status code 400
    @ExceptionHandler(InvalidPaymentStatusException.class)
    public ResponseEntity<ExceptionDto> handleInvalidPaymentStatusException(InvalidPaymentStatusException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }


    // This method handles InvalidPaymentModeException and sends a response with the exception message and status code 400
    @ExceptionHandler(InvalidPaymentModeException.class)
    public ResponseEntity<ExceptionDto> handleInvalidPaymentModeException(InvalidPaymentModeException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }


    // This method handles all other exceptions and sends a response with the exception message and status code 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleAllExceptions(Exception ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
