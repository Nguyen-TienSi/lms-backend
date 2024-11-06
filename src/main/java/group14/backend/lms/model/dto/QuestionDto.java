package group14.backend.lms.model.dto;

import group14.backend.lms.model.entity.Question;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record QuestionDto(
        Long id,
        String question,
        Long assignmentId,
        List<AnswerDto> answerDtoList
) {
    public static QuestionDto convertToDto(Question question) {
        return QuestionDto.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .assignmentId(question.getAssignment().getId())
                .answerDtoList(question.getAnswer().stream()
                        .map(AnswerDto::convertToDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
