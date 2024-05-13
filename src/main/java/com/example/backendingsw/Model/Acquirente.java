package com.example.backendingsw.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="acquirente")
public class Acquirente {
    @Column(name="nome")
    private String nome;
    @Column(name="cognome")
    private String cognome;
    @Id
    @Column(name="indirizzo_email")
    private String indirizzo_email;
    @Column(name="password")
    private String password;
    @Column(name="bio")
    private String bio;
    @Column(name="areageografica")
    private String areageografica;
    @Column(name="link")
    private String link;
    @Column(name="token")
    private String token;

    public Acquirente(){

    }
    public Acquirente(String nome, String cognome, String indirizzo_email, String password, String bio, String areageografica, String link){
        this.nome=nome;
        this.cognome=cognome;
        this.indirizzo_email = indirizzo_email;
        this.password=password;
        this.bio=bio;
        this.areageografica = areageografica;
        this.link = link;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo_emailAcquirente() {
        return indirizzo_email;
    }

    public void setIndirizzo_emailAcquirente(String email) {
        this.indirizzo_email = email;
    }

    public String getPasswordAcquirente() {
        return password;
    }

    public void setPasswordAcquirente(String password) {
        this.password = password;
    }


    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAreageografica() {
        return areageografica;
    }

    public void setAreageografica(String paese) {
        this.areageografica = paese;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String sitoweb) {
        this.link = sitoweb;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}