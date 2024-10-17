package com.project.hunter.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hunter.domain.dto.LoginDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<LoginDto> loginApi(@Valid @RequestBody LoginDto loginDto) {

        return ResponseEntity.ok().body(loginDto);
    }

}
