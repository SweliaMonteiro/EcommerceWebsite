package com.ecommerce.userservice.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LogInRequestDto {

    private String email;

    private String password;

}
