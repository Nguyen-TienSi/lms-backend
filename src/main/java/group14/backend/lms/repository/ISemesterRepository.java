package group14.backend.lms.repository;

import group14.backend.lms.model.entity.Semester;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ISemesterRepository extends CrudRepository<Semester, Long> {
    Optional<Semester> findByName(String name);
}
