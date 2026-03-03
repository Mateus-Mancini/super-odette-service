package com.example.ms_super_odette.controller;

import com.example.ms_super_odette.dto.request.NotaRequest;
import com.example.ms_super_odette.dto.response.NotaResponse;
import com.example.ms_super_odette.service.NotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nota")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NotaController {

    private final NotaService service;

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('SECRETARIA','PROFESSOR')")
    public ResponseEntity<List<NotaResponse>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/buscar/{id}")
    @PreAuthorize("hasAnyRole('SECRETARIA','PROFESSOR','ALUNO')")
    public ResponseEntity<NotaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping("/inserir")
    @PreAuthorize("hasRole('SECRETARIA') or hasRole('PROFESSOR')")
    public ResponseEntity<NotaResponse> inserir(@Valid @RequestBody NotaRequest request) {
        return ResponseEntity.ok(service.inserir(request));
    }

    @PutMapping("/atualizar/{id}")
    @PreAuthorize("hasRole('SECRETARIA') or hasRole('PROFESSOR')")
    public ResponseEntity<NotaResponse> atualizar(@PathVariable Long id,
                                                  @Valid @RequestBody NotaRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/remover/{id}")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.ok("Nota removida com sucesso");
    }
}