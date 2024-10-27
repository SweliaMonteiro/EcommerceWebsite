package com.example.orderservice.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateOrderRequestDto {

    private Long orderId;

    private String orderStatus;

    private String paymentStatus;

}
