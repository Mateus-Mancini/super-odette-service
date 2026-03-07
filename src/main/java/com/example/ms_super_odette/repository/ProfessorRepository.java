package com.example.ms_super_odette.repository;

import com.example.ms_super_odette.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}