package pl.schronisko.repository;

import org.springframework.data.repository.CrudRepository;
import pl.schronisko.model.Race;

public interface RaceRepository extends CrudRepository<Race, Integer> {
    Race findByName(String name);
}