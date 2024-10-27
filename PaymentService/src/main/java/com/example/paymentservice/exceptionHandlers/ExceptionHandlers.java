package com.example.paymentservice.exceptionHandlers;

import com.example.paymentservice.dtos.ExceptionDto;
import com.example.paymentservice.exceptions.*;
import com.razorpay.RazorpayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


// This class handles the exceptions and send appropriate responses to the client

@ControllerAdvice
public class ExceptionHandlers {

    // This method handles OrderNotFoundException and sends a response with the exception message and status code 404
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleOrderNotFoundException(OrderNotFoundException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }


    // This method handles PaymentNotFoundException and sends a response with the exception message and status code 404
    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ExceptionDto> handlePaymentNotFoundException(PaymentNotFoundException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }


    // This method handles InvalidPaymentModeException and sends a response with the exception message and status code 400
    @ExceptionHandler(InvalidPaymentModeException.class)
    public ResponseEntity<ExceptionDto> handleInvalidPaymentModeException(InvalidPaymentModeException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }


    // This method handles RazorpayException and sends a response with the exception message and status code 400
    @ExceptionHandler(RazorpayException.class)
    public ResponseEntity<ExceptionDto> handleRazorpayException(RazorpayException ex) {
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
