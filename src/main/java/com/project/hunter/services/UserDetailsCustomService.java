package com.project.hunter.services;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.hunter.constants.RoleConstant;
import com.project.hunter.domain.entities.UserEntity;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    private final UserService userService;

    public UserDetailsCustomService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userService.handleGetUserLoginByEmail(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Username/Password is not valid");
        }
        return new User(userEntity.getEmail(), userEntity.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(RoleConstant.ROLE_USER)));
    }
}
