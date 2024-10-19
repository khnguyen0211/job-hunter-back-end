package com.project.hunter.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hunter.domain.dto.auth.LoginDto;
import com.project.hunter.domain.dto.auth.LoginResponseDto;
import com.project.hunter.domain.dto.users.UserDto;
import com.project.hunter.services.AuthService;
import com.project.hunter.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthService authService;
    private final UserService userService;
    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder,
            AuthService authService, UserService userService) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginApi(@Valid @RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                        loginDto.getPassword());
        Authentication authentication =
                authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        String accessToken = this.authService.createToken(authentication);
        UserDto userDto = this.userService.handleGetUserByEmail(loginDto.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LoginResponseDto loginResponseDto = new LoginResponseDto(userDto, accessToken);
        return ResponseEntity.ok().body(loginResponseDto);
    }

}
