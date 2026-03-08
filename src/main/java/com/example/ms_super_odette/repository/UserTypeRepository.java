package com.example.ms_super_odette.repository;

import com.example.ms_super_odette.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
    Optional<UserType> findByDescription(String description);
}