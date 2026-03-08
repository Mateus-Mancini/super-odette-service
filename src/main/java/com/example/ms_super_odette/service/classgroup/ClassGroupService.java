package com.example.ms_super_odette.service.classgroup;

import com.example.ms_super_odette.dto.request.ClassGroupRequest;
import com.example.ms_super_odette.dto.response.ClassGroupResponse;
import com.example.ms_super_odette.model.ClassGroup;
import com.example.ms_super_odette.repository.ClassGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ClassGroupService {

    private final ClassGroupRepository classGroupRepo;

    public List<ClassGroupResponse> getAll() {
        return classGroupRepo.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public ClassGroupResponse getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    public ClassGroupResponse upsert(Long id, ClassGroupRequest request) {
        classGroupRepo.findByNameAndYear(request.getName(), request.getYear())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new ResponseStatusException(HttpStatus.CONFLICT,
                                "Já existe uma turma com este nome e ano.");
                    }
                });

        ClassGroup classGroup = (id != null) ? findOrThrow(id) : new ClassGroup();
        classGroup.setName(request.getName());
        classGroup.setYear(request.getYear());

        return toResponse(classGroupRepo.save(classGroup));
    }

    private ClassGroupResponse toResponse(ClassGroup classGroup) {
        return new ClassGroupResponse(
                classGroup.getId(),
                classGroup.getName(),
                classGroup.getYear()
        );
    }

    private ClassGroup findOrThrow(Long id) {
        return classGroupRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Turma não encontrada."));
    }
}