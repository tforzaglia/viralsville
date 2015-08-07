package com.viralsville.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class BaseController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
