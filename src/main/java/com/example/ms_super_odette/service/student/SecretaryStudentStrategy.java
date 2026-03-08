package com.example.ms_super_odette.service.student;

import com.example.ms_super_odette.dto.response.StudentResponse;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SecretaryStudentStrategy implements StudentStrategy {

    private final StudentRepository studentRepo;
    private final StudentMapper mapper;

    @Override
    public boolean supports(SessionData user) {
        return user.getRoles().contains("SECRETARIA");
    }

    @Override
    public List<StudentResponse> getStudents(SessionData user) {
        return mapper.toResponseList(studentRepo.findAll());
    }
}