package pl.schronisko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.schronisko.model.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {
}