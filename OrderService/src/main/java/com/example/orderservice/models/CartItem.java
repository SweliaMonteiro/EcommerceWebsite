package com.example.orderservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class CartItem extends BaseModel {

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    private Integer quantity;

}
