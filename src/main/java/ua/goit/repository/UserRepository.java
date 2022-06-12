package ua.goit.repository;

import org.springframework.data.repository.CrudRepository;
import ua.goit.model.dao.UserDao;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<UserDao, UUID> {

    Optional<UserDao> findByEmail(String email);
}
