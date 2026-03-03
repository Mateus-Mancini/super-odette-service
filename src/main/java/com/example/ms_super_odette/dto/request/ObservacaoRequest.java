package com.example.ms_super_odette.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ObservacaoRequest {
    @NotNull(message = "Código da observação é obrigatório")
    private Long cdObservacao;

    @NotNull(message = "Código do aluno é obrigatório")
    private Long cdAluno;

    @Size(max = 500, message = "Observação deve ter no máximo 500 caracteres")
    private String observacao;

    private LocalDateTime dtRegistro;
}