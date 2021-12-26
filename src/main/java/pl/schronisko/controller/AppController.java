package pl.schronisko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    // Login form
    @RequestMapping("/login")
    public String login() {
        return "login";
    }


}
