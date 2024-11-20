package group14.backend.lms.repository;

import group14.backend.lms.model.entity.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IAnswerRepository extends CrudRepository<Answer, Long> {
    @Query(value = "SELECT a.* FROM Answer a JOIN Question q ON a.question_fk = q.id " +
            "JOIN Assignment ass ON q.assignment_fk = ass.id WHERE ass.id = ?1", nativeQuery = true)
    List<Answer> findAnswersByAssignmentId(long assignmentId);
}
