package pl.schronisko.repository;

import org.springframework.data.repository.CrudRepository;
import pl.schronisko.model.Reservation;
import pl.schronisko.model.ReservationId;

public interface ReservationRepository extends CrudRepository<Reservation, ReservationId> {
}