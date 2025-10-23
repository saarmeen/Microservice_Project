package com.example.apigateway.config;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthHeaderFactory {

    @Value("${productservice.auth.username}")
    String _ProductUsername;

    @Value("${productservice.auth.password}")
    String _ProductPassword;

    @Value("${orderservice.auth.username}")
    String _OrderUsername;

    @Value("${orderservice.auth.password}")
    String _OrderPassword;

    @Value("${apigateway.shared.secret}")
    String _SharedSecret;

    public String buildAuthHeader(String serviceName) {

        String username = "";
        String password = "";

        if (serviceName.equals("orderservice")) {   
            username = _OrderUsername;
            password = _OrderPassword;
        } else if (serviceName.equals("productservice")) {  
            username = _ProductUsername;
            password = _ProductPassword;
        }

        String auth = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
    }

    public String getSharedSecret() {
        return _SharedSecret;
    }
}
