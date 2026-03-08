package com.example.ms_super_odette.service.enrollment;

import com.example.ms_super_odette.dto.response.EnrollmentResponse;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.repository.EnrollmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class StudentEnrollmentService {

    private final EnrollmentRepository enrollmentRepo;
    private final EnrollmentMapper mapper;

    public List<EnrollmentResponse> getOwnEnrollments(SessionData user) {
        return enrollmentRepo.findByStudentId(user.getUserId()).stream()
                .map(mapper::toResponse)
                .toList();
    }
}