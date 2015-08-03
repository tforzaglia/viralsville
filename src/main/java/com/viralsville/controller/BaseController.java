package com.viralsville.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BaseController {

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String goToIndexPage( ModelMap model ) {
        return "index";
    }
}