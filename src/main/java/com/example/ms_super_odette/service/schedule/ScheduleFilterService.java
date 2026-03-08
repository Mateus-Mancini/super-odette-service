package com.example.ms_super_odette.service.schedule;

import com.example.ms_super_odette.model.Schedule;
import com.example.ms_super_odette.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleFilterService {

    private final ScheduleRepository scheduleRepo;

    public List<Schedule> getFiltered(Long teacherId, Long classGroupId) {
        if (teacherId != null && classGroupId != null) {
            return scheduleRepo.findByTeacherIdAndClassGroupId(teacherId, classGroupId);
        }
        if (teacherId != null) {
            return scheduleRepo.findByTeacherId(teacherId);
        }
        return scheduleRepo.findByClassGroupId(classGroupId);
    }
}