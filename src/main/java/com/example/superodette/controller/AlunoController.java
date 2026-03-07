package com.example.superodette.controller;

import com.example.superodette.dto.request.AlunoRequest;
import com.example.superodette.dto.response.AlunoResponse;
import com.example.superodette.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/aluno")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AlunoController {

    private final AlunoService service;

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('SECRETARIA','PROFESSOR')")
    public ResponseEntity<List<AlunoResponse>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/buscar/{id}")
    @PreAuthorize("hasAnyRole('SECRETARIA','PROFESSOR','ALUNO')")
    public ResponseEntity<AlunoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping("/inserir")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<AlunoResponse> inserir(@Valid @RequestBody AlunoRequest request) {
        return ResponseEntity.ok(service.inserir(request));
    }

    @PutMapping("/atualizar/{id}")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<AlunoResponse> atualizar(@PathVariable Long id,
                                                   @Valid @RequestBody AlunoRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/remover/{id}")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.ok("Aluno removido com sucesso");
    }
}