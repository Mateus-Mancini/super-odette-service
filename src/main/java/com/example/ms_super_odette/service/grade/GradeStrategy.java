package com.example.ms_super_odette.service.grade;

import com.example.ms_super_odette.dto.response.GradeResponse;
import com.example.ms_super_odette.model.SessionData;

import java.util.List;

public interface GradeStrategy {
    boolean supports(SessionData user);
    List<GradeResponse> getGrades(SessionData user, Long subjectId, Long classGroupId);
}