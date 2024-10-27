package com.example.orderservice.dtos;

import com.example.orderservice.models.CartItem;
import com.example.orderservice.models.Order;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
public class OrderResponseDto {

    private String userEmail;

    private List<CartItemDto> cartItemDtos;

    private Double totalAmount;

    private String deliveryAddress;

    private String orderStatus;

    private String paymentMode;

    private String paymentStatus;


    public static OrderResponseDto from(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setUserEmail(order.getUser().getEmail());
        orderResponseDto.setTotalAmount(order.getTotalAmount());
        orderResponseDto.setDeliveryAddress(order.getDeliveryAddress());
        orderResponseDto.setOrderStatus(order.getOrderStatus().name());
        orderResponseDto.setPaymentMode(order.getPaymentMode().name());
        orderResponseDto.setPaymentStatus(order.getPaymentStatus().name());
        for (CartItem cartItem : order.getCartItems()) {
            orderResponseDto.getCartItemDtos().add(CartItemDto.from(cartItem));
        }
        return orderResponseDto;
    }

}
