package group14.backend.lms.repository;

import group14.backend.lms.model.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IRoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByAuthority(String authority);
}
