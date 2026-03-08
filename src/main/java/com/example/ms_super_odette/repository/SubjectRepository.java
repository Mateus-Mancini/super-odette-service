package com.example.ms_super_odette.repository;

import com.example.ms_super_odette.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    boolean existsByName(String name);

    Optional<Subject> findByName(String name);

    @Query("""

            SELECT DISTINCT s
FROM Subject s
JOIN Schedule sc ON sc.subject.id = s.id
WHERE sc.classGroup.id = :classGroupId
""")
    List<Subject> findByClassGroupId(Long classGroupId);
}