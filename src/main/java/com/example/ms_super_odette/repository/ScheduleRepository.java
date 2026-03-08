package com.example.ms_super_odette.repository;

import com.example.ms_super_odette.model.ClassGroup;
import com.example.ms_super_odette.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s WHERE s.classGroup IN :classGroups")
    List<Schedule> findByClassGroupIn(@Param("classGroups") List<ClassGroup> classGroups);

    @Query("SELECT s FROM Schedule s WHERE s.teacher.id = :teacherId")
    List<Schedule> findByTeacherId(@Param("teacherId") Long teacherId);

    @Query("SELECT s FROM Schedule s WHERE s.classGroup.id = :classGroupId")
    List<Schedule> findByClassGroupId(@Param("classGroupId") Long classGroupId);

    @Query("SELECT s FROM Schedule s WHERE s.teacher.id = :teacherId AND s.classGroup.id = :classGroupId")
    List<Schedule> findByTeacherIdAndClassGroupId(@Param("teacherId") Long teacherId,
                                                  @Param("classGroupId") Long classGroupId);

    @Query("SELECT DISTINCT s.classGroup FROM Schedule s WHERE s.teacher.id = :teacherId AND s.subject.id = :subjectId")
    List<ClassGroup> findClassGroupsByTeacherIdAndSubjectId(@Param("teacherId") Long teacherId,
                                                            @Param("subjectId") Long subjectId);
}