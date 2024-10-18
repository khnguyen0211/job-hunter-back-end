package com.project.hunter.domain.entities;

import java.time.Instant;
import java.util.UUID;

import com.project.hunter.constants.GenderEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "users")
@Table()
public class UserEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private String fullName;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private String refreshToken;
    
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private RoleEntity role;
    
    private Instant createdAt = Instant.now();
    private UUID createdBy;
    private Instant updatedAt = Instant.now();
    private UUID updatedBy;
    private Instant deletedAt;
    private UUID deletedBy;
    
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public GenderEnum getGender() {
        return gender;
    }
    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    public UUID getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }
    public Instant getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
    public UUID getUpdatedBy() {
        return updatedBy;
    }
    public void setUpdatedBy(UUID updatedBy) {
        this.updatedBy = updatedBy;
    }
    public Instant getDeletedAt() {
        return deletedAt;
    }
    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }
    public UUID getDeletedBy() {
        return deletedBy;
    }
    public void setDeletedBy(UUID deletedBy) {
        this.deletedBy = deletedBy;
    }
    @Override
    public String toString() {
        return "UserEntity [id=" + id + ", fullName=" + fullName + ", email=" + email
                + ", password=" + password + ", address=" + address + ", phoneNumber=" + phoneNumber
                + ", gender=" + gender + "]";
    }
    public RoleEntity getRole() {
        return role;
    }
    public void setRole(RoleEntity role) {
        this.role = role;
    }
    
}
