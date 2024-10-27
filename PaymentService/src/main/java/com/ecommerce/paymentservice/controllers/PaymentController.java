package com.ecommerce.paymentservice.controllers;

import com.ecommerce.paymentservice.dtos.*;
import com.ecommerce.paymentservice.exceptions.*;
import com.ecommerce.paymentservice.services.PaymentService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    // This method is used to initiate the payment for a given order id
    @PostMapping("/initiatePayment")
    public ResponseEntity<InitiatePaymentResponseDto> initiatePayment(@RequestBody InitiatePaymentRequestDto initiatePaymentRequestDto) throws OrderNotFoundException, RazorpayException, InvalidPaymentModeException {
        return new ResponseEntity<>(paymentService.initiatePayment(initiatePaymentRequestDto.getOrderId(), initiatePaymentRequestDto.getCardNumber(),
                                                                   initiatePaymentRequestDto.getCardHolderName(), initiatePaymentRequestDto.getCardExpiryDate()), HttpStatus.OK);
    }


    // This method is used to track the payment status for a given payment id
    @GetMapping("/track")
    public ResponseEntity<TrackPaymentResponseDto> trackPayment(@RequestBody TrackPaymentRequestDto trackPaymentRequestDto) throws PaymentNotFoundException, InvalidPaymentModeException {
        return new ResponseEntity<>(paymentService.trackPayment(trackPaymentRequestDto.getPaymentId(), trackPaymentRequestDto.getPaymentMode()), HttpStatus.OK);
    }

}
