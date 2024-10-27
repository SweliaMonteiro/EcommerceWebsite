package com.ecommerce.notificationservice.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


@Configuration
public class PropertiesConfig {

    @Autowired
    private Properties properties;


    // This method reads the application.properties file and returns the Properties object
    @Bean
    public Properties getPropertiesFile() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
            properties.load(inputStream);
            return properties;
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
