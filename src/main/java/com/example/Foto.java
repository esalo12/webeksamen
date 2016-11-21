package com.example;

import org.springframework.data.annotation.Id;

// Klassen som brukes av denne tjenesten.
public class Foto {

    // @ID gjør MongoDB _id tilgjengelig i klassen
    @Id
    String id;

    String tittel;
    String fotograf;
    String contentType;


    public Foto(){
    }

    public Foto(String tittel, String fotograf) {
        this.tittel = tittel;
        this.fotograf = fotograf;

    }

    public String getTittel() {
        return tittel;
    }

    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    public String getFotograf() {
        return fotograf;
    }

    public void setFotograf(String fotograf) {
        this.fotograf = fotograf;
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
