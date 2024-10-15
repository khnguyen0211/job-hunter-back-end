package com.project.hunter.services;

import org.springframework.stereotype.Service;

import com.project.hunter.domain.entities.UserEntity;
import com.project.hunter.repositories.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    } 

    public UserEntity handleSaveUser(UserEntity userEntity) {
        return this.userRepository.save(userEntity);
    }
}
