package com.project.hunter.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hunter.domain.dto.auth.LoginDto;
import com.project.hunter.domain.dto.auth.LoginResponseDto;
import com.project.hunter.domain.dto.users.UserDto;
import com.project.hunter.exceptions.NotFoundException;
import com.project.hunter.services.AuthService;
import com.project.hunter.services.UserService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthService authService;
    private final UserService userService;

    @Value("${job-hunter.jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder,
            AuthService authService, UserService userService) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginApi(@Valid @RequestBody LoginDto loginDto)
            throws NotFoundException {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                        loginDto.getPassword());
        Authentication authentication =
                authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // add user to security context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // get user dto
        UserDto userDto = this.userService.handleGetUserByEmail(loginDto.getUsername());
        // create access & refresh token
        String accessToken = this.authService.createAccessToken(userDto);
        String refreshToken = this.authService.createRefreshToken(userDto);
        // update refresh token for user in database
        this.userService.handleUpdateRefreshToken(userDto.getId(), refreshToken);
        // pass refresh_token into cookie
        ResponseCookie responseCookie = ResponseCookie.from("refresh_token", refreshToken)
                .httpOnly(true).secure(true).path("/").maxAge(refreshTokenExpiration).build();
        // create object dto for api response
        LoginResponseDto loginResponseDto = new LoginResponseDto(userDto, accessToken);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(loginResponseDto);
    }

    @PostMapping("logout")
    public ResponseEntity<Void> logoutApi() {
        String userId = AuthService.getCurrentUserLogin().isPresent()
                ? AuthService.getCurrentUserLogin().get()
                : "";
        this.userService.handleUpdateRefreshToken(UUID.fromString(userId), null);
        ResponseCookie responseCookie = ResponseCookie.from("refresh_token", null).httpOnly(true)
                .secure(true).path("/").maxAge(0).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(null);
    }


    @GetMapping("refresh")
    public ResponseEntity<LoginResponseDto> refreshTokenAResponseEntity(
            @CookieValue(name = "refresh_token") String refreshToken) {
        Jwt jwtDecoded = this.authService.decodeToken(refreshToken);
        String userId = jwtDecoded.getSubject();

        UserDto userDto = this.userService
                .handleFindUserByIdAndRefreshToken(UUID.fromString(userId), refreshToken);
        String newAccessToken = this.authService.createAccessToken(userDto);
        String newRefreshToken = this.authService.createRefreshToken(userDto);
        this.userService.handleUpdateRefreshToken(userDto.getId(), newRefreshToken);
        ResponseCookie responseCookie = ResponseCookie.from("refresh_token", newRefreshToken)
                .httpOnly(true).secure(true).path("/").maxAge(refreshTokenExpiration).build();
        LoginResponseDto loginResponseDto = new LoginResponseDto(userDto, newAccessToken);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(loginResponseDto);
    }

    @GetMapping("me")
    public ResponseEntity<UserDto> getMeApi() throws NotFoundException {
        if (AuthService.getCurrentUserLogin().isPresent()) {
            String id = AuthService.getCurrentUserLogin().get();
            UserDto userDto = this.userService.handleGetUserById(UUID.fromString(id));
            return ResponseEntity.ok().body(userDto);
        }
        return null;
    }
}
