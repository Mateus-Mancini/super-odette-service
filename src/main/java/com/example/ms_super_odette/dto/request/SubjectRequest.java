package com.example.ms_super_odette.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubjectRequest {
    @NotBlank(message = "Subject name must not be blank.")
    @Size(max = 100, message = "Subject name must not exceed 100 characters.")
    private String name;
}