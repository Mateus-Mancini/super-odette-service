package com.example.ms_super_odette.service.student;

import com.example.ms_super_odette.dto.response.StudentResponse;
import com.example.ms_super_odette.model.SessionData;

import java.util.List;

public interface StudentStrategy {
    boolean supports(SessionData user);
    List<StudentResponse> getStudents(SessionData user);
}