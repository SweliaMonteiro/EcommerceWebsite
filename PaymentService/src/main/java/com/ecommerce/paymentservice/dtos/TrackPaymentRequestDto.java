package com.ecommerce.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TrackPaymentRequestDto {

    private long paymentId;

    private String paymentMode;

}
