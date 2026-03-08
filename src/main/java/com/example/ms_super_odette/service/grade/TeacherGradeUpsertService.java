package com.example.ms_super_odette.service.grade;

import com.example.ms_super_odette.dto.request.GradeRequest;
import com.example.ms_super_odette.dto.response.GradeResponse;
import com.example.ms_super_odette.model.Grade;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.model.Student;
import com.example.ms_super_odette.model.Subject;
import com.example.ms_super_odette.repository.GradeRepository;
import com.example.ms_super_odette.repository.StudentRepository;
import com.example.ms_super_odette.repository.SubjectRepository;
import com.example.ms_super_odette.repository.TeacherSubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class TeacherGradeUpsertService {

    private final GradeRepository gradeRepo;
    private final TeacherSubjectRepository teacherSubjectRepo;
    private final StudentRepository studentRepo;
    private final SubjectRepository subjectRepo;
    private final GradeMapper mapper;

    public TeacherGradeUpsertService(GradeRepository gradeRepo,
                                     TeacherSubjectRepository teacherSubjectRepo,
                                     StudentRepository studentRepo,
                                     SubjectRepository subjectRepo,
                                     GradeMapper mapper) {
        this.gradeRepo          = gradeRepo;
        this.teacherSubjectRepo = teacherSubjectRepo;
        this.studentRepo        = studentRepo;
        this.subjectRepo        = subjectRepo;
        this.mapper             = mapper;
    }

    public GradeResponse upsert(SessionData teacher, GradeRequest request) {
        validateTeacherOwnsSubject(teacher.getUserId(), request.getSubjectId());

        Student student = studentRepo.findById(request.getStudentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aluno não encontrado."));

        Subject subject = subjectRepo.findById(request.getSubjectId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Disciplina não encontrada."));

        int year = LocalDate.now().getYear();

        if (request.getFirstSemester() != null) {
            upsertGrade(student, subject, request.getFirstSemester(),
                    LocalDate.of(year, Month.JULY, 1));
        }
        if (request.getSecondSemester() != null) {
            upsertGrade(student, subject, request.getSecondSemester(),
                    LocalDate.of(year, Month.DECEMBER, 1));
        }

        List<Grade> updated = gradeRepo.findByStudentAndSubject(
                student.getId(), subject.getId());

        return mapper.toSingleSubjectResponse(
                subject,
                student.getId(),
                student.getUser().getName(),
                updated
        );
    }

    private void validateTeacherOwnsSubject(Long teacherId, Long subjectId) {
        if (!teacherSubjectRepo.existsByTeacherIdAndSubjectId(teacherId, subjectId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Você não está atribuído a esta disciplina.");
        }
    }

    private void upsertGrade(Student student, Subject subject, BigDecimal value, LocalDate date) {
        Grade grade = gradeRepo.findByStudentSubjectAndDate(
                        student.getId(), subject.getId(), date)
                .orElse(new Grade());
        grade.setStudent(student);
        grade.setSubject(subject);
        grade.setValue(value);
        grade.setDate(date);
        gradeRepo.save(grade);
    }
}