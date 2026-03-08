package com.example.ms_super_odette.controller.grade;

import com.example.ms_super_odette.annotation.CurrentUser;
import com.example.ms_super_odette.annotation.RequireRole;
import com.example.ms_super_odette.dto.request.GradeRequest;
import com.example.ms_super_odette.dto.response.GradeResponse;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.service.grade.GradeContext;
import com.example.ms_super_odette.service.grade.SharedStudentGradeService;
import com.example.ms_super_odette.service.grade.TeacherGradeUpsertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeContext              gradeContext;
    private final SharedStudentGradeService sharedStudentGradeService;
    private final TeacherGradeUpsertService teacherGradeUpsertService;

    @GetMapping
    public ResponseEntity<List<GradeResponse>> getGrades(
            @CurrentUser SessionData user,
            @RequestParam(required = false) Long subjectId,
            @RequestParam(required = false) Long classGroupId) {
        return ResponseEntity.ok(gradeContext.getGrades(user, subjectId, classGroupId));
    }

    @GetMapping("/student/{studentId}")
    @RequireRole({"PROFESSOR", "SECRETARIA"})
    public ResponseEntity<List<GradeResponse>> getStudentGrades(
            @CurrentUser SessionData user,
            @PathVariable Long studentId) {
        return ResponseEntity.ok(sharedStudentGradeService.getStudentGrades(user, studentId));
    }

    @PutMapping
    @RequireRole("PROFESSOR")
    public ResponseEntity<GradeResponse> upsertGrade(
            @CurrentUser SessionData user,
            @Valid @RequestBody GradeRequest request) {
        return ResponseEntity.ok(teacherGradeUpsertService.upsert(user, request));
    }
}