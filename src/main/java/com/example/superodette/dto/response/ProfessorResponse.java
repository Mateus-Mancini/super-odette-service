package com.example.superodette.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProfessorResponse {
    private Long cdUsuario;
    private String nmProfessor;
    private LocalDate dtContratacao;
}