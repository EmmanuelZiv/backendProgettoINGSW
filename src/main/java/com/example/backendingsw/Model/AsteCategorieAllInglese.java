package com.example.backendingsw.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "AsteCategorieAllInglese")
public class AsteCategorieAllInglese {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_asta_allinglese")
    private Asta_allinglese asta;

    @Id
    @Column(name = "nomeCategoria")
    private String nomeCategoria;

    // Costruttore vuoto
    public AsteCategorieAllInglese() {
    }

    // Costruttore con parametri
    public AsteCategorieAllInglese(Asta_allinglese asta, String nomeCategoria) {
        this.asta = asta;
        this.nomeCategoria = nomeCategoria;
    }

    // Metodi getter e setter
    public Asta_allinglese getAsta() {
        return asta;
    }

    public void setAsta(Asta_allinglese asta) {
        this.asta = asta;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
}
