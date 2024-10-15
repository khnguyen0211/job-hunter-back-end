package com.project.hunter.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hunter.domain.entities.UserEntity;
import com.project.hunter.services.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("users/create")
    public UserEntity createUserAPI() {
        UserEntity userEntity = new UserEntity();
        userEntity.setFullName("Admin");
        userEntity.setEmail("admin@gmail.com");
        userEntity.setPassword("admin");
        userEntity.setAddress("HCM City VN");
        return this.userService.handleSaveUser(userEntity);
    }

}
