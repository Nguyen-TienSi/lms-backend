package group14.backend.lms.model.dto;

import group14.backend.lms.model.entity.Answer;
import group14.backend.lms.model.entity.Submission;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record SubmissionDto(
        Long id,
        Long studentId,
        Long assignmentId,
        byte[] fileData,
        List<Long> answerIds,
        LocalDateTime submissionTime
) {
    public static SubmissionDto convertToDto(Submission submission) {
        return SubmissionDto.builder()
                .id(submission.getId())
                .studentId(submission.getStudent().getId())
                .assignmentId(submission.getAssignment().getId())
                .fileData(submission.getFileData())
                .answerIds(submission.getAnswers().stream()
                        .map(Answer::getId)
                        .collect(Collectors.toList()))
                .submissionTime(submission.getSubmissionTime())
                .build();
    }
}
