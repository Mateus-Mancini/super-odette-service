package com.example.ms_super_odette.repository;

import com.example.ms_super_odette.model.TeacherSubject;
import com.example.ms_super_odette.model.TeacherSubjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, TeacherSubjectId> {

    @Query("SELECT ts FROM TeacherSubject ts WHERE ts.teacher.id = :teacherId")
    List<TeacherSubject> findByTeacherId(@Param("teacherId") Long teacherId);

    @Query("SELECT ts.subject.id FROM TeacherSubject ts WHERE ts.teacher.id = :teacherId")
    List<Long> findSubjectIdsByTeacherId(@Param("teacherId") Long teacherId);

    @Query("SELECT COUNT(ts) > 0 FROM TeacherSubject ts WHERE ts.teacher.id = :teacherId AND ts.subject.id = :subjectId")
    boolean existsByTeacherIdAndSubjectId(@Param("teacherId") Long teacherId,
                                          @Param("subjectId") Long subjectId);

    @Modifying
    @Transactional
    @Query("DELETE FROM TeacherSubject ts WHERE ts.teacher.id = :teacherId")
    void deleteAllByTeacherId(@Param("teacherId") Long teacherId);
}