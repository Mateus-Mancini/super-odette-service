package com.example.ms_super_odette.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResponse {
    private Long id;
    private String name;
    private String email;
    private LocalDate hiredAt;
    private List<SubjectSummary> subjects;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubjectSummary {
        private Long id;
        private String name;
    }
}