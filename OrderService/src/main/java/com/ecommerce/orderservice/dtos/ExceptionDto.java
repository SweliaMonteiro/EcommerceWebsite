package com.ecommerce.orderservice.dtos;

import lombok.Getter;
import lombok.Setter;


// This dto is used to send exception messages to the client

@Getter
@Setter
public class ExceptionDto {

    private String message;

}
