package com.viralsville.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BaseController {

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "This is Viralsville!";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
