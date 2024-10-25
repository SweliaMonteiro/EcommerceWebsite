package com.example.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    // This method creates a SecurityFilterChain bean that configures the security of the application
    // All requests coming to the application will be passed through this SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
            try {
                // Permit all the requests of the endpoints that start with /users/
                requests.requestMatchers("antMatchers", "/user/*").permitAll();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        // Build the http security configuration
        return http.build();
    }

}
