package group14.backend.lms.repository;

import group14.backend.lms.model.entity.Submission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ISubmissionRepository extends CrudRepository<Submission, Long> {
    List<Submission> findSubmissionsByAssignmentId(Long assignmentId);
    List<Submission> findSubmissionsByStudentId(Long studentId);
}
