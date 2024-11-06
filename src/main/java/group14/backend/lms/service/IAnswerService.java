package group14.backend.lms.service;

import group14.backend.lms.model.dto.AnswerDto;

import java.util.List;

public interface IAnswerService {
    List<AnswerDto> getAnswerByQuestionId(int questionId);
    AnswerDto updateAnswer(long id, AnswerDto answerDto);
    void deleteAnswer(long id);
}