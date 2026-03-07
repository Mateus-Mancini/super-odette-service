package com.example.superodette.repository;

import com.example.superodette.model.Observacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservacaoRepository extends JpaRepository<Observacao, Long> {
}