package com.example.ms_super_odette.controller.enrollment;

import com.example.ms_super_odette.annotation.CurrentUser;
import com.example.ms_super_odette.annotation.RequireRole;
import com.example.ms_super_odette.dto.request.EnrollmentApprovalRequest;
import com.example.ms_super_odette.dto.request.StudentRegistrationRequest;
import com.example.ms_super_odette.dto.response.EnrollmentResponse;
import com.example.ms_super_odette.dto.response.StudentRegistrationResponse;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.service.enrollment.SecretaryEnrollmentService;
import com.example.ms_super_odette.service.enrollment.StudentEnrollmentService;
import com.example.ms_super_odette.service.enrollment.StudentRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final StudentRegistrationService studentRegistrationService;
    private final StudentEnrollmentService   studentEnrollmentService;
    private final SecretaryEnrollmentService secretaryEnrollmentService;

    @PostMapping("/register")
    public ResponseEntity<StudentRegistrationResponse> register(
            @Valid @RequestBody StudentRegistrationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentRegistrationService.register(request));
    }

    @GetMapping
    @RequireRole("ALUNO")
    public ResponseEntity<List<EnrollmentResponse>> getOwnEnrollments(
            @CurrentUser SessionData user) {
        return ResponseEntity.ok(studentEnrollmentService.getOwnEnrollments(user));
    }

    @GetMapping("/pending")
    @RequireRole("SECRETARIA")
    public ResponseEntity<List<EnrollmentResponse>> getPendingEnrollments() {
        return ResponseEntity.ok(secretaryEnrollmentService.getPendingEnrollments());
    }

    @PatchMapping("/{id}/approve")
    @RequireRole("SECRETARIA")
    public ResponseEntity<EnrollmentResponse> approve(
            @PathVariable Long id,
            @Valid @RequestBody EnrollmentApprovalRequest request) {
        return ResponseEntity.ok(secretaryEnrollmentService.approve(id, request));
    }

    @PatchMapping("/{id}/reject")
    @RequireRole("SECRETARIA")
    public ResponseEntity<EnrollmentResponse> reject(@PathVariable Long id) {
        return ResponseEntity.ok(secretaryEnrollmentService.reject(id));
    }
}