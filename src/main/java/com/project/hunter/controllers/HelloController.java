package com.project.hunter.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
public class HelloController {
    @GetMapping("/hello")
    public String getMethodName() {
        return "Hello world";
    }
    
}
