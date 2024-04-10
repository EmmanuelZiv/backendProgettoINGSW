package com.example.backendingsw.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorieAcquirente")
public class CategorieAcquirente {
    @Id
    @Column(name = "nome")
    private String nome;

    @Id
    @Column(name = "indirizzo_email")
    private String indirizzoEmail;

    // Costruttore vuoto
    public CategorieAcquirente() {
    }

    // Costruttore con parametri
    public CategorieAcquirente(String nome, String indirizzoEmail) {
        this.nome = nome;
        this.indirizzoEmail = indirizzoEmail;
    }

    // Metodi getter e setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzoEmail() {
        return indirizzoEmail;
    }

    public void setIndirizzoEmail(String indirizzoEmail) {
        this.indirizzoEmail = indirizzoEmail;
    }

}
