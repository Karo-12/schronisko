package pl.schronisko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.schronisko.exception.ReservationNotFoundException;
import pl.schronisko.exception.UserNotFoundException;
import pl.schronisko.model.Reservation;
import pl.schronisko.model.ReservationId;
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
    public void delete(ReservationId id) throws ReservationNotFoundException {
        Long count = reservationRepository.countById(id);
        if(count == null || count == 0) {
            throw new ReservationNotFoundException("Could not find any user with ID: "+id);
        }
        reservationRepository.deleteById(id);
    }
}