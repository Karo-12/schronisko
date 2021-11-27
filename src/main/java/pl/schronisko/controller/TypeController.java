package pl.schronisko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.schronisko.model.Type;
import pl.schronisko.service.TypeService;

@Controller
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/manage_animals/new_type")
    public String createNewTypeOfAnimal(Model model) {
        model.addAttribute("typ", new Type());
        return "new_type_form";
    }
    @PostMapping("/manage_animals/save_type")
    public String saveType(Type type, RedirectAttributes ra) {
        typeService.save(type);
        ra.addFlashAttribute("message","Typ zwierzecia zostal dodany prawidlowo");
        return "redirect:/manage_animals";
    }
}
