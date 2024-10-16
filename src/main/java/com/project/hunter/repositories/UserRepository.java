package com.project.hunter.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hunter.domain.entities.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID>{
    List<UserEntity> findByEmail(String email);
}
