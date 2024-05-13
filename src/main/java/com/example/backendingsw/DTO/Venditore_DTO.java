package com.example.backendingsw.DTO;


public class Venditore_DTO {
    private String nome;
    private String cognome;
    private String indirizzo_email;
    private String password;
    private String bio;
    private String areageografica;
    private String link;
    private String token;

    public Venditore_DTO(){

    }
    public Venditore_DTO(String nomeV, String cognomeV, String email, String password, String descrizione, String paese, String linkV) {
        this.nome = nomeV;
        this.cognome = cognomeV;
        this.indirizzo_email = email;
        this.password = password;
        this.bio = descrizione;
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
        this.password = passwordV;
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

    public void setLink(String linkV) {
        link = linkV;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String tokenv) {
        token = tokenv;
    }
}