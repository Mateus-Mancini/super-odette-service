package com.example.ms_super_odette.controller.student;

import com.example.ms_super_odette.annotation.CurrentUser;
import com.example.ms_super_odette.annotation.RequireRole;
import com.example.ms_super_odette.dto.response.StudentResponse;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.service.student.StudentContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentContext studentContext;

    @GetMapping
    @RequireRole({"PROFESSOR", "SECRETARIA"})
    public ResponseEntity<List<StudentResponse>> getStudents(@CurrentUser SessionData user) {
        return ResponseEntity.ok(studentContext.getStudents(user));
    }
}