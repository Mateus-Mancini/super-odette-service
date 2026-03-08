package com.example.ms_super_odette.service.enrollment;

import com.example.ms_super_odette.dto.response.EnrollmentResponse;
import com.example.ms_super_odette.model.Enrollment;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    public EnrollmentResponse toResponse(Enrollment enrollment) {
        return toResponse(enrollment, false);
    }

    public EnrollmentResponse toResponse(Enrollment enrollment, boolean includeStudent) {
        Long classGroupId     = null;
        String classGroupName = null;
        Integer classGroupYear = null;

        if (enrollment.getClassGroup() != null) {
            classGroupId   = enrollment.getClassGroup().getId();
            classGroupName = enrollment.getClassGroup().getName();
            classGroupYear = enrollment.getClassGroup().getYear();
        }

        Long studentId     = includeStudent ? enrollment.getStudent().getId()              : null;
        String studentName = includeStudent ? enrollment.getStudent().getUser().getName()  : null;

        return new EnrollmentResponse(
                enrollment.getId(),
                enrollment.getStatus(),
                enrollment.getEnrolledAt(),
                classGroupId,
                classGroupName,
                classGroupYear,
                studentId,
                studentName
        );
    }
}