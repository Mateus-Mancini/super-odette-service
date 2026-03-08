package com.example.ms_super_odette.repository;

import com.example.ms_super_odette.model.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretaryRepository extends JpaRepository<Secretary, Long> {
}