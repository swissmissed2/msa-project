package com.spring.cloud.eureka.client.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo/test")
    public String test() {
        return "test completed";
    }
}
