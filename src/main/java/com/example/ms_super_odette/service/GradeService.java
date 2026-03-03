package com.example.ms_super_odette.service;

import com.example.ms_super_odette.dto.request.GradeRequest;
import com.example.ms_super_odette.dto.response.GradeResponse;
import com.example.ms_super_odette.model.Grade;
import com.example.ms_super_odette.repository.GradeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository repository;

    private final ObjectMapper objectMapper;

    public List<GradeResponse> listarTodos() {
        return repository.findAll().stream()
                .map(g -> objectMapper.convertValue(g, GradeResponse.class))
                .toList();
    }

    public GradeResponse buscarPorId(Long id) {
        Grade grade = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grade não encontrada com id: " + id));
        return objectMapper.convertValue(grade, GradeResponse.class);
    }

    @Transactional
    public GradeResponse inserir(GradeRequest request) {
        Grade grade = objectMapper.convertValue(request, Grade.class);
        Grade salvo = repository.save(grade);
        return objectMapper.convertValue(salvo, GradeResponse.class);
    }

    @Transactional
    public GradeResponse atualizar(Long id, GradeRequest request) {
        Grade grade = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grade não encontrada com id: " + id));
        grade.setCdTurma(request.getCdTurma());
        grade.setCdDisciplina(request.getCdDisciplina());
        grade.setCdProfessor(request.getCdProfessor());
        grade.setHrInicio(request.getHrInicio());
        grade.setHrFim(request.getHrFim());
        grade.setDiaSemana(request.getDiaSemana());
        Grade atualizado = repository.save(grade);
        return objectMapper.convertValue(atualizado, GradeResponse.class);
    }

    @Transactional
    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Grade não encontrada com id: " + id);
        }
        repository.deleteById(id);
    }
}