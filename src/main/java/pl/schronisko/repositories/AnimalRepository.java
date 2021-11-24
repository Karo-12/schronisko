package pl.schronisko.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.schronisko.model.Animal;
import pl.schronisko.model.AnimalId;

public interface AnimalRepository extends CrudRepository<Animal, AnimalId> {
}