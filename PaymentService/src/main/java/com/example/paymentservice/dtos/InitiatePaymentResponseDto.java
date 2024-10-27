package com.example.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InitiatePaymentResponseDto {

    private String paymentMode;

    private String paymentStatus;

    private String onlinePaymentLink;

}
