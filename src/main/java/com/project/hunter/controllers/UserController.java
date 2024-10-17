package com.project.hunter.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.project.hunter.exceptions.IdInvalidException;
import com.project.hunter.services.UserService;
import com.project.hunter.utils.UUIDChecker;

@RestController()
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping()
    public ResponseEntity<UserDto> createUserAPI(@RequestBody UserEntity userEntity) {
        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
        UserDto userDto = this.userService.handleSaveUser(userEntity);
        return ResponseEntity.status(201).body(userDto);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsersApi() {
        List<UserDto> userDtoList = this.userService.handleGetAllUser();
        return ResponseEntity.status(200).body(userDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetailsApi(@PathVariable("id") String id)
            throws IdInvalidException {
        if (!UUIDChecker.isValidUUID(id)) {
            throw new IdInvalidException("UUID is invalid");
        }
        UUID uuid = UUID.fromString(id);
        UserDto userDto = this.userService.handleGetUserById(uuid);
        return ResponseEntity.ok().body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserApi(@PathVariable("id") String id, @RequestBody UpdateUserDto updateUserDto)
            throws IdInvalidException {
        if (!UUIDChecker.isValidUUID(id)) {
            throw new IdInvalidException("UUID is invalid");
        }
        UUID uuid = UUID.fromString(id);
        UserDto userDto = this.userService.handleUpdateUserById(uuid, updateUserDto);
        return ResponseEntity.status(200).body(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUserApi(@PathVariable("id") String id) throws IdInvalidException {
        if (!UUIDChecker.isValidUUID(id)) {
            throw new IdInvalidException("UUID is invalid");
        }
        UUID uuid = UUID.fromString(id);
        UserDto userDto = this.userService.handleDeleteUserById(uuid);
        return ResponseEntity.ok().body(userDto);
    }
}
