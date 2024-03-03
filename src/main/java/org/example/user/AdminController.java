package org.example.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {


    private static final String ADMIN_EMAIL = "mruczykmaria@gmail.com";
    private static final String ADMIN_PASSWORD = "D00psko";

    @GetMapping("/adminpanel")
    public String adminPanel() {
        return "adminpanel";
    }

    @GetMapping("/adminpanel/dashboard")
    public String adminDashboard() {
        return "admindashboard";
    }

    @PostMapping("/adminlogin")
    public String adminLogin(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             RedirectAttributes redirectAttributes) {
        // Sprawdzenie poprawności loginu i hasła
        if (email.equals(ADMIN_EMAIL) && password.equals(ADMIN_PASSWORD)) {
            return "redirect:/adminall";
        } else {
            redirectAttributes.addFlashAttribute("error", "Hasło i/lub użytkownik niepoprawne");
            return "redirect:/adminpanel";
        }
    }
}