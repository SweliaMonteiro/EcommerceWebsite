package com.ecommerce.userservice.controllers;

import com.ecommerce.userservice.dtos.*;
import com.ecommerce.userservice.exceptions.*;
import com.ecommerce.userservice.models.*;
import com.ecommerce.userservice.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Used to send messages to Kafka where Key is the Topic and Value is the user details which will be used by Notification service
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    // This ObjectMapper is used to convert the Order Dto object to a JSON string as Kafka only accepts strings as messages and not objects
    @Autowired
    private ObjectMapper objectMapper;


    // This method registers a user and returns the user details after saving them in the database
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException, JsonProcessingException {
        User user = userService.register(signUpRequestDto.getName(), signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
        // Send the UserDto object as a JSON string to the Kafka topic "NotifyUserRegister"
        kafkaTemplate.send("NotifyUserRegister", objectMapper.writeValueAsString(UserDto.from(user)));
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }


    // This method is used to log in a user and return a token for the user to access the application resources
    @PostMapping("/login")
    public ResponseEntity<TokenDto> logIn(@RequestBody LogInRequestDto logInRequestDto) throws UserNotFoundException, PasswordNotMatchingException, JsonProcessingException {
        Token token = userService.logIn(logInRequestDto.getEmail(), logInRequestDto.getPassword());
        // Send the LogInRequestDto object as a JSON string to the Kafka topic "NotifyUserLogIn"
        kafkaTemplate.send("NotifyUserLogIn", objectMapper.writeValueAsString(logInRequestDto));
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
    public ResponseEntity<UserDto> updateProfile(@PathVariable long id, @RequestBody UpdateProfileRequestDto updateProfileRequestDto) throws UserNotFoundException, JsonProcessingException {
        User user = userService.updateProfile(id, updateProfileRequestDto.getName(), updateProfileRequestDto.getEmail(), updateProfileRequestDto.getPassword());
        // Send the UserDto object as a JSON string to the Kafka topic "NotifyUserProfileUpdate"
        kafkaTemplate.send("NotifyUserProfileUpdate", objectMapper.writeValueAsString(UserDto.from(user)));
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

}
