package com.example.ms_super_odette.service.schedule;

import com.example.ms_super_odette.dto.response.ScheduleResponse;
import com.example.ms_super_odette.model.Schedule;
import com.example.ms_super_odette.model.SessionData;
import lombok.RequiredArgsConstructor;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleContext {

    private final List<ScheduleStrategy> strategies;
    private final ScheduleMapper mapper;
    private final ScheduleFilterService filterService;

    @RegisterReflectionForBinding({ ScheduleResponse.class, Schedule.class })
    public List<ScheduleResponse> getSchedule(SessionData user, Long teacherId, Long classGroupId) {
        if (teacherId != null || classGroupId != null) {
            return mapper.toResponseList(
                    filterService.getFiltered(teacherId, classGroupId)
            );
        }

        List<Schedule> schedules = strategies.stream()
                .filter(s -> s.supports(user))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No schedule strategy found for user role."))
                .getSchedule(user);

        return mapper.toResponseList(schedules);
    }
}