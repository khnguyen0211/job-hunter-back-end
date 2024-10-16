package com.project.hunter.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hunter.domain.dto.UpdateUserDto;
import com.project.hunter.domain.dto.UserDto;
import com.project.hunter.domain.entities.UserEntity;
import com.project.hunter.services.UserService;

@RestController()
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserDto> createUserAPI(@RequestBody UserEntity userEntity) {
        UserDto userDto = this.userService.handleSaveUser(userEntity);
        return ResponseEntity.status(201).body(userDto);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsersApi() {
        List<UserDto> userDtoList = this.userService.handleGetAllUser();
        return ResponseEntity.status(200).body(userDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetailsApi(@PathVariable("id") UUID id) {
        UserDto userDto = this.userService.handleGetUserById(id);
        return ResponseEntity.status(200).body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserApi(@PathVariable("id") UUID id, @RequestBody UpdateUserDto updateUserDto) {
        UserDto userDto = this.userService.handleUpdateUserById(id, updateUserDto);
        return ResponseEntity.status(200).body(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUserApi(@PathVariable("id") UUID id) {
        UserDto userDto = this.userService.handleDeleteUserById(id);
        return ResponseEntity.status(200).body(userDto);
    }
}
