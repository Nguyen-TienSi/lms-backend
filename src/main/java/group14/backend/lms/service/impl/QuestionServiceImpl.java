package group14.backend.lms.service.impl;

import group14.backend.lms.model.dto.AnswerDto;
import group14.backend.lms.model.entity.Answer;
import group14.backend.lms.model.entity.Question;
import group14.backend.lms.model.dto.QuestionDto;
import group14.backend.lms.repository.IAnswerRepository;
import group14.backend.lms.repository.IAssignmentRepository;
import group14.backend.lms.repository.IQuestionRepository;
import group14.backend.lms.service.IQuestionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionServiceImpl implements IQuestionService {
    private final IQuestionRepository questionRepository;
    private final IAssignmentRepository assignmentRepository;
    private final IAnswerRepository answerRepository;

    @Override
    public List<QuestionDto> getQuestionsByAssignmentId(long assignmentId) {
        return questionRepository.findQuestionsByAssignmentId(assignmentId).stream()
                .map(QuestionDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public QuestionDto createQuestion(QuestionDto questionDto) {
        Question question = new Question();
        question.setQuestion(questionDto.question());
        question.setAssignment(assignmentRepository.findById(questionDto.assignmentId()).orElseThrow());

        // Create answers and set the questionId
        List<Answer> answers = questionDto.answerDtoList().stream()
                .map(answerDto -> {
                    Answer answer = new Answer();
                    answer.setAnswer(answerDto.answer());
                    //Crucial change: set the question ID here
                    answer.setQuestion(question); //This sets the association to the question
                    answer.setIsCorrect(answerDto.isCorrect());
                    return answer;
                })
                .collect(Collectors.toList());

        question.setAnswer(answers);

        return QuestionDto.convertToDto(questionRepository.save(question));
    }

    @Override
    public QuestionDto updateQuestion(long questionId, QuestionDto questionDto) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));

        // TODO update question
        return QuestionDto.convertToDto(questionRepository.save(question));
    }

    @Override
    public void deleteQuestion(long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow();
        questionRepository.delete(question);
    }
}
