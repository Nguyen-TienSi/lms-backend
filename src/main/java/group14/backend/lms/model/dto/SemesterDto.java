package group14.backend.lms.model.dto;

import group14.backend.lms.model.entity.Semester;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record SemesterDto(
        long id,
        String name,
        LocalDate startDate,
        LocalDate endDate
) {
    public static SemesterDto convertToDto(Semester semester) {
        return SemesterDto.builder()
                .id(semester.getId())
                .name(semester.getName())
                .startDate(semester.getStartDate())
                .endDate(semester.getEndDate())
                .build();
    }
}
