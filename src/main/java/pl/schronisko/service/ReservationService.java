package pl.schronisko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.schronisko.exception.AnimalNotAvailableException;
import pl.schronisko.exception.AnimalNotFoundException;
import pl.schronisko.exception.ReservationNotFoundException;
import pl.schronisko.model.*;
import pl.schronisko.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AnimalService animalService;

    public List<Reservation> listAll() {
        return (List<Reservation>) reservationRepository.findAll();
    }
    public void saveReservation(Reservation reservation, Integer idAnimal) throws AnimalNotAvailableException, AnimalNotFoundException {
        MyUserDetails activeUser =  (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserByEmail(activeUser.getUsername());
        ReservationId id = new ReservationId();
        if(!animalService.isAnimalAdopted(idAnimal) && !animalService.isAnimalReserved(idAnimal)) {
            id.setIdAnimal(idAnimal);
            id.setIdUser(user.getId());
            id.setIdReservation(nextReservationId());
            reservation.setId(id);
            reservation.setDate(LocalDate.now());
            reservationRepository.save(reservation);
            Animal animal = animalService.getAnimalById(idAnimal);
            animal.setStatus("reserved");
            animalService.save(animal);
        }
        else throw new AnimalNotAvailableException("Animal is already reserved or adopted");
    }
    public Integer nextReservationId() {
        List<Reservation> reservations = listAll();
        Integer lastId = 0;
        if(reservations.size() == 0) lastId = 1;
        else {
            lastId = reservations.get(reservations.size() - 1).getId().getIdReservation();
            lastId += 1;
        }
        return lastId;
    }
    public void delete(ReservationId id) throws ReservationNotFoundException {
        Long count = reservationRepository.countById(id);
        if(count == null || count == 0) {
            throw new ReservationNotFoundException("Could not find any user with ID: "+id);
        }
        reservationRepository.deleteById(id);
    }
    public Reservation getReservationByUserId(Integer id) throws ReservationNotFoundException{
        List <Reservation> reservations = listAll();
        Reservation result = null;
        for (Reservation reservation : reservations) {
            if (Objects.equals(reservation.getId().getIdUser(), id)) result = reservation;
        }
        if( result == null) throw new ReservationNotFoundException("Reservation not found");
        else return result;
    }
}
