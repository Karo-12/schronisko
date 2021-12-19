package pl.schronisko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.schronisko.model.Reservation;
import pl.schronisko.repository.ReservationRepository;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> listAll() {
        return (List<Reservation>) reservationRepository.findAll();
    }
    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }
    public Integer nextReservationId() {
        List<Reservation> reservations = listAll();
        Integer lastId = reservations.get(reservations.size()-1).getId().getIdReservation();
        lastId +=1;
        return lastId;
    }
}
