package com.example.notificationservice.services;

import com.example.notificationservice.configs.EmailConfig;
import com.example.notificationservice.configs.PropertiesConfig;
import com.example.notificationservice.utils.EmailUtil;
import jakarta.mail.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Properties;


@Service
public class NotificationService {

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private PropertiesConfig propertiesConfig;


    public void sendEmailNotifyUserRegister(String userEmail, String userName) {
        // Get the email session
        Session session = emailConfig.getEmailSession();
        // Get the properties file and get the subject and body of the email
        Properties propertiesFile = propertiesConfig.getPropertiesFile();
        String subject = propertiesFile.getProperty("user.register.mail.subject");
        String body = propertiesFile.getProperty("user.register.mail.body").replaceAll("<username>", userName);
        // Send the email to the user using the emailUtil for registration
        emailUtil.sendEmail(session, userEmail, subject, body);
    }


    public void sendEmailNotifyUserLogIn(String userEmail) {
        // Get the email session
        Session session = emailConfig.getEmailSession();
        // Get the properties file and get the subject and body of the email
        Properties propertiesFile = propertiesConfig.getPropertiesFile();
        String subject = propertiesFile.getProperty("user.login.mail.subject");
        String body = propertiesFile.getProperty("user.login.mail.body");
        // Send the email to the user using the emailUtil for login
        emailUtil.sendEmail(session, userEmail, subject, body);
    }


    public void sendEmailNotifyUserProfileUpdate(String userEmail, String userName) {
        // Get the email session
        Session session = emailConfig.getEmailSession();
        // Get the properties file and get the subject and body of the email
        Properties propertiesFile = propertiesConfig.getPropertiesFile();
        String subject = propertiesFile.getProperty("user.profile.update.mail.subject");
        String body = propertiesFile.getProperty("user.profile.update.mail.body").replaceAll("<username>", userName);
        // Send the email to the user using the emailUtil for profile update
        emailUtil.sendEmail(session, userEmail, subject, body);
    }


    public void sendEmailNotifyUserOrderUpdate(String userEmail, String orderStatus) {
        // Get the email session
        Session session = emailConfig.getEmailSession();
        // Get the properties file and get the subject and body of the email
        Properties propertiesFile = propertiesConfig.getPropertiesFile();
        String subject = propertiesFile.getProperty("user.order.update.mail.subject");
        String body = propertiesFile.getProperty("user.order.update.mail.body").replaceAll("<status>", orderStatus);
        // Send the email to the user using the emailUtil for order update
        emailUtil.sendEmail(session, userEmail, subject, body);
    }

}
