package com.example.ms_super_odette.service;

import com.example.ms_super_odette.dto.request.ProfessorDisciplinaRequest;
import com.example.ms_super_odette.dto.response.ProfessorDisciplinaResponse;
import com.example.ms_super_odette.model.ProfessorDisciplina;
import com.example.ms_super_odette.model.ProfessorDisciplinaId;
import com.example.ms_super_odette.repository.ProfessorDisciplinaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessorDisciplinaService {

    private final ProfessorDisciplinaRepository repository;

    private final ObjectMapper objectMapper;

    public List<ProfessorDisciplinaResponse> listarTodos() {
        return repository.findAll().stream()
                .map(pd -> new ProfessorDisciplinaResponse(pd.getId().getCdProfessor(), pd.getId().getCdDisciplina()))
                .collect(Collectors.toList());
    }

    public ProfessorDisciplinaResponse buscarPorId(ProfessorDisciplinaId id) {
        ProfessorDisciplina pd = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Associação não encontrada"));
        return new ProfessorDisciplinaResponse(pd.getId().getCdProfessor(), pd.getId().getCdDisciplina());
    }

    @Transactional
    public ProfessorDisciplinaResponse inserir(ProfessorDisciplinaRequest request) {
        ProfessorDisciplinaId id = new ProfessorDisciplinaId(request.getCdProfessor(), request.getCdDisciplina());
        ProfessorDisciplina pd = new ProfessorDisciplina(id);
        ProfessorDisciplina salvo = repository.save(pd);
        return new ProfessorDisciplinaResponse(salvo.getId().getCdProfessor(), salvo.getId().getCdDisciplina());
    }

    @Transactional
    public void remover(ProfessorDisciplinaId id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Associação não encontrada");
        }
        repository.deleteById(id);
    }
}