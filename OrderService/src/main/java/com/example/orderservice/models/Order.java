package com.example.orderservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseModel {

    @ManyToOne
    private User user;

    @OneToMany
    private List<CartItem> cartItems;

    private Double totalAmount;

    private String deliveryAddress;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

    private String paymentMode;

    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;

}
