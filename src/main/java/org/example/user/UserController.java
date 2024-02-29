package org.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Strona główna, wybór panelu
    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }

    // Panel logowania dla administratora
    @GetMapping("/adminlogin")
    public String adminLoginPage() {
        return "adminlogin";
    }

    // Obsługa logowania administratora
    @PostMapping("/adminlogin")
    public String adminLogin(@RequestParam String email, @RequestParam String password, RedirectAttributes redirectAttributes) {
        // Sprawdzanie hasła admina
        if (email.equals("admin@example.com") && password.equals("adminpassword")) {
            // Pomyślne logowanie
            return "redirect:/adminpanel";
        } else {
            // Błędne dane logowania
            redirectAttributes.addFlashAttribute("error", "Nieprawidłowy email lub hasło");
            return "redirect:/adminlogin";
        }
    }

    // Panel logowania dla użytkownika
    @GetMapping("/userlogin")
    public String userLoginPage() {
        return "userlogin";
    }

    // Obsługa logowania użytkownika
    @PostMapping("/userlogin")
    public String userLogin(@RequestParam String email, @RequestParam String password, RedirectAttributes redirectAttributes) {
        // Sprawdzanie danych logowania użytkownika w bazie danych
        User user = userService.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            // Pomyślne logowanie
            return "redirect:/userpanel";
        } else {
            // Błędne dane logowania
            redirectAttributes.addFlashAttribute("error", "Nieprawidłowy email lub hasło");
            return "redirect:/userlogin";
        }
    }

    // Strona rejestracji dla użytkownika
    @GetMapping("/userregister")
    public String userRegisterPage() {
        return "userregister";
    }

    // Obsługa rejestracji użytkownika
    @PostMapping("/userregister")
    public String userRegister(@RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword, RedirectAttributes redirectAttributes) {
        // Sprawdzanie czy hasła się zgadzają i czy mają co najmniej 5 znaków
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Hasła się nie zgadzają");
            return "redirect:/userregister";
        }
        if (password.length() < 5) {
            redirectAttributes.addFlashAttribute("error", "Hasło musi mieć co najmniej 5 znaków");
            return "redirect:/userregister";
        }

        return "redirect:/userlogin"; // Po rejestracji przekierowujemy do panelu logowania użytkownika
    }
}