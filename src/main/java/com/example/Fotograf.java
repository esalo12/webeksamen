package com.example;

import org.springframework.data.annotation.Id;

public class Fotograf {

    @Id
    String id;

    String fornavn;
    String etternavn;
    String brukernavn;
    String passord;

    public Fotograf(){
    }

    public Fotograf(String fornavn, String etternavn, String bruker, String passord) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.brukernavn = bruker;
        this.passord = passord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }
}
