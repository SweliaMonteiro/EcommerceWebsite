package com.ecommerce.userservice.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateProfileRequestDto {

    private String name;

    private String email;

    private String password;

}
