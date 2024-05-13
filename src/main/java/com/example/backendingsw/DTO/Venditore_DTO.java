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

    public String getNomeDTO() {
        return nome;
    }

    public void setNomeDTO(String nomeV) {
        nome = nomeV;
    }

    public String getCognomeDTO() {
        return cognome;
    }

    public void setCognomeDTO(String cognomeV) {
        cognome = cognomeV;
    }

    public String getIndirizzo_emailDTO() {
        return indirizzo_email;
    }

    public void setIndirizzo_emailDTO(String email) {
        indirizzo_email = email;
    }

    public String getPasswordDTO() {
        return password;
    }

    public void setPasswordDTO(String passwordV) {
        this.password = passwordV;
    }


    public String getBioDTO() {
        return bio;
    }

    public void setBioDTO(String descrizione) {
        bio = descrizione;
    }

    public String getAreageograficaDTO() {
        return areageografica;
    }

    public void setAreageograficaDTO(String paese) {
        areageografica = paese;
    }

    public String getLinkDTO() {
        return link;
    }

    public void setLinkDTO(String linkV) {
        link = linkV;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String tokenv) {
        token = tokenv;
    }
}