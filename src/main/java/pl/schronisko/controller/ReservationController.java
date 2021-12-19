package pl.schronisko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.schronisko.model.*;
import pl.schronisko.service.ReservationService;
import pl.schronisko.service.UserService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UserService userService;
    @GetMapping("/reservations")
    public String showReservationList(Model model){
        List<Reservation> reservations = reservationService.listAll();
        model.addAttribute("listOfReservations", reservations);
        return "reservations";
    }
    @GetMapping("reservations/new/{idAnimal}")
    public String addNewReservation(@PathVariable Integer idAnimal, Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("idAnimal", idAnimal);
        return "new_reservation_form";
    }
    @PostMapping("reservations/save/{idAnimal}")
    public String saveAnimal(Reservation reservation, RedirectAttributes ra, @PathVariable Integer idAnimal) {
        MyUserDetails activeUser =  (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserByEmail(activeUser.getUsername());
        ReservationId id = new ReservationId();
        id.setIdAnimal(idAnimal);
        id.setIdUser(user.getId());
        id.setIdReservation(reservationService.nextReservationId());
        reservation.setId(id);
        reservation.setDate(LocalDate.now());
        reservationService.saveReservation(reservation);
        ra.addFlashAttribute("message","Rezerwacja zostala dodana");
        return "redirect:/animals";
    }
}
