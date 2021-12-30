package pl.schronisko.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import pl.schronisko.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    Long countById(Integer id);
    List<User> findByRoleIs(String role);
    User findByEmail(String email);
    Page<User> findAll(Pageable pageable);
}