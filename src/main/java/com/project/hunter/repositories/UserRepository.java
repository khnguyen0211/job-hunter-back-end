package com.project.hunter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hunter.domain.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Object>{
    
}
