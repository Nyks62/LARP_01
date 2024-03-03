package org.example.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Tag>> getAllTag() {
        List<Tag> tags = tagService.getAllTag();
        if (tags.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/add")
    public String showAddTagForm(Model model) {
        model.addAttribute("tag", new Tag());
        return "addtagform";
    }

    @PostMapping("/add")
    public String addTag(@ModelAttribute Tag tag, RedirectAttributes redirectAttributes) {
        tagService.addTag(tag);
        redirectAttributes.addFlashAttribute("message", "Tag added successfully");
        return "redirect:/tag/main";
    }

    @GetMapping("/main")
    public String getMainPage(Model model) {
        List<Tag> tags = tagService.getAllTag();
        model.addAttribute("tags", tags);
        return "tagmain";
    }
}