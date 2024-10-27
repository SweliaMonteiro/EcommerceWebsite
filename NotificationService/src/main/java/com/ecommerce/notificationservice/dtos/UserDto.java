package com.ecommerce.notificationservice.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDto {

    private String name;

    private String email;

    private boolean isEmailVerified;

}
