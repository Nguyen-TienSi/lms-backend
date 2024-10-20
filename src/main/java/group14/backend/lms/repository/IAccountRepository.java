package group14.backend.lms.repository;

import group14.backend.lms.model.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IAccountRepository extends CrudRepository<Account, Long> {
}
