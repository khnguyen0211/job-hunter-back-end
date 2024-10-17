package com.project.hunter.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HelloController {

    @GetMapping()
    public ResponseEntity<Object> getLicenseApi() {
        String license = "Copyright © 2024 KhNguyen0211. All rights reserved.";
        return ResponseEntity.ok().body(null);
    }
}
