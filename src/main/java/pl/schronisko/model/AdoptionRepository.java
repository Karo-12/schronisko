package pl.schronisko.model;

import org.springframework.data.repository.CrudRepository;

public interface AdoptionRepository extends CrudRepository<Adoption, AdoptionId> {
}