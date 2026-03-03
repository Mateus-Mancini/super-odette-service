package com.example.ms_super_odette.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProfessorRequest {
    @NotNull(message = "Código do usuário é obrigatório")
    private Long cdUsuario;

    @NotBlank(message = "Nome do professor é obrigatório")
    @Size(max = 150)
    private String nmProfessor;

    private LocalDate dtContratacao;
}