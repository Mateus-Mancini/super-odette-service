package com.example.ms_super_odette.repository;

import com.example.ms_super_odette.model.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassGroupRepository extends JpaRepository<ClassGroup, Long> {
    Optional<ClassGroup> findByNameAndYear(String name, Integer year);
    boolean existsByNameAndYear(String name, Integer year);
}