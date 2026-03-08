package com.example.ms_super_odette.service.teacher;

import com.example.ms_super_odette.dto.response.TeacherResponse;
import com.example.ms_super_odette.model.Teacher;
import com.example.ms_super_odette.model.TeacherSubject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherMapper {

    public TeacherResponse toResponse(Teacher teacher, List<TeacherSubject> assignments) {
        List<TeacherResponse.SubjectSummary> subjects = assignments.stream()
                .map(ts -> new TeacherResponse.SubjectSummary(
                        ts.getSubject().getId(),
                        ts.getSubject().getName()
                ))
                .toList();

        return new TeacherResponse(
                teacher.getId(),
                teacher.getUser().getName(),
                teacher.getUser().getEmail(),
                teacher.getHiredAt(),
                subjects
        );
    }
}