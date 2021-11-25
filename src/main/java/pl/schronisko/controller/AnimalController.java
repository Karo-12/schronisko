package pl.schronisko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.schronisko.model.Animal;
import pl.schronisko.service.AnimalService;

import java.util.List;

@Controller
public class AnimalController {

    @Autowired
    private AnimalService service;


    @GetMapping("/animals")
    public String showAnimalList(Model model){
        List<Animal> animals = service.listAll();
        model.addAttribute("listAnimals", animals);
        return "animals";
    }
}
