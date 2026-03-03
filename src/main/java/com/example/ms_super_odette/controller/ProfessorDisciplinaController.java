package com.example.ms_super_odette.controller;

import com.example.ms_super_odette.dto.request.ProfessorDisciplinaRequest;
import com.example.ms_super_odette.dto.response.ProfessorDisciplinaResponse;
import com.example.ms_super_odette.model.ProfessorDisciplinaId;
import com.example.ms_super_odette.service.ProfessorDisciplinaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professor-disciplina")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProfessorDisciplinaController {

    private final ProfessorDisciplinaService service;

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('SECRETARIA','PROFESSOR')")
    public ResponseEntity<List<ProfessorDisciplinaResponse>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasAnyRole('SECRETARIA','PROFESSOR')")
    public ResponseEntity<ProfessorDisciplinaResponse> buscarPorId(
            @RequestParam Long professor, @RequestParam Long disciplina) {
        ProfessorDisciplinaId id = new ProfessorDisciplinaId(professor, disciplina);
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping("/inserir")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<ProfessorDisciplinaResponse> inserir(@Valid @RequestBody ProfessorDisciplinaRequest request) {
        return ResponseEntity.ok(service.inserir(request));
    }

    @DeleteMapping("/remover")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<String> remover(@RequestParam Long professor, @RequestParam Long disciplina) {
        ProfessorDisciplinaId id = new ProfessorDisciplinaId(professor, disciplina);
        service.remover(id);
        return ResponseEntity.ok("Associação removida com sucesso");
    }
}