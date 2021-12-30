package pl.schronisko.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.schronisko.exception.EmailAlreadyInDatabaseException;
import pl.schronisko.exception.UserHasAnimalException;
import pl.schronisko.exception.UserNotFoundException;
import pl.schronisko.model.Animal;
import pl.schronisko.model.User;
import pl.schronisko.service.UserService;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/users")
    public String showUserList(Model model) {
        return findPaginatedUsers(1, model);
    }
    @GetMapping("/users/{usersPageNo}")
    public String findPaginatedUsers(@PathVariable(value = "usersPageNo") int usersPageNo, Model model) {
        int pageSize = 10;
        Page<User> page = userService.findPaginatedAll(usersPageNo, pageSize);
        List <User> listOfUsers= page.getContent();

        model.addAttribute("currentPage", usersPageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listOfUsers", listOfUsers);
        return "users";
    }
    @GetMapping("users/new")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "new_user_form";
    }
    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra) {
        try {
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userService.save(user);
        }catch(EmailAlreadyInDatabaseException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/users/new";
        }
        ra.addFlashAttribute("message","The user has been saved successfully");
        return "redirect:/users";
    }
    @PostMapping("/users/edit_save")
    public String saveEditUser(User user, RedirectAttributes ra) {
        try {
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userService.saveEdit(user);
        }catch(EmailAlreadyInDatabaseException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/users/edit/" + user.getId();
        }
        ra.addFlashAttribute("message","The user has been saved successfully");
        return "redirect:/users";
    }
    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = userService.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: "+id+")");
            return "edit_user_form";
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
        } catch (UserNotFoundException | UserHasAnimalException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/users";
        }
        return "redirect:/users";
    }
    @GetMapping("users/register")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }
    @PostMapping("/users/save_register")
    public String registerUserSave(User user, RedirectAttributes ra){
        try {
            user.setRole("ROLE_USER");
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userService.register(user);
        }catch(EmailAlreadyInDatabaseException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/users/register";
        }
        ra.addFlashAttribute("message","The user has been saved successfully");
        return "redirect:/";
    }


}
