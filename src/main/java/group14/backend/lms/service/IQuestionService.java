package group14.backend.lms.service;

import group14.backend.lms.model.dto.QuestionDto;

import java.util.List;

public interface IQuestionService {
    List<QuestionDto> getQuestionsByAssignmentId(long assignmentId);
    QuestionDto createQuestion(QuestionDto questionDto);
    QuestionDto updateQuestion(long questionId, QuestionDto questionDto);
    void deleteQuestion(long questionId);
}
