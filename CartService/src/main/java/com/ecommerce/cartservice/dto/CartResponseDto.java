package com.ecommerce.cartservice.dto;

import com.ecommerce.cartservice.models.Cart;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CartResponseDto {

    private String userEmail;

    private String productName;

    private double price;

    private String imageUrl;

    private int quantity;

    public static CartResponseDto from(Cart cart) {
        CartResponseDto cartResponseDto = new CartResponseDto();
        cartResponseDto.setUserEmail(cart.getUser().getName());
        cartResponseDto.setProductName(cart.getProduct().getName());
        cartResponseDto.setPrice(cart.getProduct().getPrice());
        cartResponseDto.setImageUrl(cart.getProduct().getImageUrl());
        cartResponseDto.setQuantity(cart.getQuantity());
        return cartResponseDto;
    }

}
