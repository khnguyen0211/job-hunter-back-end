package com.project.hunter.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.hunter.domain.dto.users.UpdateUserDto;
import com.project.hunter.domain.dto.users.UserDto;
import com.project.hunter.domain.entities.UserEntity;
import com.project.hunter.exceptions.NotFoundException;
import com.project.hunter.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto handleSaveUser(UserEntity userEntity) {
        UserDto userDto = this.handleGetUserByEmail(userEntity.getEmail());
        if (userDto != null) {
            return null;
        }
        UserEntity insertedUserEntity = this.userRepository.save(userEntity);
        return new UserDto(insertedUserEntity);
    }

    public List<UserDto> handleGetAllUser() {
        List<UserEntity> userEntities = this.userRepository.findAll();
        return userEntities.stream().map(userEntity -> new UserDto(userEntity))
                .collect(Collectors.toList());
    }

    public UserDto handleGetUserById(UUID id) {
        Optional<UserEntity> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return new UserDto(optionalUser.get());
        }
        return null;
    }

    public UserDto handleUpdateUserById(UUID id, UpdateUserDto updateUserDto)
            throws NotFoundException {
        Optional<UserEntity> optionalUser = this.userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new NotFoundException("Not found UserId " + id.toString());
        }
        UserEntity userEntity = optionalUser.get();
        userEntity.setFullName(updateUserDto.getFullName());
        userEntity.setPhoneNumber(updateUserDto.getPhoneNumber());
        userEntity.setAddress(updateUserDto.getAddress());
        userEntity.setUpdatedAt(Instant.now());
        this.userRepository.save(userEntity);
        return new UserDto(userEntity);
    }

    public UserDto handleDeleteUserById(UUID id) {
        Optional<UserEntity> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            userEntity.setDeletedAt(Instant.now());
            this.userRepository.save(userEntity);
            return new UserDto(userEntity);
        }
        return null;
    }

    public UserDto handleGetUserByEmail(String email) {
        List<UserEntity> userEntities = this.userRepository.findByEmail(email);
        if (userEntities != null && !userEntities.isEmpty()) {
            UserEntity userEntity = userEntities.get(0);
            return new UserDto(userEntity);
        }
        return null;
    }

    public UserEntity handleGetUserLoginByEmail(String email) {
        List<UserEntity> userEntities = this.userRepository.findByEmail(email);
        if (userEntities != null && !userEntities.isEmpty()) {
            UserEntity userEntity = userEntities.get(0);
            return userEntity;
        }
        return null;
    }

    public void handleUpdateRefreshToken(UUID id, String refreshToken) {
        Optional<UserEntity> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            userEntity.setRefreshToken(refreshToken);
            this.userRepository.save(userEntity);
        }
    }
}
