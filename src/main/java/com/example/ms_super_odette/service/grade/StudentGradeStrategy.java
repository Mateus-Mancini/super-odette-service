package com.example.ms_super_odette.service.grade;

import com.example.ms_super_odette.dto.response.GradeResponse;
import com.example.ms_super_odette.model.Grade;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.model.Subject;
import com.example.ms_super_odette.repository.EnrollmentRepository;
import com.example.ms_super_odette.repository.GradeRepository;
import com.example.ms_super_odette.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentGradeStrategy implements GradeStrategy {

    private final GradeRepository gradeRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final SubjectRepository subjectRepo;
    private final GradeMapper mapper;

    public StudentGradeStrategy(GradeRepository gradeRepo,
                                EnrollmentRepository enrollmentRepo,
                                SubjectRepository subjectRepo,
                                GradeMapper mapper) {
        this.gradeRepo      = gradeRepo;
        this.enrollmentRepo = enrollmentRepo;
        this.subjectRepo    = subjectRepo;
        this.mapper         = mapper;
    }

    @Override
    public boolean supports(SessionData user) {
        return user.getRoles().contains("ALUNO");
    }

    @Override
    public List<GradeResponse> getGrades(SessionData user, Long subjectId, Long classGroupId) {
        List<Subject> enrolledSubjects = enrollmentRepo
                .findByStudentId(user.getUserId()).stream()
                .flatMap(enrollment -> subjectRepo
                        .findByClassGroupId(enrollment.getClassGroup().getId()).stream())
                .distinct()
                .toList();

        Map<Long, List<Grade>> gradesBySubject = gradeRepo
                .findByStudentId(user.getUserId()).stream()
                .collect(Collectors.groupingBy(g -> g.getSubject().getId()));

        return enrolledSubjects.stream()
                .map(subject -> mapper.toSingleSubjectResponse(
                        subject,
                        null, null,
                        gradesBySubject.getOrDefault(subject.getId(), List.of())
                ))
                .toList();
    }
}