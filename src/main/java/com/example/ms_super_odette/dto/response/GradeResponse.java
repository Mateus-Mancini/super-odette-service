package com.example.ms_super_odette.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradeResponse {

    private Long subjectId;
    private String subjectName;

    private BigDecimal firstSemester;
    private BigDecimal secondSemester;
    private BigDecimal average;

    private Long studentId;
    private String studentName;
}