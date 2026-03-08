package com.example.ms_super_odette.service.student;

import com.example.ms_super_odette.dto.response.StudentResponse;
import com.example.ms_super_odette.model.SessionData;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentContext {

    private final List<StudentStrategy> strategies;

    public StudentContext(List<StudentStrategy> strategies) {
        this.strategies = strategies;
    }

    @RegisterReflectionForBinding(StudentResponse.class)
    public List<StudentResponse> getStudents(SessionData user) {
        return strategies.stream()
                .filter(s -> s.supports(user))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No student strategy found for user role."))
                .getStudents(user);
    }
}