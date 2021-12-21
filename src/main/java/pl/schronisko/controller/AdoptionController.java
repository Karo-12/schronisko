package pl.schronisko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.schronisko.exception.AnimalNotFoundException;
import pl.schronisko.exception.ReservationNotFoundException;
import pl.schronisko.model.Adoption;
import pl.schronisko.model.Animal;
import pl.schronisko.model.Reservation;
import pl.schronisko.service.AdoptionService;
import pl.schronisko.service.AnimalService;
import pl.schronisko.service.ReservationService;

import java.util.List;

@Controller
public class AdoptionController {
    @Autowired
    AdoptionService adoptionService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    AnimalService animalService;

    @GetMapping("/reservations/accept/{idReservation}/{idAnimal}/{idUser}")
    public String acceptReservation(RedirectAttributes ra, @PathVariable Integer idUser, @PathVariable Integer idAnimal, @PathVariable String idReservation) {
        try {
            Reservation reservation = reservationService.getReservationByUserId(idUser);
            adoptionService.saveAdoption(reservation);
            Animal animal = animalService.getAnimalById(idAnimal);
            animal.setStatus("adopted");
            animalService.save(animal);
        } catch (ReservationNotFoundException | AnimalNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        ra.addFlashAttribute("message", "Adopcja zostala wykonana prawidlowo");
        return "redirect:/reservations/delete/"+idReservation+"/"+idAnimal+"/"+idUser;
    }
    @GetMapping("/adoptions")
    public String showAdoptionList(Model model){
        List<Adoption> adoptions = adoptionService.listAll();
        model.addAttribute("listOfAdoptions", adoptions);
        return "adoptions";
    }
}
