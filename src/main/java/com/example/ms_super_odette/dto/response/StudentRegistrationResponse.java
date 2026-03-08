package com.example.ms_super_odette.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegistrationResponse {
    private Long userId;
    private String name;
    private String email;
    private LocalDate birthDate;

    private EnrollmentResponse enrollment;
}