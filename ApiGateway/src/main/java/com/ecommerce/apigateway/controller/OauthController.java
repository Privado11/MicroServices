package com.ecommerce.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthController {

    @GetMapping("/user")
    public String getUser() {
        return "Welcome User";
    }

}
