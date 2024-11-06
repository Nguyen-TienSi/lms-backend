package group14.backend.lms.model.dto;

import group14.backend.lms.model.entity.Assignment;
import group14.backend.lms.model.entity.Question;
import group14.backend.lms.model.entity.Submission;
import lombok.Builder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record AssignmentDto(
        Long id,
        String name,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Duration duration,
        String fileName,
        String fileType,
        byte[] file,
        Long courseId,
        List<Long> questionIds,
        List<Long> submissionIds
) {
    public static AssignmentDto convertToDto(Assignment assignment) {
        return AssignmentDto.builder()
                .id(assignment.getId())
                .name(assignment.getName())
                .description(assignment.getDescription())
                .startDate(assignment.getStartDate())
                .endDate(assignment.getEndDate())
                .duration(Duration.between(assignment.getStartDate(), assignment.getEndDate()))
                .fileName(assignment.getFileName())
                .fileType(assignment.getFileType())
                .file(assignment.getFile())
                .courseId(assignment.getCourse().getId())
                .questionIds(assignment.getQuestions().stream()
                        .map(Question::getId)
                        .toList())
                .submissionIds(assignment.getSubmissions().stream()
                        .map(Submission::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}
