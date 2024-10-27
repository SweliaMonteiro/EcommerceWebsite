package com.ecommerce.orderservice.dtos;

import com.ecommerce.orderservice.models.CartItem;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CartItemDto {

    private String userEmail;

    private String productName;

    private double price;

    private String imageUrl;

    private int quantity;

    public static CartItemDto from(CartItem cartItem) {
        CartItemDto CartItemDto = new CartItemDto();
        CartItemDto.setUserEmail(cartItem.getUser().getName());
        CartItemDto.setProductName(cartItem.getProduct().getName());
        CartItemDto.setPrice(cartItem.getProduct().getPrice());
        CartItemDto.setImageUrl(cartItem.getProduct().getImageUrl());
        CartItemDto.setQuantity(cartItem.getQuantity());
        return CartItemDto;
    }

}
