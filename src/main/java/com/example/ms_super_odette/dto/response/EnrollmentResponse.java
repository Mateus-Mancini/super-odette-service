package com.example.ms_super_odette.dto.response;

import com.example.ms_super_odette.enums.EnrollmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponse {
    private Long id;
    private EnrollmentStatus status;
    private LocalDate enrolledAt;

    private Long classGroupId;
    private String classGroupName;
    private Integer classGroupYear;

    private Long studentId;
    private String studentName;
}