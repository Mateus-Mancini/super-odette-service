package com.example.ms_super_odette.repository;

import com.example.ms_super_odette.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("""
            SELECT DISTINCT e.student
            FROM Schedule s
            JOIN Enrollment e ON e.classGroup.id = s.classGroup.id
            WHERE s.teacher.id = :teacherId
            AND e.status = 'ENROLLED'
            """)
    List<Student> findByTeacherId(@Param("teacherId") Long teacherId);

    @Query("SELECT s FROM Student s WHERE s.id = :classGroupId")
    List<Student> findByClassGroupId(@Param("classGroupId") Long classGroupId);
}