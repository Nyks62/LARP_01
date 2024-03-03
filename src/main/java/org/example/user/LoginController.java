package org.example.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm() {
        return "redirect:/mainpage";
    }

    @GetMapping("/mainpage")
    public String main() {
        return "mainpage";
    }

    @GetMapping("/login/adminpanel")
    public String adminPanel() {
        return "adminpanel";
    }

    @GetMapping("/userpanelhome")
    public String userPanelHome() {
        return "userpanel/home";
    }
}

