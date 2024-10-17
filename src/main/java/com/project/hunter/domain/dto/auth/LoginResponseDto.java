package com.project.hunter.domain.dto.auth;

import com.project.hunter.domain.dto.users.UserDto;


public class LoginResponseDto {
    private UserDto user;
    private String accessToken;

    public LoginResponseDto(UserDto user, String accessToken) {
        this.accessToken = accessToken;
        this.user = user;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
