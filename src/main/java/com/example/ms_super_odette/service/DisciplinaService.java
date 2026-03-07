package com.example.ms_super_odette.service;

import com.example.ms_super_odette.dto.request.DisciplinaRequest;
import com.example.ms_super_odette.dto.response.DisciplinaResponse;
import com.example.ms_super_odette.model.Disciplina;
import com.example.ms_super_odette.repository.DisciplinaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final DisciplinaRepository repository;

    private final ObjectMapper objectMapper;

    public List<DisciplinaResponse> listarTodos() {
        return repository.findAll().stream()
                .map(d -> objectMapper.convertValue(d, DisciplinaResponse.class))
                .toList();
    }

    public DisciplinaResponse buscarPorId(Long id) {
        Disciplina disciplina = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada com id: " + id));
        return objectMapper.convertValue(disciplina, DisciplinaResponse.class);
    }

    @Transactional
    public DisciplinaResponse inserir(DisciplinaRequest request) {
        Disciplina disciplina = objectMapper.convertValue(request, Disciplina.class);
        Disciplina salvo = repository.save(disciplina);
        return objectMapper.convertValue(salvo, DisciplinaResponse.class);
    }

    @Transactional
    public DisciplinaResponse atualizar(Long id, DisciplinaRequest request) {
        Disciplina disciplina = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada com id: " + id));
        disciplina.setNmDisciplina(request.getNmDisciplina());
        Disciplina atualizado = repository.save(disciplina);
        return objectMapper.convertValue(atualizado, DisciplinaResponse.class);
    }

    @Transactional
    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Disciplina não encontrada com id: " + id);
        }
        repository.deleteById(id);
    }
}