package com.example.paymentservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class OnlineTransactionPayment extends BaseModel {

    @OneToOne
    private Order order;

    private String paymentLink;

    private String onlineTransactionPaymentId;

}
