package com.example.ms_super_odette.service;

import com.example.ms_super_odette.dto.request.TurmaRequest;
import com.example.ms_super_odette.dto.response.TurmaResponse;
import com.example.ms_super_odette.model.Turma;
import com.example.ms_super_odette.repository.TurmaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository repository;

    private final ObjectMapper objectMapper;

    public List<TurmaResponse> listarTodos() {
        return repository.findAll().stream()
                .map(t -> objectMapper.convertValue(t, TurmaResponse.class))
                .toList();
    }

    public TurmaResponse buscarPorId(Long id) {
        Turma turma = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada com id: " + id));
        return objectMapper.convertValue(turma, TurmaResponse.class);
    }

    @Transactional
    public TurmaResponse inserir(TurmaRequest request) {
        Turma turma = objectMapper.convertValue(request, Turma.class);
        Turma salvo = repository.save(turma);
        return objectMapper.convertValue(salvo, TurmaResponse.class);
    }

    @Transactional
    public TurmaResponse atualizar(Long id, TurmaRequest request) {
        Turma turma = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada com id: " + id));
        turma.setNmTurma(request.getNmTurma());
        turma.setAno(request.getAno());
        Turma atualizado = repository.save(turma);
        return objectMapper.convertValue(atualizado, TurmaResponse.class);
    }

    @Transactional
    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Turma não encontrada com id: " + id);
        }
        repository.deleteById(id);
    }
}