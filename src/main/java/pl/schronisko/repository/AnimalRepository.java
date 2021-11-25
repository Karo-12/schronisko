package pl.schronisko.repository;

import org.springframework.data.repository.CrudRepository;
import pl.schronisko.model.Animal;
import pl.schronisko.model.AnimalId;

import java.util.Optional;

public interface AnimalRepository extends CrudRepository<Animal, AnimalId> {
    Optional<Animal> findById(AnimalId id);


}