package com.example.backendingsw.DTO;


public class Acquirente_DTO {
    private String nome;
    private String cognome;
    private String indirizzo_email;
    private String password;
    private String bio;
    private String areageografica;
    private String link;

    public Acquirente_DTO(){

    }
    public Acquirente_DTO(String nome, String cognome, String indirizzo_email, String password, String bio, String areageografica, String link) {
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo_email = indirizzo_email;
        this.password = password;
        this.bio = bio;
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

    public String getIndirizzo_email() {
        return indirizzo_email;
    }

    public void setIndirizzo_email(String indirizzo_email) {
        this.indirizzo_email = indirizzo_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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

    public void setAreageografica(String areageografica) {
        this.areageografica = areageografica;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}