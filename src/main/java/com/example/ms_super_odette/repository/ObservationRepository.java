package com.example.ms_super_odette.repository;

import com.example.ms_super_odette.model.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObservationRepository extends JpaRepository<Observation, Long> {
    List<Observation> findByStudentId(@Param("studentId") Long studentId);
}