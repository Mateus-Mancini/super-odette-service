package com.example.ms_super_odette.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClassGroupRequest {

    @NotBlank(message = "O nome da turma é obrigatório.")
    @Size(max = 100, message = "O nome da turma deve ter no máximo 100 caracteres.")
    private String name;

    @NotNull(message = "O ano é obrigatório.")
    private Integer year;
}