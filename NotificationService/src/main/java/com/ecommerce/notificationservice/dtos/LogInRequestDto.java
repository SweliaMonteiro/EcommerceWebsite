package com.ecommerce.notificationservice.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LogInRequestDto {

    private String email;

    private String password;

}
