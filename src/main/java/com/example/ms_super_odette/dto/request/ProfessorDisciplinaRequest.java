package com.example.ms_super_odette.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProfessorDisciplinaRequest {
    @NotNull(message = "Código do professor é obrigatório")
    private Long cdProfessor;

    @NotNull(message = "Código da disciplina é obrigatório")
    private Long cdDisciplina;
}