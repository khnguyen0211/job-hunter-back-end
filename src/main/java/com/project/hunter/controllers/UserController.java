package com.project.hunter.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.hunter.domain.dto.UserDto;
import com.project.hunter.domain.entities.UserEntity;
import com.project.hunter.services.UserService;

@RestController()
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("users")
    public UserDto createUserAPI(@RequestBody UserEntity userEntity) {
        return this.userService.handleSaveUser(userEntity);
    }

    @GetMapping("users")
    public List<UserDto> getAllUsersApi() {
        return this.userService.handleGetAllUser();
    }

    @GetMapping("users/{id}")
    public UserDto getUserDetailsApi(@PathVariable("id") UUID id) {
        return this.userService.handleGetUserById(id);
    }

}
