package pl.schronisko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.schronisko.model.Race;
import pl.schronisko.model.Type;
import pl.schronisko.service.RaceService;
import pl.schronisko.service.TypeService;

import java.util.List;

@Controller
public class RaceController {
    @Autowired
    RaceService raceService;
    @Autowired
    TypeService typeService;

    @GetMapping("/manage_animals/new_race")
    public String createNewTypeOfAnimal(Model model) {
        List<Type> types = typeService.listAll();
        model.addAttribute("race", new Race());
        model.addAttribute("types", types);
        return "new_race_form";
    }
    @PostMapping("/manage_animals/save_race")
    public String saveType(Race race, RedirectAttributes ra) {
        raceService.save(race);
        ra.addFlashAttribute("message", "Rasa zwierzecia zostal dodany prawidlowo");
        return "redirect:/manage_animals";
    }
}
