package org.example.user;

import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.example.tag.Tag;
import org.example.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final TagService tagService;


    @Autowired
    public UserController(UserService userService, TagService tagService) {
        this.userService = userService;
        this.tagService = tagService;
    }

    @GetMapping("/userpanel")
    public String userPanel() {
        return "userpanel";
    }

    @GetMapping("/userpanel/home")
    public String userPanelHome() {
        return "userpanelhome";
    }

    @GetMapping("/usertags")
    public String chooseTags(Model model) {
        List<Tag> tags = tagService.getAllTag();
        model.addAttribute("tags", tags);
        return "usertags";
    }

    @GetMapping("/userlogin")
    public String userLogin() {
        return "userlogin";
    }

    @PostMapping("/userlogin")
    public String login(@ModelAttribute("user") @Valid User user,
                        BindingResult result,
                        RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "userlogin";
        }

        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser == null || !existingUser.getPassword().equals(user.getPassword())) {
            redirectAttributes.addFlashAttribute("error", "Invalid email or password");
            return "redirect:/userlogin";
        }

        return "redirect:/usertags";
    }

    @GetMapping("/userregister")
    public String showRegistrationForm(@ModelAttribute("user") User user) {
        return "userregister";
    }

    @PostMapping("/userregister")
    public String registerUser(@ModelAttribute("user") @Valid User user,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "userregister";
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("error", "Passwords do not match");
            return "redirect:/userregister";
        }

        if (userService.registerUser(user)) {
            return "redirect:/userpanel";
        } else {
            redirectAttributes.addFlashAttribute("error", "Email address already registered");
            return "redirect:/userregister";
        }
    }
}