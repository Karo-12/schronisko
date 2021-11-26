package pl.schronisko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.schronisko.model.User;
import pl.schronisko.service.UserService;

import java.util.List;

@Controller
public class ManageUsersController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> users = userService.listAll();
        model.addAttribute("listOfUsers", users);
        return "users";
    }

}
