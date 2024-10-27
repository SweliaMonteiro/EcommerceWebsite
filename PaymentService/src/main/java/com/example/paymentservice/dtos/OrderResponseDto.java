package com.example.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderResponseDto {

    private Long orderId;

    private String userEmail;

    private Double totalAmount;

    private String deliveryAddress;

    private String orderStatus;

    private String paymentMode;

    private String paymentStatus;

}
