package com.example.ms_super_odette.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DisciplinaRequest {
    @NotNull(message = "Código da disciplina é obrigatório")
    private Long cdDisciplina;

    @NotBlank(message = "Nome da disciplina é obrigatório")
    @Size(max = 100)
    private String nmDisciplina;
}