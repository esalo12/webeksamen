package com.example;

import org.springframework.data.annotation.Id;

// Klassen som brukes av denne tjenesten.
public class Foto {

    // @ID gjør MongoDB _id tilgjengelig i klassen
    @Id
    String id;

    Integer antall;
    String varenavn;
    Integer sortering;

    public Foto(){
    }

    public Foto(Integer antall, String varenavn, Integer sortering) {
        this.antall = antall;
        this.varenavn = varenavn;
        this.sortering = sortering;
    }

    public Integer getAntall() {
        return antall;
    }

    public void setAntall(Integer antall) {
        this.antall = antall;
    }

    public String getVarenavn() {
        return varenavn;
    }

    public void setVarenavn(String varenavn) {
        this.varenavn = varenavn;
    }

    public Integer getSortering() {
        return sortering;
    }

    public void setSortering(Integer sortering) {
        this.sortering = sortering;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
