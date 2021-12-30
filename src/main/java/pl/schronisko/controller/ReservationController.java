package pl.schronisko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.schronisko.exception.AnimalNotAvailableException;
import pl.schronisko.exception.AnimalNotFoundException;
import pl.schronisko.exception.ReservationNotFoundException;
import pl.schronisko.exception.UserHasAnimalException;
import pl.schronisko.model.*;
import pl.schronisko.service.AnimalService;
import pl.schronisko.service.ReservationService;
import pl.schronisko.service.UserService;

import java.util.List;

@Controller
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UserService userService;
    @Autowired
    private AnimalService animalService;

    @GetMapping("/reservations")
    public String showReservationList(Model model){
        return findPaginatedReservations(1, model);
    }
    @GetMapping("/reservations/{reservationPageNo}")
    public String findPaginatedReservations(@PathVariable(value = "reservationPageNo") int reservationPageNo, Model model) {
        int pageSize = 5;
        Page<Reservation> page = reservationService.findPaginatedAll(reservationPageNo, pageSize);
        List <Reservation> listOfReservations= page.getContent();

        model.addAttribute("currentPage", reservationPageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listOfReservations", listOfReservations);
        return "reservations";
    }
    @GetMapping("reservations/new/{idAnimal}")
    public String addNewReservation(@PathVariable Integer idAnimal, Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("idAnimal", idAnimal);
        return "new_reservation_form";
    }
    @PostMapping("reservations/save/{idAnimal}")
    public String saveReservation(Reservation reservation, RedirectAttributes ra, @PathVariable Integer idAnimal) {
        try {
            reservationService.saveReservation(reservation, idAnimal);
        } catch(AnimalNotAvailableException | AnimalNotFoundException | UserHasAnimalException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        ra.addFlashAttribute("message","Rezerwacja zostala dodana");
        return "redirect:/animals";
    }
    @GetMapping("/reservations/delete/{idReservation}/{idAnimal}/{idUser}")
    public String deleteReservation(@PathVariable("idReservation") Integer idReservation, RedirectAttributes ra, @PathVariable Integer idAnimal, @PathVariable Integer idUser) {
        try {
            ReservationId id = new ReservationId(idAnimal, idReservation, idUser);
            reservationService.delete(id);
            Animal animal = animalService.getAnimalById(idAnimal);
            if(animal.getStatus().equals("reserved")) {
                animal.setStatus("available");
                animalService.save(animal);
            }
            ra.addFlashAttribute("message", "Rezerwacja została usunięta");
        } catch (ReservationNotFoundException | AnimalNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/reservations";
    }
    @GetMapping("/reservation")
    public String showReservation(Model model, RedirectAttributes ra) {
        try {
            MyUserDetails activeUser =  (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.findUserByEmail(activeUser.getUsername());
            Reservation reservation = reservationService.getReservationByUserId(user.getId());
            try {
                Animal animal = animalService.getAnimalById(reservation.getId().getIdAnimal());
                model.addAttribute("animal", animal);
            }catch(AnimalNotFoundException e) {
                ra.addFlashAttribute("message",e.getMessage());
            }
            model.addAttribute("reservation", reservation);
            return "reservation";
        }catch(ReservationNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/";
        }
    }
    @GetMapping("/reservation/cancel/{idReservation}/{idAnimal}/{idUser}")
    public String cancelReservation(@PathVariable("idReservation") Integer idReservation, RedirectAttributes ra, @PathVariable Integer idAnimal, @PathVariable Integer idUser) {
        try {
            ReservationId id = new ReservationId(idAnimal, idReservation, idUser);
            reservationService.delete(id);
            Animal animal = animalService.getAnimalById(idAnimal);
            animal.setStatus("available");
            animalService.save(animal);
            ra.addFlashAttribute("message", "Rezerwacja została anulowana");
        } catch (ReservationNotFoundException | AnimalNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/reservation";
        }
        return "redirect:/reservation";
    }

}
