package com.ecommerce.orderservice.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
public class CheckoutRequestDto {

    private Long userId;

    private List<Long> cartItemIds;

    private String deliveryAddress;

    private String paymentMode;

}
