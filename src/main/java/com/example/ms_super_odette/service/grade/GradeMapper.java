package com.example.ms_super_odette.service.grade;

import com.example.ms_super_odette.dto.response.GradeResponse;
import com.example.ms_super_odette.model.Grade;
import com.example.ms_super_odette.model.Subject;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Shared mapping logic: converts Grade rows into GradeResponses,
 * splitting by semester and computing the average.
 *
 * Semester rule: months 1–6 → first semester, months 7–12 → second semester.
 * When a semester has multiple entries, their average is used.
 * When a semester has no entries, its value is null (not yet attributed).
 */
@Component
public class GradeMapper {

    public List<GradeResponse> toStudentView(List<Grade> grades) {
        return grades.stream()
                .collect(Collectors.groupingBy(Grade::getSubject))
                .entrySet().stream()
                .map(entry -> buildResponse(entry.getKey(), null, null, entry.getValue()))
                .toList();
    }

    public GradeResponse toSingleSubjectResponse(Subject subject,
                                                 Long studentId, String studentName,
                                                 List<Grade> grades) {
        return buildResponse(subject, studentId, studentName, grades);
    }

    // --- internal ---

    private GradeResponse buildResponse(Subject subject,
                                        Long studentId, String studentName,
                                        List<Grade> grades) {
        Map<Boolean, List<Grade>> bySemester = grades.stream()
                .collect(Collectors.partitioningBy(this::isFirstSemester));

        BigDecimal first  = semesterAverage(bySemester.get(true));
        BigDecimal second = semesterAverage(bySemester.get(false));
        BigDecimal avg    = computeAverage(first, second);

        return new GradeResponse(
                subject.getId(), subject.getName(),
                first, second, avg,
                studentId, studentName
        );
    }

    private boolean isFirstSemester(Grade grade) {
        return grade.getDate().getMonthValue() <= 6;
    }

    private BigDecimal semesterAverage(List<Grade> grades) {
        if (grades == null || grades.isEmpty()) return null;
        BigDecimal sum = grades.stream()
                .map(Grade::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(BigDecimal.valueOf(grades.size()), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal computeAverage(BigDecimal first, BigDecimal second) {
        if (first == null && second == null) return null;
        if (first == null) return second;
        if (second == null) return first;
        return first.add(second).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP);
    }
}