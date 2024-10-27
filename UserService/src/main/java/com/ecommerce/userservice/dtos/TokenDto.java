package com.ecommerce.userservice.dtos;

import com.ecommerce.userservice.models.Token;
import com.ecommerce.userservice.models.User;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
public class TokenDto {

    private String value;

    private Date expiryOn;

    private User user;

    private boolean isDeleted;


    // This method is used to convert a Token object to a TokenDto object
    public static TokenDto from(Token token) {
        TokenDto tokenDto = new TokenDto();
        tokenDto.setValue(token.getValue());
        tokenDto.setExpiryOn(token.getExpiryOn());
        tokenDto.setUser(token.getUser());
        tokenDto.setDeleted(token.isDeleted());
        return tokenDto;
    }

}
