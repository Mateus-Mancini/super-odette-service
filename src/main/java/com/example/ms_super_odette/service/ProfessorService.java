package com.example.ms_super_odette.service;

import com.example.ms_super_odette.dto.request.ProfessorRequest;
import com.example.ms_super_odette.dto.response.ProfessorResponse;
import com.example.ms_super_odette.model.Professor;
import com.example.ms_super_odette.repository.ProfessorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository repository;

    private final ObjectMapper objectMapper;

    public List<ProfessorResponse> listarTodos() {
        return repository.findAll().stream()
                .map(p -> objectMapper.convertValue(p, ProfessorResponse.class))
                .toList();
    }

    public ProfessorResponse buscarPorId(Long id) {
        Professor professor = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado com id: " + id));
        return objectMapper.convertValue(professor, ProfessorResponse.class);
    }

    @Transactional
    public ProfessorResponse inserir(ProfessorRequest request) {
        Professor professor = objectMapper.convertValue(request, Professor.class);
        Professor salvo = repository.save(professor);
        return objectMapper.convertValue(salvo, ProfessorResponse.class);
    }

    @Transactional
    public ProfessorResponse atualizar(Long id, ProfessorRequest request) {
        Professor professor = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado com id: " + id));
        professor.setNmProfessor(request.getNmProfessor());
        professor.setDtContratacao(request.getDtContratacao());
        Professor atualizado = repository.save(professor);
        return objectMapper.convertValue(atualizado, ProfessorResponse.class);
    }

    @Transactional
    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Professor não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}