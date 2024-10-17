package com.project.hunter.domain.dto.users;


import java.util.UUID;

import com.project.hunter.domain.entities.UserEntity;

public class UserDto {
    private UUID id;
    private String fullName;
    private String email;
    private String address;
    private String phoneNumber;

    public UserDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.fullName = userEntity.getFullName();
        this.email = userEntity.getEmail();
        this.address = userEntity.getAddress();
        this.phoneNumber = userEntity.getPhoneNumber();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
