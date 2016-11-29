package com.example;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Kommentar {
    @Id
    String id;

    Date dato;
    String navn;
    String kommentar;

    public Kommentar(){
    }

    public Kommentar(String navn, String kommentar){
        this.navn = navn;
        this.kommentar = kommentar;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDato() {
        return dato;
    }

    public void setDato(Date dato) {
        this.dato = dato;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }
}
