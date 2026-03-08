package com.example.ms_super_odette.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ObservationResponse {
    private Long id;
    private String text;
    private LocalDateTime registeredAt;

    private Long studentId;
    private String studentName;
}