package com.example.notificationservice.configs;

import com.example.notificationservice.controllers.NotificationController;
import com.example.notificationservice.dtos.LogInRequestDto;
import com.example.notificationservice.dtos.OrderResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import com.example.notificationservice.dtos.UserDto;


@Configuration
public class KafkaConsumerConfig {

    // This ObjectMapper is used to convert the JSON string received from Kafka to a Dto object
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NotificationController notificationController;


    // This method listens to the Kafka topic "NotifyUserRegister" and consumes the messages sent to it
    @KafkaListener(topics = "NotifyUserRegister")
    public void consumeNotifyUserRegister(String message) throws Exception {
        UserDto userDto = objectMapper.readValue(message, UserDto.class);
        notificationController.sendEmailNotifyUserRegister(userDto);
    }


    // This method listens to the Kafka topic "NotifyUserLogIn" and consumes the messages sent to it
    @KafkaListener(topics = "NotifyUserLogIn")
    public void consumeNotifyUserLogIn(String message) throws Exception {
        LogInRequestDto logInRequestDto = objectMapper.readValue(message, LogInRequestDto.class);
        notificationController.sendEmailNotifyUserLogIn(logInRequestDto);
    }


    // This method listens to the Kafka topic "NotifyUserProfileUpdate" and consumes the messages sent to it
    @KafkaListener(topics = "NotifyUserProfileUpdate")
    public void consumeNotifyUserProfileUpdate(String message) throws Exception {
        UserDto userDto = objectMapper.readValue(message, UserDto.class);
        notificationController.sendEmailNotifyUserProfileUpdate(userDto);
    }


    // This method listens to the Kafka topic "NotifyUserOrderUpdate" and consumes the messages sent to it
    @KafkaListener(topics = "NotifyUserOrderUpdate")
    public void consumeNotifyUserOrderUpdate(String message) throws Exception {
        OrderResponseDto orderResponseDto = objectMapper.readValue(message, OrderResponseDto.class);
        notificationController.sendEmailNotifyUserOrderUpdate(orderResponseDto);
    }

}
