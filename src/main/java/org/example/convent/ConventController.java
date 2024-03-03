package org.example.convent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/convent")
public class ConventController {

    private final ConventService conventService;

    private final List<String> predefinedTags = Arrays.asList("postapo", "fantasy", "battlelarp", "high-larpówek", "cyberpunk", "reko", "steampunk", "chamber", "gra miejska");

    @Autowired
    public ConventController(ConventService conventService) {
        this.conventService = conventService;
    }

    @GetMapping("/main")
    public String showMainPage(Model model) {
        List<Convent> convents = conventService.getAllConvents();
        model.addAttribute("convents", convents);
        return "conventmain";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Convent> getAllConvents() {
        return conventService.getAllConvents();
    }

    @GetMapping("/add")
    public String showAddConventForm(Model model) {
        model.addAttribute("predefinedTags", predefinedTags);
        return "conventadd";
    }

    @PostMapping("/add")
    public String addConvent(@RequestParam String name, @RequestParam String date, @RequestParam String tag) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate parsedDate = LocalDate.parse(date, formatter);
            conventService.createNewConvent(name, parsedDate.toString(), tag);
            return "redirect:/convent/main";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

       /*     // Wyślij e-mail z informacjami o wybranych konwentach
            sendEmail(chosenConvents);

            return ResponseEntity.ok().body("{\"message\": \"Konwent został dodany pomyślnie\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("{\"error\": \"Wystąpił błąd podczas dodawania konwentu.\"}");
        }
    }
} */

   /* private void sendEmail(List<Chosen> chosenConvents) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo("recipient@example.com"); // Adres e-mail odbiorcy
            helper.setSubject("Nowe konwenty!");

            StringBuilder sb = new StringBuilder();
            sb.append("Hej! Oto nowe konwenty:\n");

            for (Chosen chosen : chosenConvents) {
                sb.append("Nazwa: ").append(chosen.getName())
                        .append(", Data: ").append(chosen.getDate())
                        .append(", Tag: ").append(chosen.getTag()).append("\n");
            }

            helper.setText(sb.toString());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }
} */









