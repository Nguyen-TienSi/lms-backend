package group14.backend.lms.repository;
import group14.backend.lms.model.entity.Class;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IClassRepository extends CrudRepository<Class, Long> {
    Optional<Class> findByName(String name);
}
