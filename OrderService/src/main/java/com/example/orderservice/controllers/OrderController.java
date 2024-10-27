package com.example.orderservice.controllers;

import com.example.orderservice.dtos.*;
import com.example.orderservice.exceptions.*;
import com.example.orderservice.models.Order;
import com.example.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    // This method is used to check out the cart items, sets order and payment status to "Pending" and returns the order details to process further for payment
    @PostMapping("/checkout")
    public ResponseEntity<OrderResponseDto> checkout(@RequestBody CheckoutRequestDto checkoutRequestDto) throws UserNotFoundException, CartItemNotFoundException {
        Order order = orderService.checkout(checkoutRequestDto.getUserId(), checkoutRequestDto.getCartItemIds(),
                                            checkoutRequestDto.getDeliveryAddress(), checkoutRequestDto.getPaymentMode());
        return new ResponseEntity<>(OrderResponseDto.from(order), HttpStatus.OK);
    }


    // This method is used to get the order history of a user
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<OrderResponseDto>> getOrderHistory(@PathVariable Long userId) throws UserNotFoundException {
        List<Order> orders = orderService.getOrderHistory(userId);
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
        for (Order order : orders) {
            orderResponseDtos.add(OrderResponseDto.from(order));
        }
        return new ResponseEntity<>(orderResponseDtos, HttpStatus.OK);
    }


    // This method is used to track the order status for a given order id
    @GetMapping("/track/{orderId}")
    public ResponseEntity<OrderResponseDto> trackOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        Order order = orderService.trackOrder(orderId);
        return new ResponseEntity<>(OrderResponseDto.from(order), HttpStatus.OK);
    }


    // This method is used to update the order status or the payment status for a given order id
    @PutMapping("/update")
    public ResponseEntity<OrderResponseDto> updateOrder(@RequestBody UpdateOrderRequestDto updateOrderRequestDto) throws OrderNotFoundException, InvalidOrderStatusException, InvalidPaymentStatusException {
        Order order = orderService.updateOrder(updateOrderRequestDto.getOrderId(), updateOrderRequestDto.getOrderStatus(), updateOrderRequestDto.getPaymentStatus());
        return new ResponseEntity<>(OrderResponseDto.from(order), HttpStatus.OK);
    }

}
