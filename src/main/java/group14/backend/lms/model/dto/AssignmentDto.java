package group14.backend.lms.model.dto;

import group14.backend.lms.model.entity.Assignment;
import group14.backend.lms.model.entity.Question;
import group14.backend.lms.model.entity.Submission;
import lombok.Builder;

import java.time.Duration;
import java.time.LocalDateTime;
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
//        byte[] file,
        String filePath,
        Long courseId,
        List<QuestionDto> questions,
        List<Long> submissionIds
) {
    public static AssignmentDto convertToDto(Assignment assignment) {
        return AssignmentDto.builder()
                .id(assignment.getId())
                .name(assignment.getName())
                .description(assignment.getDescription())
                .startDate(assignment.getStartDate())
                .endDate(assignment.getEndDate())
                .duration(assignment.getDuration())
                .filePath(assignment.getFilePath())
                .courseId(assignment.getCourse().getId())
                .questions(assignment.getQuestions().stream()
                        .map(QuestionDto::convertToDto)
                        .collect(Collectors.toList()))
                .submissionIds(assignment.getSubmissions().stream()
                        .map(Submission::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}
