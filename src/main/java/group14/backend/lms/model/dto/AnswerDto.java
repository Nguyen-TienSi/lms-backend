package group14.backend.lms.model.dto;

import group14.backend.lms.model.entity.Answer;
import lombok.Builder;

@Builder
public record AnswerDto(
        Long id,
        String answer,
        Long questionId,
        Boolean isCorrect
) {
    public static AnswerDto convertToDto(Answer answer) {
        return AnswerDto.builder()
                .id(answer.getId())
                .answer(answer.getAnswer())
                .questionId(answer.getQuestion().getId())
                .isCorrect(answer.getIsCorrect())
                .build();
    }
}
