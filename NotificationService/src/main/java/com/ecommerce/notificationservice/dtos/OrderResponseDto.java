package com.ecommerce.notificationservice.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderResponseDto {

    private String userEmail;

    private Double totalAmount;

    private String deliveryAddress;

    private String orderStatus;

    private String paymentMode;

    private String paymentStatus;

}
