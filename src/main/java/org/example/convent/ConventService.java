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
import java.util.List;

@Service
@Transactional
public class ConventService {
    @Autowired
    private ConventRepository conventRepository;

    public void createNewConvent(String name, String dateString) {

        System.out.println("POTATO createNewConvent");
        // Sprawdzenie, czy dane wejściowe nie są puste
        if (name == null || dateString == null || name.isEmpty() || dateString.isEmpty()) {
            throw new IllegalArgumentException("Nazwa konwentu i data nie mogą być puste");
        }
        try {
            System.out.println("Received date string: " + dateString);
            //dateString = "11-12-2010";

            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate date = LocalDate.parse(dateString, formatter);

            Convent newConvent = new Convent();
            newConvent.setName(name);
            newConvent.setDate(date);

            conventRepository.save(newConvent);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Nieprawidłowy format daty");
        }

        // do wywalenia wszystko ponizej

        try {

            List<Convent> list = getAllConvents();
            String logname = "DataBaseLogger.txt";
            FileWriter myWriter = new FileWriter(logname);
            myWriter.write("\n START POTATO logger DATABASE:");

        for (Convent con : list)
        {
            myWriter.write("\n POTATO logger DATABASE:" + con.getName());
        }
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // do wywalenia wszystk powyzej



    }

    public List<Convent> getAllConvents() {
        return conventRepository.findAll();
    }
}



