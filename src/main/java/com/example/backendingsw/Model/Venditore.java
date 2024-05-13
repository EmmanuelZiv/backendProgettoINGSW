package com.example.backendingsw.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="venditore")
public class Venditore {
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

    public Venditore(){

    }
    public Venditore(String nomeV, String cognomeV, String email, String passwordV, String descrizione, String paese, String linkV){
        this.nome=nomeV;
        this.cognome=cognomeV;
        this.indirizzo_email = email;
        this.password=passwordV;
        this.bio=descrizione;
        this.areageografica = paese;
        this.link = linkV;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nomeV) {
        nome = nomeV;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognomeV) {
        cognome = cognomeV;
    }

    public String getIndirizzo_email() {
        return indirizzo_email;
    }

    public void setIndirizzo_email(String email) {
        indirizzo_email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordV) {
        password = passwordV;
    }


    public String getBio() {
        return bio;
    }

    public void setBio(String descrizione) {
        bio = descrizione;
    }

    public String getAreageografica() {
        return areageografica;
    }

    public void setAreageografica(String paese) {
        areageografica = paese;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String sitoweb) {
        link = sitoweb;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}