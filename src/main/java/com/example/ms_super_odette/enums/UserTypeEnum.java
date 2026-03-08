package com.example.ms_super_odette.enums;

import com.example.ms_super_odette.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserTypeEnum {
    STUDENT(new UserType(1, "ALUNO")),
    TEACHER(new UserType(2, "PROFESSOR")),
    SECRETARY(new UserType(3, "SECRETARIA"));

    private final UserType userType;
}