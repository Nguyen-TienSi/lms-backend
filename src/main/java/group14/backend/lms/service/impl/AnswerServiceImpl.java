package group14.backend.lms.service.impl;

import group14.backend.lms.model.entity.Answer;
import group14.backend.lms.model.entity.Question;
import group14.backend.lms.model.dto.AnswerDto;
import group14.backend.lms.repository.IAnswerRepository;
import group14.backend.lms.repository.IQuestionRepository;
import group14.backend.lms.service.IAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements IAnswerService {
    private final IAnswerRepository answerRepository;
    private final IQuestionRepository questionRepository;

    @Override
    public List<AnswerDto> getAnswerByQuestionId(int questionId) {
        List<Answer> answerList = answerRepository.findAnswerByQuestionId(questionId);
        return answerList.stream()
                .map(AnswerDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnswerDto updateAnswer(long id, AnswerDto answerDto) {
        Answer answer = answerRepository.findById(id).orElseThrow();
        Question question = questionRepository.findById(answerDto.questionId()).orElseThrow();

        answer.setAnswer(answerDto.answer());
        answer.setQuestion(question);

        return AnswerDto.convertToDto(answerRepository.save(answer));
    }

    @Override
    public void deleteAnswer(long id) {
        answerRepository.findById(id).ifPresent(answerRepository::delete);
    }
}
