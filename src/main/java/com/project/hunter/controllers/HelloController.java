package com.project.hunter.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController()
public class HelloController {

    @GetMapping()
    public RedirectView getLicenseApi() {
        return new RedirectView("/swagger-ui/index.html");
    }
}
