package com.example.ms_super_odette.controller.teacher;

import com.example.ms_super_odette.annotation.RequireRole;
import com.example.ms_super_odette.dto.request.TeacherRequest;
import com.example.ms_super_odette.dto.response.TeacherResponse;
import com.example.ms_super_odette.service.teacher.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    @RequireRole({"ALUNO", "PROFESSOR", "SECRETARIA"})
    public ResponseEntity<List<TeacherResponse>> getAll() {
        return ResponseEntity.ok(teacherService.getAll());
    }

    @GetMapping("/{id}")
    @RequireRole({"ALUNO", "SECRETARIA"})
    public ResponseEntity<TeacherResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getById(id));
    }

    @PutMapping({"", "/{id}"})
    @RequireRole("SECRETARIA")
    public ResponseEntity<TeacherResponse> upsert(
            @PathVariable(required = false) Long id,
            @Valid @RequestBody TeacherRequest request) {
        TeacherResponse response = teacherService.upsert(id, request);
        HttpStatus status = (id == null) ? HttpStatus.CREATED : HttpStatus.OK;
        return ResponseEntity.status(status).body(response);
    }
}