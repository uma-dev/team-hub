package com.umadev.springboot.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    // Create a mapping for "/hello"

    @GetMapping("/hello")
    public String sayHello(Model theModel) {

        theModel.addAttribute("theDate", new java.util.Date());
        // Due to Thymeleaf, spring boot will auto configure and search for
        // src/main/resources/templates/helloworld.html
        return "helloworld";
    }
}
