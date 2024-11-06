package group14.backend.lms.repository;

import group14.backend.lms.model.entity.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IQuestionRepository extends CrudRepository<Question, Long> {
    List<Question> findQuestionsByAssignmentId(long assignmentId);
}
