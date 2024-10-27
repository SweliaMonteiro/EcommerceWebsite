package com.example.paymentservice.controllers;

import com.example.paymentservice.dtos.OrderResponseDto;
import com.example.paymentservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping()
    public String initiatePayment(@RequestBody OrderResponseDto orderResponseDto) {
        try {
            return paymentService.initiatePayment(orderResponseDto.getOrderId(), orderResponseDto.getTotalAmount(), orderResponseDto.getPaymentMode());
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
