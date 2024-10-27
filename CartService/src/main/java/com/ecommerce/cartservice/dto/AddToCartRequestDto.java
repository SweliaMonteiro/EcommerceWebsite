package com.ecommerce.cartservice.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddToCartRequestDto {

    private long userId;

    private long productId;

}
