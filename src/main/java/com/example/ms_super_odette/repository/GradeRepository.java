package com.example.ms_super_odette.repository;

import com.example.ms_super_odette.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}