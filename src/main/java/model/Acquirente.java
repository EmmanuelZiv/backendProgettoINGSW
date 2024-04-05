package model;

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
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="bio")
    private String bio;
    @Column(name="areageografica")
    private String paese;
    @Column(name="link")
    private String sitoweb;

    public Acquirente(){

    }
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