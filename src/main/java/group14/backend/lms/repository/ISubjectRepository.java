package group14.backend.lms.repository;

import group14.backend.lms.model.entity.Subject;
import org.springframework.data.repository.CrudRepository;

public interface ISubjectRepository extends CrudRepository<Subject, Long> {
}
