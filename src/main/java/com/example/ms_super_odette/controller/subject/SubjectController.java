package com.example.ms_super_odette.controller.subject;

import com.example.ms_super_odette.annotation.CurrentUser;
import com.example.ms_super_odette.annotation.RequireRole;
import com.example.ms_super_odette.dto.request.SubjectRequest;
import com.example.ms_super_odette.dto.response.SubjectResponse;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.service.subject.SecretarySubjectService;
import com.example.ms_super_odette.service.subject.SubjectContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectContext dispatcher;
    private final SecretarySubjectService secretarySubjectService;

    @GetMapping
    public ResponseEntity<List<SubjectResponse>> getSubjects(@CurrentUser SessionData user) {
        return ResponseEntity.ok(dispatcher.getSubjects(user));
    }

    @PutMapping({"", "/{id}"})
    @RequireRole("SECRETARIA")
    public ResponseEntity<SubjectResponse> upsertSubject(
            @PathVariable(required = false) Long id,
            @Valid @RequestBody SubjectRequest request) {
        SubjectResponse response = secretarySubjectService.upsert(id, request);
        HttpStatus status = (id == null) ? HttpStatus.CREATED : HttpStatus.OK;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/{id}")
    @RequireRole("SECRETARIA")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        secretarySubjectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}