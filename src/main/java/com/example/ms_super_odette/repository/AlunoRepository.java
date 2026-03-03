package com.example.ms_super_odette.repository;

import com.example.ms_super_odette.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}