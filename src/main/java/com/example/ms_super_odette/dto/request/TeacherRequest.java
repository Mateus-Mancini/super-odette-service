package com.example.ms_super_odette.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TeacherRequest {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres.")
    private String name;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail informado é inválido.")
    @Size(max = 150, message = "O e-mail deve ter no máximo 150 caracteres.")
    private String email;

    // Only required on creation — ignored on update if blank
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
    private String password;

    private LocalDate hiredAt;

    // Subject IDs to assign — replaces current assignments entirely on update
    private List<Long> subjectIds;
}