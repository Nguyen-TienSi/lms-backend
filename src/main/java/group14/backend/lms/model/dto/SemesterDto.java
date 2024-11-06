package group14.backend.lms.model.dto;

import group14.backend.lms.model.entity.Semester;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SemesterDto(
        long id,
        String name,
        LocalDateTime startDate,
        LocalDateTime endDate
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
