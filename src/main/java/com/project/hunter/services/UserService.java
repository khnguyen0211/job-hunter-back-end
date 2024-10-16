package com.project.hunter.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.hunter.domain.dto.UserDto;
import com.project.hunter.domain.entities.UserEntity;
import com.project.hunter.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto handleSaveUser(UserEntity userEntity) {
        UserEntity insertedUserEntity = this.userRepository.save(userEntity);
        return new UserDto(insertedUserEntity);
    }

    public List<UserDto> handleGetAllUser() {
        List<UserEntity> userEntities = this.userRepository.findAll();
        return userEntities.stream().map(userEntity -> new UserDto(userEntity)).collect(Collectors.toList());
    }

    public UserDto handleGetUserById(UUID id) {
        Optional<UserEntity> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return new UserDto(optionalUser.get());
        }
        return null;
    }

}
