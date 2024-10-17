package com.project.hunter.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginDto {
    @NotBlank(message="Your username can not empty.")
    @Email(message="Your username is not a valid email.")
    private String username;

    @NotBlank(message = "Your password can not empty.")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
