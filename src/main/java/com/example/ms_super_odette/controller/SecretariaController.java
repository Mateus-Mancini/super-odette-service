package com.example.ms_super_odette.controller;

import com.example.ms_super_odette.dto.request.SecretariaRequest;
import com.example.ms_super_odette.dto.response.SecretariaResponse;
import com.example.ms_super_odette.service.SecretariaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/secretaria")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SecretariaController {

    private final SecretariaService service;

    @GetMapping("/listar")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<List<SecretariaResponse>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/buscar/{id}")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<SecretariaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping("/inserir")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<SecretariaResponse> inserir(@Valid @RequestBody SecretariaRequest request) {
        return ResponseEntity.ok(service.inserir(request));
    }

    @PutMapping("/atualizar/{id}")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<SecretariaResponse> atualizar(@PathVariable Long id,
                                                        @Valid @RequestBody SecretariaRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/remover/{id}")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.ok("Secretaria removida com sucesso");
    }
}