package group14.backend.lms.repository;

import group14.backend.lms.model.entity.Material;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IMaterialRepository extends CrudRepository<Material, Long> {
    List<Material> findAllByCourseId(long courseId);
}
