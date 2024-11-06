package group14.backend.lms.repository;

import group14.backend.lms.model.entity.Assignment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IAssignmentRepository extends CrudRepository<Assignment, Long> {
    List<Assignment> findAssignmentsByCourseId(long courseId);
}
