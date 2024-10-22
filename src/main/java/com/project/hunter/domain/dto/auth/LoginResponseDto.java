package com.project.hunter.domain.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.hunter.domain.dto.users.UserDto;


public class LoginResponseDto {
    private UserDto user;
    @JsonProperty("access_token")
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
