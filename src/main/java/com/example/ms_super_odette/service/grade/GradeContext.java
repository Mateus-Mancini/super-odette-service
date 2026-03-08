package com.example.ms_super_odette.service.grade;

import com.example.ms_super_odette.dto.response.GradeResponse;
import com.example.ms_super_odette.model.SessionData;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeContext {

    private final List<GradeStrategy> strategies;

    public GradeContext(List<GradeStrategy> strategies) {
        this.strategies = strategies;
    }

    @RegisterReflectionForBinding(GradeResponse.class)
    public List<GradeResponse> getGrades(SessionData user, Long subjectId, Long classGroupId) {
        return strategies.stream()
                .filter(s -> s.supports(user))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Notas não encontradas para esse tipo de usuário."))
                .getGrades(user, subjectId, classGroupId);
    }
}