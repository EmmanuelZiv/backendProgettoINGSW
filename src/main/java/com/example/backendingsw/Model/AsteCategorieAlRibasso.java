package com.example.backendingsw.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "AsteCategorieAlRibasso ")
public class AsteCategorieAlRibasso {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_asta_alribasso")
    private Asta_alribasso asta;

    @Id
    @Column(name = "nomeCategoria")
    private String nomeCategoria;

    // Costruttore vuoto
    public AsteCategorieAlRibasso() {
    }

    // Costruttore con parametri
    public AsteCategorieAlRibasso(Asta_alribasso asta, String nomeCategoria) {
        this.asta = asta;
        this.nomeCategoria = nomeCategoria;
    }
    // Metodi getter e setter
    public Asta_alribasso getAsta() {
        return asta;
    }

    public void setAsta(Asta_alribasso asta) {
        this.asta = asta;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

}
