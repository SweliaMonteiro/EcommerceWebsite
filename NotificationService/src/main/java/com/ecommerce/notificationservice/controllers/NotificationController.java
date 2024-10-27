package com.ecommerce.notificationservice.controllers;

import com.ecommerce.notificationservice.dtos.LogInRequestDto;
import com.ecommerce.notificationservice.dtos.OrderResponseDto;
import com.ecommerce.notificationservice.dtos.UserDto;
import com.ecommerce.notificationservice.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


    // This method sends an email notification to the user when they register
    public void sendEmailNotifyUserRegister(UserDto userDto) {
        notificationService.sendEmailNotifyUserRegister(userDto.getEmail(), userDto.getName());
    }


    // This method sends an email notification to the user when they log in
    public void sendEmailNotifyUserLogIn(LogInRequestDto logInRequestDto) {
        notificationService.sendEmailNotifyUserLogIn(logInRequestDto.getEmail());
    }


    // This method sends an email notification to the user when they update their profile
    public void sendEmailNotifyUserProfileUpdate(UserDto userDto) {
        notificationService.sendEmailNotifyUserProfileUpdate(userDto.getEmail(), userDto.getName());
    }


    // This method sends an email notification to the user when their order is updated
    public void sendEmailNotifyUserOrderUpdate(OrderResponseDto orderResponseDto) {
        notificationService.sendEmailNotifyUserOrderUpdate(orderResponseDto.getUserEmail(), orderResponseDto.getOrderStatus(), orderResponseDto.getPaymentStatus());
    }

}
