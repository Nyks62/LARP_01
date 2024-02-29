package org.example.tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Tag>> getAllTag(){
        List<Tag> tag = tagService.getAllTag();
        if(tag.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tag);
    }

    @PostMapping("/add/")
    public ResponseEntity<Tag> addTag(@RequestBody Tag tag){
        try {
            Tag addedTag = tagService.addTag(tag);
            return ResponseEntity.ok(addedTag);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/main")
    public String getMainPage(Model model) {
        List<Tag> tag = tagService.getAllTag();
        model.addAttribute("tag", tag);
        return "tagmain";
    }
}




