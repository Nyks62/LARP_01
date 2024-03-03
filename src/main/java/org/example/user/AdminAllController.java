package org.example.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminAllController {

    @GetMapping("/adminall")
    public String adminAll() {
        return "adminall";
    }
}