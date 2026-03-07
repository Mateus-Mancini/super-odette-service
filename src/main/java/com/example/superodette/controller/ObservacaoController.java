package com.example.superodette.controller;

import com.example.superodette.dto.request.ObservacaoRequest;
import com.example.superodette.dto.response.ObservacaoResponse;
import com.example.superodette.service.ObservacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/observacao")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ObservacaoController {

    private final ObservacaoService service;

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('SECRETARIA','PROFESSOR')")
    public ResponseEntity<List<ObservacaoResponse>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/buscar/{id}")
    @PreAuthorize("hasAnyRole('SECRETARIA','PROFESSOR','ALUNO')")
    public ResponseEntity<ObservacaoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping("/inserir")
    @PreAuthorize("hasRole('SECRETARIA') or hasRole('PROFESSOR')")
    public ResponseEntity<ObservacaoResponse> inserir(@Valid @RequestBody ObservacaoRequest request) {
        return ResponseEntity.ok(service.inserir(request));
    }

    @PutMapping("/atualizar/{id}")
    @PreAuthorize("hasRole('SECRETARIA') or hasRole('PROFESSOR')")
    public ResponseEntity<ObservacaoResponse> atualizar(@PathVariable Long id,
                                                        @Valid @RequestBody ObservacaoRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/remover/{id}")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.ok("Observação removida com sucesso");
    }
}