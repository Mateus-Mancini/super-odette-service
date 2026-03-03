package com.example.ms_super_odette.controller;

import com.example.ms_super_odette.dto.request.ProfessorRequest;
import com.example.ms_super_odette.dto.response.ProfessorResponse;
import com.example.ms_super_odette.service.ProfessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professor")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProfessorController {

    private final ProfessorService service;

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('SECRETARIA','PROFESSOR')")
    public ResponseEntity<List<ProfessorResponse>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/buscar/{id}")
    @PreAuthorize("hasAnyRole('SECRETARIA','PROFESSOR')")
    public ResponseEntity<ProfessorResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping("/inserir")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<ProfessorResponse> inserir(@Valid @RequestBody ProfessorRequest request) {
        return ResponseEntity.ok(service.inserir(request));
    }

    @PutMapping("/atualizar/{id}")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<ProfessorResponse> atualizar(@PathVariable Long id,
                                                       @Valid @RequestBody ProfessorRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/remover/{id}")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.ok("Professor removido com sucesso");
    }
}