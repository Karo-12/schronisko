package pl.schronisko.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.schronisko.exception.UserNotFoundException;
import pl.schronisko.model.User;
import pl.schronisko.service.UserService;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> users = userService.listAll();
        model.addAttribute("listOfUsers", users);
        return "users";
    }
    @GetMapping("users/new")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "new_user_form";
    }
    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra){
        userService.save(user);
        ra.addFlashAttribute("message","The user has been saved successfully");
        return "redirect:/users";
    }
    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = userService.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: "+id+")");
            return "new_user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/users";
        }
    }
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            userService.delete(id);
            ra.addFlashAttribute("message", " The user ID: "+id+" has been deleted.");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/users";
    }
    @GetMapping("users/register")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "registration_form";
    }
    @PostMapping("/users/save_register")
    public String registerUserSave(User user, RedirectAttributes ra){
        user.setRole("USER");
        userService.save(user);
        ra.addFlashAttribute("message","The user has been saved successfully");
        return "redirect:/users";
    }


}
