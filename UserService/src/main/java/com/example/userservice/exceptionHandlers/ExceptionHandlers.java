package com.example.userservice.exceptionHandlers;

import com.example.userservice.dtos.ExceptionDto;
import com.example.userservice.exceptions.InvalidTokenException;
import com.example.userservice.exceptions.PasswordNotMatchingException;
import com.example.userservice.exceptions.UserAlreadyExistsException;
import com.example.userservice.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


// This class handles the exceptions and send appropriate responses to the client

@ControllerAdvice
public class ExceptionHandlers {

    // This method handles UserAlreadyExistsException and sends a response with the exception message and status code 409
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionDto> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.CONFLICT);
    }


    // This method handles UserNotFoundException and sends a response with the exception message and status code 401
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleUserNotFoundException(UserNotFoundException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.UNAUTHORIZED);
    }


    // This method handles PasswordNotMatchingException and sends a response with the exception message and status code 400
    @ExceptionHandler(PasswordNotMatchingException.class)
    public ResponseEntity<ExceptionDto> handlePasswordNotMatchingException(PasswordNotMatchingException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }


    // This method handles InvalidTokenException and sends a response with the exception message and status code 401
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ExceptionDto> handleInvalidTokenException(InvalidTokenException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.UNAUTHORIZED);
    }

}
