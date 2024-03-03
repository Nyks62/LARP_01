package org.example.convent;

import java.time.LocalDate;

public class Chosen {
    private String name;
    private LocalDate date;
    private String tag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setDateFromString(String dateString) {
        this.date = LocalDate.parse(dateString);
    }
}
