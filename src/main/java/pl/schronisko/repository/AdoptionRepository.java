package pl.schronisko.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import pl.schronisko.model.Adoption;
import pl.schronisko.model.AdoptionId;


public interface AdoptionRepository extends CrudRepository<Adoption, AdoptionId> {
    Page<Adoption> findAll(Pageable pagable);
}