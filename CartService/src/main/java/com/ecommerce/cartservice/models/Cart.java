package com.ecommerce.cartservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Cart extends BaseModel {

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    private Integer quantity;

}
