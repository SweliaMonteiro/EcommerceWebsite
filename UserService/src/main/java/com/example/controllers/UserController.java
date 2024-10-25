package com.example.controllers;

import com.example.dtos.*;
import com.example.exceptions.InvalidTokenException;
import com.example.exceptions.PasswordNotMatchingException;
import com.example.exceptions.UserAlreadyExistsException;
import com.example.exceptions.UserNotFoundException;
import com.example.models.Token;
import com.example.models.User;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    // This method registers a user and returns the user details after saving them in the database
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException {
        User user = userService.register(signUpRequestDto.getName(), signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }


    // This method is used to log in a user and return a token for the user to access the application resources
    @PostMapping("/login")
    public ResponseEntity<TokenDto> logIn(@RequestBody LogInRequestDto logInRequestDto) throws UserNotFoundException, PasswordNotMatchingException {
        Token token = userService.logIn(logInRequestDto.getEmail(), logInRequestDto.getPassword());
        return new ResponseEntity<>(TokenDto.from(token), HttpStatus.OK);
    }


    // This method is used to log out a user by deleting the token from the database
    @PostMapping("/logout")
    public ResponseEntity<Void> logOut(@RequestBody LogOutRequestDto logOutRequestDto) throws InvalidTokenException {
        userService.logOut(logOutRequestDto.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // This method validates the token and return the user details
    @GetMapping("/validate/{token}")
    public ResponseEntity<UserDto> validateToken(@PathVariable("token") String token) {
        try {
            User user = userService.validateToken(token);
            return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
        }
        catch (InvalidTokenException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // This method updates the user profile and returns the updated user details
    @PutMapping("/updateProfile/{id}")
    public ResponseEntity<UserDto> updateProfile(@PathVariable long id, @RequestBody UpdateProfileRequestDto updateProfileRequestDto) throws UserNotFoundException {
        User user = userService.updateProfile(id, updateProfileRequestDto.getName(), updateProfileRequestDto.getEmail(), updateProfileRequestDto.getPassword());
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

}
