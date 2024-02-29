package org.example.convent;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.io.IOException;
import java.io.FileWriter;


@Controller
@RequestMapping("/convent")
public class ConventController {
    private final ConventService conventService;

    public ConventController(ConventService conventService) {
        this.conventService = conventService;
    }

    @GetMapping("/main")
    public String showMainPage() {
        return "conventmain";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Convent> getAllConvents() {
        return conventService.getAllConvents();
    }

    @GetMapping("/add")
    public String showAddConventForm() {
        return "conventadd";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addConvent(@RequestBody String body) {
        String name, date;

        try {

            JSONObject obj = new JSONObject(body);
            name = obj.getString("name");
            date = obj.getString("date");

            String logname = name +".txt";

        FileWriter myWriter = new FileWriter(logname);
        myWriter.write("\n POTATO logger body:" + body);
            myWriter.write("\n POTATO logger name:" + name);
            myWriter.write("\n POTATO logger date:" + date);

            myWriter.close();
    } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }


        try {
            conventService.createNewConvent(name, date);
            return ResponseEntity.ok().body("{\"message\": \"Konwent został dodany pomyślnie\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("{\"error\": \"Wystąpił błąd podczas dodawania konwentu.\"}");
        }
    }
}










