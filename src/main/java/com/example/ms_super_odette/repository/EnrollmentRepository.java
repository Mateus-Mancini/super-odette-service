package com.example.ms_super_odette.repository;

import com.example.ms_super_odette.enums.EnrollmentStatus;
import com.example.ms_super_odette.model.Enrollment;
import com.example.ms_super_odette.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @Query("SELECT e FROM Enrollment e WHERE e.student.id = :studentId")
    List<Enrollment> findByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT e FROM Enrollment e WHERE e.status = :status")
    List<Enrollment> findByStatus(@Param("status") EnrollmentStatus status);

    @Query("SELECT e FROM Enrollment e WHERE e.classGroup.id = :classGroupId")
    List<Enrollment> findByClassGroupId(@Param("classGroupId") Long classGroupId);

    @Query("SELECT e FROM Enrollment e WHERE e.student.id = :studentId AND e.classGroup.id = :classGroupId")
    Optional<Enrollment> findByStudentIdAndClassGroupId(@Param("studentId") Long studentId,
                                                        @Param("classGroupId") Long classGroupId);

    @Query("SELECT COUNT(e) > 0 FROM Enrollment e WHERE e.student.id = :studentId AND e.status = 'PENDING'")
    boolean existsPendingByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT COUNT(e) > 0 FROM Enrollment e WHERE e.student.id = :studentId AND e.status = :status")
    boolean existsByStudentIdAndStatus(@Param("studentId") Long studentId,
                                       @Param("status") EnrollmentStatus status);

    @Query("SELECT e.student FROM Enrollment e WHERE e.classGroup.id = :classGroupId AND e.status = 'ENROLLED'")
    List<Student> findStudentsByClassGroupId(@Param("classGroupId") Long classGroupId);
}