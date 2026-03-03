package com.example.ms_super_odette.repository;

import com.example.ms_super_odette.model.ProfessorDisciplina;
import com.example.ms_super_odette.model.ProfessorDisciplinaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorDisciplinaRepository extends JpaRepository<ProfessorDisciplina, ProfessorDisciplinaId> {
}