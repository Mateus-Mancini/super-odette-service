package com.example.ms_super_odette.controller.classgroup;

import com.example.ms_super_odette.annotation.RequireRole;
import com.example.ms_super_odette.dto.request.ClassGroupRequest;
import com.example.ms_super_odette.dto.response.ClassGroupResponse;
import com.example.ms_super_odette.service.classgroup.ClassGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class-groups")
@RequiredArgsConstructor
public class ClassGroupController {

    private final ClassGroupService classGroupService;

    @GetMapping
    @RequireRole({"ALUNO", "PROFESSOR", "SECRETARIA"})
    public ResponseEntity<List<ClassGroupResponse>> getAll() {
        return ResponseEntity.ok(classGroupService.getAll());
    }

    @GetMapping("/{id}")
    @RequireRole({"ALUNO", "SECRETARIA"})
    public ResponseEntity<ClassGroupResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(classGroupService.getById(id));
    }

    @PutMapping({"", "/{id}"})
    @RequireRole("SECRETARIA")
    public ResponseEntity<ClassGroupResponse> upsert(
            @PathVariable(required = false) Long id,
            @Valid @RequestBody ClassGroupRequest request) {
        ClassGroupResponse response = classGroupService.upsert(id, request);
        HttpStatus status = (id == null) ? HttpStatus.CREATED : HttpStatus.OK;
        return ResponseEntity.status(status).body(response);
    }
}