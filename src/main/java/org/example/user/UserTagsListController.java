package org.example.user;

import org.example.convent.Selected;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserTagsListController {

    private List<String> selectedTags = new ArrayList<>();

    @GetMapping("/usertagslist")
    public String getUserTagsListPage(Model model) {
        model.addAttribute("selectedTags", selectedTags);
        return "usertagslist";
    }

    @PostMapping("/usertagslist")
    public String processSelectedTags(@RequestParam(value = "tags", required = false) List<String> tags) {
        if (tags != null) {
            selectedTags = tags;
        }
        return "redirect:/usertagslist";
    }

    public List<String> getSelectedTags() {
        return selectedTags;
    }
}