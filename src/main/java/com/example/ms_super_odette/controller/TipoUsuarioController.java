package com.example.ms_super_odette.controller;

import com.example.ms_super_odette.dto.request.TipoUsuarioRequest;
import com.example.ms_super_odette.dto.response.TipoUsuarioResponse;
import com.example.ms_super_odette.service.TipoUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipo-usuario")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TipoUsuarioController {

    private final TipoUsuarioService service;

    @GetMapping("/listar")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<List<TipoUsuarioResponse>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/buscar/{id}")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<TipoUsuarioResponse> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping("/inserir")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<TipoUsuarioResponse> inserir(@Valid @RequestBody TipoUsuarioRequest request) {
        return ResponseEntity.ok(service.inserir(request));
    }

    @PutMapping("/atualizar/{id}")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<TipoUsuarioResponse> atualizar(@PathVariable Integer id,
                                                         @Valid @RequestBody TipoUsuarioRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/remover/{id}")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<String> remover(@PathVariable Integer id) {
        service.remover(id);
        return ResponseEntity.ok("Tipo de usuário removido com sucesso");
    }
}