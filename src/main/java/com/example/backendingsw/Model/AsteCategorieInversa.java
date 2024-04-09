package com.example.backendingsw.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "AsteCategorieInversa ")
public class AsteCategorieInversa {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_asta_inversa")
    private Asta_inversa asta;

    @Id
    @Column(name = "nomeCategoria")
    private String nomeCategoria;

    // Costruttore vuoto
    public AsteCategorieInversa() {
    }

    // Costruttore con parametri
    public AsteCategorieInversa(Asta_inversa asta, String nomeCategoria) {
        this.asta = asta;
        this.nomeCategoria = nomeCategoria;
    }

    // Metodi getter e setter
    public Asta_inversa getAsta() {
        return asta;
    }

    public void setAsta(Asta_inversa asta) {
        this.asta = asta;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
}
