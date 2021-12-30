package pl.schronisko.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import pl.schronisko.model.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends CrudRepository<Animal, Integer> {
    Optional<Animal> findById(Integer id);
    Long countById(Integer id);
    List<Animal> findByStatusIs(String status);
    Page <Animal> findAll(Pageable pageable);
    Page <Animal> findByStatusIs(String status, Pageable pageable);
}