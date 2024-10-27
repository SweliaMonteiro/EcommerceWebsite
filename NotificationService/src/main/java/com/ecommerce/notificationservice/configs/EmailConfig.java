package com.ecommerce.notificationservice.configs;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Properties;


@Configuration
public class EmailConfig {

    @Autowired
    private Properties emailProperties;

    @Autowired
    private PropertiesConfig propertiesConfig;


    // This method creates a Session object to authenticate the email client with the email server
    @Bean
    public Session getEmailSession() {
        Authenticator auth = getEmailAuthenticator();

        // Set the properties of the email client
        emailProperties.put("mail.smtp.host", "smtp.gmail.com");  // SMTP Host
        emailProperties.put("mail.smtp.port", "587");  // TLS Port
        emailProperties.put("mail.smtp.auth", "true");  // Enable authentication
        emailProperties.put("mail.smtp.starttls.enable", "true");  // Enable STARTTLS

        // Create a Session object
        return Session.getInstance(emailProperties, auth);
    }


    private Authenticator getEmailAuthenticator() {
        Properties propertiesFile = propertiesConfig.getPropertiesFile();

        // Create Authenticator object to pass in Session.getInstance argument
        return new Authenticator() {
            // Override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(propertiesFile.getProperty("from.email.id"), propertiesFile.getProperty("from.email.password"));
            }
        };
    }

}
