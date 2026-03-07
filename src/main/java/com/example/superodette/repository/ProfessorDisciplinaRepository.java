package com.example.superodette.repository;

import com.example.superodette.model.ProfessorDisciplina;
import com.example.superodette.model.ProfessorDisciplinaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorDisciplinaRepository extends JpaRepository<ProfessorDisciplina, ProfessorDisciplinaId> {
}