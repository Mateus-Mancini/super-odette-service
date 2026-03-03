package com.example.ms_super_odette.repository;

import com.example.ms_super_odette.model.Observacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservacaoRepository extends JpaRepository<Observacao, Long> {
}