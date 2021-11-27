package pl.schronisko.repository;

import org.springframework.data.repository.CrudRepository;
import pl.schronisko.model.Type;

public interface TypeRepository extends CrudRepository<Type, Integer> {
}