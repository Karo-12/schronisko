package pl.schronisko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.schronisko.exception.AnimalNotFoundException;
import pl.schronisko.exception.UserNotFoundException;
import pl.schronisko.model.Animal;
import pl.schronisko.model.Race;
import pl.schronisko.model.User;
import pl.schronisko.service.AnimalService;
import pl.schronisko.service.RaceService;
import pl.schronisko.service.UserService;

import java.util.List;

@Controller
public class AnimalController {

    @Autowired
    private AnimalService animalService;
    @Autowired
    private RaceService raceService;
    @Autowired
    private UserService userService;


    @GetMapping("/animals")
    public String showAnimalList(Model model){
        return findPaginatedAnimals(1, model);
    }
    @GetMapping("/animals/{animalsPageNo}")
    public String findPaginatedAnimals(@PathVariable(value = "animalsPageNo") int animalsPageNo, Model model) {
        int pageSize = 4;

        Page< Animal > page = animalService.findPaginatedByStatus(animalsPageNo, pageSize);
        List <Animal> listAnimals = page.getContent();

        model.addAttribute("currentPage", animalsPageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listAnimals", listAnimals);
        return "animals";
    }

    @GetMapping("/profile/{id}")
    public String showAnimalProfile(@PathVariable String id, Model model, RedirectAttributes ra) {
        try {
            Animal animal = animalService.getAnimalById(Integer.parseInt(id));
            model.addAttribute("animalProfile", animal);
            return "profile";

        }catch(AnimalNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/animals";
        }
    }
    @GetMapping("/manage_animals")
    public String showAnimalsListManage(Model model) {
        return findPaginatedManageAnimals(1, model);
    }
    @GetMapping("/manage_animals/{animalsPageNo}")
    public String findPaginatedManageAnimals(@PathVariable(value = "animalsPageNo") int animalsPageNo, Model model) {
        int pageSize = 10;
        Page< Animal > page = animalService.findPaginatedAll(animalsPageNo, pageSize);
        List <Animal> listAnimals = page.getContent();

        model.addAttribute("currentPage", animalsPageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listAnimals", listAnimals);
        return "manage_animals";
    }
    @GetMapping("manage_animals/new")
    public String addNewUser(Model model) {
        List <Race> races = raceService.listAll();
        List <User> users = userService.listEmployees();
        model.addAttribute("animal", new Animal());
        model.addAttribute("races", races);
        model.addAttribute("users", users);
        return "new_animal_form";
    }
    @PostMapping("/manage_animals/save")
    public String saveAnimal(Animal animal, RedirectAttributes ra, @RequestParam("type") String type, @RequestParam("race") String race) {
        Race r = raceService.getRace(type,race);
        animal.setIdRace(r);
        animalService.save(animal);
        ra.addFlashAttribute("message","Użytkownik został dodany");
        return "redirect:/manage_animals";
    }
    @GetMapping("/manage_animals/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Animal animal = animalService.getAnimalById(id);
            List <Race> races = raceService.listAll();
            List <User> users = userService.listEmployees();
            model.addAttribute("animal", animal);
            model.addAttribute("races", races);
            model.addAttribute("users", users);
            return "new_animal_form";
        } catch (AnimalNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/users";
        }
    }
    @GetMapping("/manage_animals/delete/{id}")
    public String deleteAnimal(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            animalService.delete(id);
            ra.addFlashAttribute("message", "Zwierzę zostało usunięte");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/manage_animals";
    }

}
