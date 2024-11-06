package group14.backend.lms.repository;

import group14.backend.lms.model.entity.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherRepository extends CrudRepository<Teacher, Long> {
}
