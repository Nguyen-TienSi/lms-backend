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
        String fileName,
        String fileType,
        byte[] fileData,
        List<AnswerDto> answerDtoList,
        LocalDateTime submissionTime
) {
    public static SubmissionDto convertToDto(Submission submission) {
        return SubmissionDto.builder()
                .id(submission.getId())
                .studentId(submission.getStudent().getId())
                .assignmentId(submission.getAssignment().getId())
                .fileName(submission.getFileName())
                .fileType(submission.getFileType())
                .fileData(submission.getFileData())
                .answerDtoList(submission.getAnswers().stream()
                        .map(AnswerDto::convertToDto)
                        .collect(Collectors.toList()))
                .submissionTime(submission.getSubmissionTime())
                .build();
    }
}
