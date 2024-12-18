package com.ecommerce.userservice.services;

import com.ecommerce.userservice.exceptions.InvalidTokenException;
import com.ecommerce.userservice.exceptions.PasswordNotMatchingException;
import com.ecommerce.userservice.exceptions.UserAlreadyExistsException;
import com.ecommerce.userservice.exceptions.UserNotFoundException;
import com.ecommerce.userservice.models.Token;
import com.ecommerce.userservice.models.User;
import com.ecommerce.userservice.repositories.TokenRepository;
import com.ecommerce.userservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private TokenRepository tokenRepository;


    // This method is used to register a new user
    public User register(String name, String email, String password) throws UserAlreadyExistsException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("User with Email Id " + email + " already exists");
        }
        // Create a new user object and save it to the database
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        // Hash the password before saving it to the database using BCryptPasswordEncoder
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }


    //  This method is used to log in a user
    public Token logIn(String email, String password) throws UserNotFoundException, PasswordNotMatchingException {
        // Find the user with the given email
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with Email Id " + email + " is not registered");
        }
        User user = optionalUser.get();

        // Check if the password is correct
        if (bCryptPasswordEncoder.matches(password, user.getHashedPassword())) {
            // Check if the user already has a token
            Optional<Token> optionalToken = tokenRepository.findByUserAndIsDeleted(user, false);
            if (optionalToken.isPresent()) {
                return optionalToken.get();
            }

            // Create a new token for the user if the user does not have a token and save it to the DB
            Token token = new Token();
            // Generate a random alphanumeric string of length 128
            token.setValue(RandomStringUtils.randomAlphanumeric(128));
            // Set the expiry date of the token to 30 days from the current date
            Date expiryOn = new Date();
            expiryOn.setDate(expiryOn.getDate() + 30);
            token.setExpiryOn(expiryOn);
            token.setUser(user);
            return tokenRepository.save(token);
        }

        throw new PasswordNotMatchingException("Password is Incorrect");
    }


    // This method is used to log out a user
    public void logOut(String userToken) throws InvalidTokenException {
        // Find the token with the given value and is not deleted
        Optional<Token> optionalToken = tokenRepository.findByValueAndIsDeleted(userToken, false);
        if (optionalToken.isEmpty()) {
            throw new InvalidTokenException("Token is Invalid");
        }
        // If the token is found, set the token as deleted in the DB
        Token token = optionalToken.get();
        token.setDeleted(true);
        tokenRepository.save(token);
    }


    // This method is used to validate a token
    public User validateToken(String userToken) throws InvalidTokenException {
        // Find the token with the given value, is not deleted and has not expired
        Optional<Token> optionalToken = tokenRepository.findByValueAndIsDeletedAndExpiryAtGreaterThan(userToken, false, new Date());
        if (optionalToken.isEmpty()) {
            throw new InvalidTokenException("Token is Invalid");
        }
        // If the token is found, return the user associated with the token
        return optionalToken.get().getUser();
    }

    // This method is used to register a new user
    public User updateProfile(long id, String name, String email, String password) throws UserNotFoundException {
        // Find the user with the given id, if not found throw an exception
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty() || optionalUser.get().isDeleted()) {
            throw new UserNotFoundException("User with Id " + id + " does not exists");
        }
        User user = optionalUser.get();

        // Check if the user details are same as the existing user details, if not update the user details and update the user in the database
        if (name!=null && !user.getName().equals(name.trim())) {
            user.setName(name);
        }
        if (email!=null && !user.getEmail().equals(email.trim())) {
            user.setEmail(email);
        }
        if (password!=null && !bCryptPasswordEncoder.matches(password.trim(), user.getHashedPassword())) {
            user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        }
        return userRepository.save(user);
    }

}
