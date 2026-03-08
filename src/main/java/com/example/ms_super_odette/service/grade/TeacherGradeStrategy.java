package com.example.ms_super_odette.service.grade;

import com.example.ms_super_odette.dto.response.GradeResponse;
import com.example.ms_super_odette.model.Grade;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.model.Student;
import com.example.ms_super_odette.model.Subject;
import com.example.ms_super_odette.repository.EnrollmentRepository;
import com.example.ms_super_odette.repository.GradeRepository;
import com.example.ms_super_odette.repository.SubjectRepository;
import com.example.ms_super_odette.repository.TeacherSubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherGradeStrategy implements GradeStrategy {

    private final TeacherSubjectRepository teacherSubjectRepo;
    private final SubjectRepository subjectRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final GradeRepository gradeRepo;
    private final GradeMapper mapper;

    @Override
    public boolean supports(SessionData user) {
        return user.getRoles().contains("PROFESSOR");
    }

    @Override
    public List<GradeResponse> getGrades(SessionData user, Long subjectId, Long classGroupId) {
        if (subjectId == null || classGroupId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Os parâmetros subjectId e classGroupId são obrigatórios.");
        }

        if (!teacherSubjectRepo.existsByTeacherIdAndSubjectId(user.getUserId(), subjectId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Você não está atribuído a esta disciplina.");
        }

        Subject subject = subjectRepo.findById(subjectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Disciplina não encontrada."));

        List<Student> students = enrollmentRepo.findStudentsByClassGroupId(classGroupId);

        Map<Long, List<Grade>> gradesByStudent = gradeRepo
                .findBySubjectId(subjectId).stream()
                .collect(Collectors.groupingBy(g -> g.getStudent().getId()));

        return students.stream()
                .map(student -> mapper.toSingleSubjectResponse(
                        subject,
                        student.getId(),
                        student.getUser().getName(),
                        gradesByStudent.getOrDefault(student.getId(), List.of())
                ))
                .toList();
    }
}