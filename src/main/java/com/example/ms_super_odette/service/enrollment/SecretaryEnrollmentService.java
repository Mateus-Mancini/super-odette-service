package com.example.ms_super_odette.service.enrollment;

import com.example.ms_super_odette.dto.request.EnrollmentApprovalRequest;
import com.example.ms_super_odette.dto.response.EnrollmentResponse;
import com.example.ms_super_odette.model.Enrollment;
import com.example.ms_super_odette.enums.EnrollmentStatus;
import com.example.ms_super_odette.repository.ClassGroupRepository;
import com.example.ms_super_odette.repository.EnrollmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class SecretaryEnrollmentService {

    private final EnrollmentRepository enrollmentRepo;
    private final ClassGroupRepository classGroupRepo;
    private final EnrollmentMapper mapper;

    public List<EnrollmentResponse> getPendingEnrollments() {
        return enrollmentRepo.findByStatus(EnrollmentStatus.PENDING).stream()
                .map(e -> mapper.toResponse(e, true))
                .toList();
    }

    public EnrollmentResponse approve(Long enrollmentId, EnrollmentApprovalRequest request) {
        Enrollment enrollment = findPendingOrThrow(enrollmentId);

        var classGroup = classGroupRepo.findById(request.getClassGroupId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Turma não encontrada."));

        // Guard against enrolling a student in a class group they're already active in
        enrollmentRepo.findByStudentIdAndClassGroupId(
                enrollment.getStudent().getId(), classGroup.getId()
        ).ifPresent(existing -> {
            if (existing.getStatus() == EnrollmentStatus.ENROLLED) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "O aluno já está matriculado nesta turma.");
            }
        });

        enrollment.setClassGroup(classGroup);
        enrollment.setStatus(EnrollmentStatus.ENROLLED);

        return mapper.toResponse(enrollmentRepo.save(enrollment), true);
    }

    public EnrollmentResponse reject(Long enrollmentId) {
        Enrollment enrollment = findPendingOrThrow(enrollmentId);
        enrollment.setStatus(EnrollmentStatus.REJECTED);
        return mapper.toResponse(enrollmentRepo.save(enrollment), true);
    }

    private Enrollment findPendingOrThrow(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepo.findById(enrollmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matrícula não encontrada."));

        if (enrollment.getStatus() != EnrollmentStatus.PENDING) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Apenas solicitações pendentes podem ser processadas.");
        }

        return enrollment;
    }
}