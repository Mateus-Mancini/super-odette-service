package com.example.superodette.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProfessorDisciplinaRequest {
    @NotNull(message = "Código do professor é obrigatório")
    private Long cdProfessor;

    @NotNull(message = "Código da disciplina é obrigatório")
    private Long cdDisciplina;
}