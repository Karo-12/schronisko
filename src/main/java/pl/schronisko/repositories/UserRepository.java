package pl.schronisko.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.schronisko.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}