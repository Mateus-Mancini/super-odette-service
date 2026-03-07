package com.example.superodette.service;

import com.example.superodette.dto.request.AlunoRequest;
import com.example.superodette.dto.response.AlunoResponse;
import com.example.superodette.model.Aluno;
import com.example.superodette.repository.AlunoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository repository;

    private final ObjectMapper objectMapper;

    public List<AlunoResponse> listarTodos() {
        return repository.findAll().stream()
                .map(a -> objectMapper.convertValue(a, AlunoResponse.class))
                .toList();
    }

    public AlunoResponse buscarPorId(Long id) {
        Aluno aluno = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com id: " + id));
        return objectMapper.convertValue(aluno, AlunoResponse.class);
    }

    @Transactional
    public AlunoResponse inserir(AlunoRequest request) {
        Aluno aluno = objectMapper.convertValue(request, Aluno.class);
        Aluno salvo = repository.save(aluno);
        return objectMapper.convertValue(salvo, AlunoResponse.class);
    }

    @Transactional
    public AlunoResponse atualizar(Long id, AlunoRequest request) {
        Aluno aluno = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com id: " + id));
        aluno.setNmAluno(request.getNmAluno());
        aluno.setDtNascimento(request.getDtNascimento());
        Aluno atualizado = repository.save(aluno);
        return objectMapper.convertValue(atualizado, AlunoResponse.class);
    }

    @Transactional
    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Aluno não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}