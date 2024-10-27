package com.example.paymentservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class CardPayment extends BaseModel {

    @ManyToOne
    private Order order;

    private String cardNumber;

    private String cardHolderName;

    private String cardExpiryDate;

}

