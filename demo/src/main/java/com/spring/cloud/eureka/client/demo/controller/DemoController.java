package com.spring.cloud.eureka.client.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class DemoController {

    @Value("${message}")
    private String message;

    @GetMapping("/demo/test")
    public String test() {
        return message;
    }
}
