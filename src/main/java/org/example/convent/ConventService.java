package org.example.convent;

import org.example.convent.Convent;
import org.example.convent.ConventRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class ConventService {
    @Autowired
    private ConventRepository conventRepository;

    public void createNewConvent(String name, String dateString, String tag) {

        // Sprawdzenie, czy dane wejściowe nie są puste
        if (name == null || dateString == null || tag == null || name.isEmpty() || dateString.isEmpty() || tag.isEmpty()) {
            throw new IllegalArgumentException("Nazwa konwentu, data i tag nie mogą być puste");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateString, formatter);

            Convent newConvent = new Convent();
            newConvent.setName(name);
            newConvent.setDate(date);
            newConvent.setTag(tag); // Ustawienie tagu

            conventRepository.save(newConvent);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Nieprawidłowy format daty");
        }
    }

    public List<Convent> getAllConvents() {
        return conventRepository.findAll();
    }
}