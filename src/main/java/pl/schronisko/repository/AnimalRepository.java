package pl.schronisko.repository;

import org.springframework.data.repository.CrudRepository;
import pl.schronisko.model.Animal;

import java.util.Optional;

public interface AnimalRepository extends CrudRepository<Animal, Integer> {
    Optional<Animal> findById(Integer id);


}