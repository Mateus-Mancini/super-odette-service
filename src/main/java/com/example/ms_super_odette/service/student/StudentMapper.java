package com.example.ms_super_odette.service.student;

import com.example.ms_super_odette.dto.response.StudentResponse;
import com.example.ms_super_odette.model.Enrollment;
import com.example.ms_super_odette.enums.EnrollmentStatus;
import com.example.ms_super_odette.model.Student;
import com.example.ms_super_odette.repository.EnrollmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class StudentMapper {

    private final EnrollmentRepository enrollmentRepo;

    public StudentResponse toResponse(Student student) {
        Enrollment activeEnrollment = enrollmentRepo.findByStudentId(student.getId())
                .stream()
                .filter(e -> e.getStatus() == EnrollmentStatus.ENROLLED)
                .findFirst()
                .orElse(null);

        Long classGroupId     = null;
        String classGroupName = null;
        Integer classGroupYear = null;

        if (activeEnrollment != null && activeEnrollment.getClassGroup() != null) {
            classGroupId   = activeEnrollment.getClassGroup().getId();
            classGroupName = activeEnrollment.getClassGroup().getName();
            classGroupYear = activeEnrollment.getClassGroup().getYear();
        }

        return new StudentResponse(
                student.getId(),
                student.getUser().getName(),
                student.getUser().getEmail(),
                student.getBirthDate(),
                classGroupId,
                classGroupName,
                classGroupYear
        );
    }

    public List<StudentResponse> toResponseList(List<Student> students) {
        return students.stream()
                .map(this::toResponse)
                .toList();
    }
}