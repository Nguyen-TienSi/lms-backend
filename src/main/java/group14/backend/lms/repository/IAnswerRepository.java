package group14.backend.lms.repository;

import group14.backend.lms.model.entity.Answer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IAnswerRepository extends CrudRepository<Answer, Long> {
    List<Answer> findAnswerByQuestionId(long questionId);
}
