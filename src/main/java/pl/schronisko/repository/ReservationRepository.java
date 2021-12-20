package pl.schronisko.repository;

import org.springframework.data.repository.CrudRepository;
import pl.schronisko.model.Reservation;
import pl.schronisko.model.ReservationId;

import java.util.Optional;

public interface ReservationRepository extends CrudRepository<Reservation, ReservationId> {
    Long countById(ReservationId id);

    @Override
    Optional<Reservation> findById(ReservationId reservationId);
}