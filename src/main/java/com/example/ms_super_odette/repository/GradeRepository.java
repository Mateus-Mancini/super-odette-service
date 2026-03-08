package com.example.ms_super_odette.repository;

import com.example.ms_super_odette.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("SELECT g FROM Grade g WHERE g.student.id = :studentId")
    List<Grade> findByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT g FROM Grade g WHERE g.subject.id = :subjectId")
    List<Grade> findBySubjectId(@Param("subjectId") Long subjectId);

    @Query("SELECT g FROM Grade g WHERE g.student.id = :studentId AND g.subject.id = :subjectId")
    List<Grade> findByStudentAndSubject(@Param("studentId") Long studentId,
                                        @Param("subjectId") Long subjectId);

    @Query("SELECT g FROM Grade g WHERE g.student.id = :studentId AND g.subject.id = :subjectId AND g.date = :date")
    Optional<Grade> findByStudentSubjectAndDate(@Param("studentId") Long studentId,
                                                @Param("subjectId") Long subjectId,
                                                @Param("date") LocalDate date);
}