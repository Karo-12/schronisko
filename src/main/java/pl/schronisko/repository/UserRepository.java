package pl.schronisko.repository;

import org.springframework.data.repository.CrudRepository;
import pl.schronisko.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}