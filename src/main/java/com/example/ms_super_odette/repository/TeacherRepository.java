package com.example.ms_super_odette.repository;

import com.example.ms_super_odette.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}