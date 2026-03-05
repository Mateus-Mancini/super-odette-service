package com.example.ms_super_odette.classgroup.repository;

import com.example.ms_super_odette.classgroup.model.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassGroupRepository extends JpaRepository<ClassGroup, Long> {}