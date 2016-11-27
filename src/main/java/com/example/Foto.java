package com.example;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Klassen som brukes av denne tjenesten.
public class Foto {

    // @ID gjør MongoDB _id tilgjengelig i klassen
    @Id
    String id;

    String tittel;
    String fotografId;
    String filtype;
    String filnavn;
    Integer storrelse;
    Date dato;
    List<Kommentar> kommentarer;



    public Foto(){
    }

    public Foto(String tittel, String fotografId) {
        this.tittel = tittel;
        this.fotografId = fotografId;
    }

    public String getTittel() {
        return tittel;
    }

    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    public String getFotografId() {
        return fotografId;
    }

    public void setFotografId(String fotografId) {
        this.fotografId = fotografId;
    }

    public Date getDato() {
        return dato;
    }

    public void setDato() {
        this.dato = new Date();
    }

    public String getFiltype() {
        return filtype;
    }

    public void setFiltype(String filtype) {
        this.filtype = filtype;
    }

    public String getFilnavn() {
        return filnavn;
    }

    public void setFilnavn(String filnavn) {
        this.filnavn = filnavn;
    }

    public Integer getStorrelse() {
        return storrelse;
    }

    public void setStorrelse(Integer storrels) {
        this.storrelse = storrels;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Kommentar> getKommentarer() {
        return kommentarer;
    }

    public void setKommentarer() {
        this.kommentarer = new ArrayList<>();
    }

    public void addKommentarer(Kommentar kommentar) {
        this.getKommentarer().add(kommentar);
    }
}
