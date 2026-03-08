package com.example.ms_super_odette.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ObservationRequest {

    @NotNull(message = "O ID do aluno é obrigatório.")
    private Long studentId;

    @Size(max = 500, message = "A observação deve ter no máximo 500 caracteres.")
    private String text;
}