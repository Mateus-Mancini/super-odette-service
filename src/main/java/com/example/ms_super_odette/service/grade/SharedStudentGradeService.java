package com.example.ms_super_odette.service.grade;

import com.example.ms_super_odette.dto.response.GradeResponse;
import com.example.ms_super_odette.model.Grade;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.model.Student;
import com.example.ms_super_odette.model.Subject;
import com.example.ms_super_odette.repository.EnrollmentRepository;
import com.example.ms_super_odette.repository.GradeRepository;
import com.example.ms_super_odette.repository.StudentRepository;
import com.example.ms_super_odette.repository.SubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SharedStudentGradeService {

    private final StudentRepository studentRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final SubjectRepository subjectRepo;
    private final GradeRepository gradeRepo;
    private final GradeMapper mapper;

    public SharedStudentGradeService(StudentRepository studentRepo,
                                     EnrollmentRepository enrollmentRepo,
                                     SubjectRepository subjectRepo,
                                     GradeRepository gradeRepo,
                                     GradeMapper mapper) {
        this.studentRepo    = studentRepo;
        this.enrollmentRepo = enrollmentRepo;
        this.subjectRepo    = subjectRepo;
        this.gradeRepo      = gradeRepo;
        this.mapper         = mapper;
    }

    public List<GradeResponse> getStudentGrades(SessionData requester, Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aluno não encontrado."));

        List<Subject> enrolledSubjects = enrollmentRepo
                .findByStudentId(studentId).stream()
                .flatMap(enrollment -> subjectRepo
                        .findByClassGroupId(enrollment.getClassGroup().getId()).stream())
                .distinct()
                .toList();

        Map<Long, List<Grade>> gradesBySubject = gradeRepo
                .findByStudentId(studentId).stream()
                .collect(Collectors.groupingBy(g -> g.getSubject().getId()));

        return enrolledSubjects.stream()
                .map(subject -> mapper.toSingleSubjectResponse(
                        subject,
                        studentId,
                        student.getUser().getName(),
                        gradesBySubject.getOrDefault(subject.getId(), List.of())
                ))
                .toList();
    }
}