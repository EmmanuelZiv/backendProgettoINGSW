package model;

import jakarta.persistence.Entity;

@Entity
public class Acquirente {



    private String nome;
    private String cognome;

    private String email;



    private String password;
    private String bio;
    private String paese;


    private String sitoweb;


    public Acquirente(String nome,String cognome,String email,String password,String bio,String paese,String sitoweb){
        this.nome=nome;
        this.cognome=cognome;
        this.email=email;
        this.password=password;
        this.bio=bio;
        this.paese=paese;
        this.sitoweb=sitoweb;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPaese() {
        return paese;
    }

    public void setPaese(String paese) {
        this.paese = paese;
    }

    public String getSitoweb() {
        return sitoweb;
    }

    public void setSitoweb(String sitoweb) {
        this.sitoweb = sitoweb;
    }



}
