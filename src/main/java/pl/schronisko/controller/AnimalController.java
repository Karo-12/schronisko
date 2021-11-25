package pl.schronisko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.schronisko.exception.AnimalNotFoundException;
import pl.schronisko.model.Animal;
import pl.schronisko.model.AnimalId;
import pl.schronisko.service.AnimalService;

import java.util.List;

@Controller
public class AnimalController {

    @Autowired
    private AnimalService animalService;


    @GetMapping("/animals")
    public String showAnimalList(Model model){
        List<Animal> animals = animalService.listAll();
        model.addAttribute("listAnimals", animals);
        return "animals";
    }
    @GetMapping("/profile/{idAnimal}/{idUser}")
    public String showAnimalProfile(@PathVariable Integer idAnimal, @PathVariable Integer idUser, Model model, RedirectAttributes ra) {
        try {
            Animal animal = animalService.getAnimalById(new AnimalId(idAnimal,idUser));
            model.addAttribute("animalProfile", animal);
            return "profile";

        }catch(AnimalNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/animals";
        }
    }
}
