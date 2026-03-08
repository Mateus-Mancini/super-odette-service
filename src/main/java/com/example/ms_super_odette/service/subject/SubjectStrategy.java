package com.example.ms_super_odette.service.subject;

import com.example.ms_super_odette.dto.response.SubjectResponse;
import com.example.ms_super_odette.model.SessionData;

import java.util.List;

public interface SubjectStrategy {
    boolean supports(SessionData user);
    List<SubjectResponse> getSubjects(SessionData user);
}