package com.ecommerce.cartservice.controllers;

import com.ecommerce.cartservice.dto.AddToCartRequestDto;
import com.ecommerce.cartservice.dto.CartResponseDto;
import com.ecommerce.cartservice.dto.ViewCartRequestDto;
import com.ecommerce.cartservice.exceptions.ProductNotFoundException;
import com.ecommerce.cartservice.exceptions.UserNotFoundException;
import com.ecommerce.cartservice.models.Cart;
import com.ecommerce.cartservice.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    // This method is used to add a product to the cart for a user
    @PostMapping("/add")
    public ResponseEntity<CartResponseDto> addToCart(@RequestBody AddToCartRequestDto addToCartRequestDto) throws UserNotFoundException, ProductNotFoundException {
        Cart cart = cartService.addToCart(addToCartRequestDto.getUserId(), addToCartRequestDto.getProductId());
        return new ResponseEntity<>(CartResponseDto.from(cart), HttpStatus.OK);
    }


    // This method is used to view the cart items for a user
    @GetMapping("/review")
    public ResponseEntity<List<CartResponseDto>> viewCart(@RequestParam ViewCartRequestDto viewCartRequestDto) throws UserNotFoundException {
        List<Cart> carts = cartService.viewCart(viewCartRequestDto.getUserId());
        List<CartResponseDto> cartResponseDtos = new ArrayList<>();
        for (Cart cart : carts) {
            cartResponseDtos.add(CartResponseDto.from(cart));
        }
        return new ResponseEntity<>(cartResponseDtos, HttpStatus.OK);
    }

}
