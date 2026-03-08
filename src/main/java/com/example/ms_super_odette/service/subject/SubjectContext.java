package com.example.ms_super_odette.service.subject;

import com.example.ms_super_odette.dto.response.SubjectResponse;
import com.example.ms_super_odette.model.SessionData;
import lombok.RequiredArgsConstructor;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectContext {

    private final List<SubjectStrategy> services;

    @RegisterReflectionForBinding(SubjectResponse.class)
    public List<SubjectResponse> getSubjects(SessionData user) {
        return services.stream()
                .filter(s -> s.supports(user))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nenunhuma visualização disponívél para esse tipo de usuário."))
                .getSubjects(user);
    }
}