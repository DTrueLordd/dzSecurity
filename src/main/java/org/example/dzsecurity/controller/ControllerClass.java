package org.example.dzsecurity.controller;

import org.example.dzsecurity.model.User;
import org.example.dzsecurity.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class ControllerClass {

    private final UserService userService;

    public ControllerClass(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/second")
    public String second() {
        return "second";
    }


    @PostMapping("/regSubmit")
    public String regHandle(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        if(userService.usernameIsOccupied(user.getUsername())) {
            redirectAttributes.addFlashAttribute("error", "Имя пользователя занято");
            return "redirect:/register";
        }
        userService.register(user);
        return "redirect:/";
    }

}
