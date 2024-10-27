package com.example.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InitiatePaymentRequestDto {

    private long orderId;

    private String cardNumber;

    private String cardHolderName;

    private String cardExpiryDate;

}
