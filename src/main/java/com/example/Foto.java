package com.example;

import org.springframework.data.annotation.Id;

import java.util.Date;

// Klassen som brukes av denne tjenesten.
public class Foto {

    // @ID gjør MongoDB _id tilgjengelig i klassen
    @Id
    String id;

    String tittel;
    String fotografId;
    String contentType;
    Date dato;


    public Foto(){
    }

    public Foto(String tittel) {
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
