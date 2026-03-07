package com.example.superodette.service;

import com.example.superodette.dto.request.MatriculaRequest;
import com.example.superodette.dto.response.MatriculaResponse;
import com.example.superodette.model.Matricula;
import com.example.superodette.repository.MatriculaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository repository;

    private final ObjectMapper objectMapper;

    public List<MatriculaResponse> listarTodos() {
        return repository.findAll().stream()
                .map(m -> objectMapper.convertValue(m, MatriculaResponse.class))
                .toList();
    }

    public MatriculaResponse buscarPorId(Long id) {
        Matricula matricula = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Matrícula não encontrada com id: " + id));
        return objectMapper.convertValue(matricula, MatriculaResponse.class);
    }

    @Transactional
    public MatriculaResponse inserir(MatriculaRequest request) {
        Matricula matricula = objectMapper.convertValue(request, Matricula.class);
        Matricula salvo = repository.save(matricula);
        return objectMapper.convertValue(salvo, MatriculaResponse.class);
    }

    @Transactional
    public MatriculaResponse atualizar(Long id, MatriculaRequest request) {
        Matricula matricula = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Matrícula não encontrada com id: " + id));
        matricula.setCdAluno(request.getCdAluno());
        matricula.setCdTurma(request.getCdTurma());
        matricula.setDtMatricula(request.getDtMatricula());
        matricula.setStatus(request.getStatus());
        Matricula atualizado = repository.save(matricula);
        return objectMapper.convertValue(atualizado, MatriculaResponse.class);
    }

    @Transactional
    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Matrícula não encontrada com id: " + id);
        }
        repository.deleteById(id);
    }
}