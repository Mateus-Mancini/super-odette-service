package com.example.ms_super_odette.schedule.repository;

import com.example.ms_super_odette.schedule.model.Schedule;
import com.example.superodette.classgroup.model.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByClassGroupIn(List<ClassGroup> classes);
    List<Schedule> findByTeacherId(Long teacherId);
}
