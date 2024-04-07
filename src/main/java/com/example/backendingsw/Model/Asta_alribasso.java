package com.example.backendingsw.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "asta_alribasso")
public class Asta_alribasso {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "path_immagine")
    private byte[] path_immagine;

    @Column(name = "prezzoBase")
    private float prezzoBase;

    @Column(name = "intervalloDecrementale")
    private String intervalloDecrementale; // in minuti

    @Column(name = "intervalloBase")
    private String intervalloBase; // in minuti

    @Column(name = "decrementoAutomaticoCifra")
    private float decrementoAutomaticoCifra;

    @Column(name = "prezzoMin")
    private float prezzoMin;

    @Column(name = "prezzoAttuale")
    private float prezzoAttuale;

    @Column(name = "condizione")
    private String condizione;

    @Column(name = "id_venditore")
    private String id_venditore; // considerando che id_venditore è una stringa

    // Costruttore, getter e setter

    public Asta_alribasso() {
    }

    public Asta_alribasso(String nome, String descrizione, byte[] path_immagine, float prezzoBase,
                          String intervalloDecrementale, String intervalloBase, float decrementoAutomaticoCifra,
                          float prezzoMin, float prezzoAttuale, String condizione, String id_venditore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.path_immagine = path_immagine;
        this.prezzoBase = prezzoBase;
        this.intervalloDecrementale = intervalloDecrementale;
        this.intervalloBase = intervalloBase;
        this.decrementoAutomaticoCifra = decrementoAutomaticoCifra;
        this.prezzoMin = prezzoMin;
        this.prezzoAttuale = prezzoAttuale;
        this.condizione = condizione;
        this.id_venditore = id_venditore;
    }

    // Getter e setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public byte[] getPath_immagine() {
        return path_immagine;
    }

    public void setPath_immagine(byte[] path_immagine) {
        this.path_immagine = path_immagine;
    }

    public float getPrezzoBase() {
        return prezzoBase;
    }

    public void setPrezzoBase(float prezzoBase) {
        this.prezzoBase = prezzoBase;
    }

    public String getIntervalloDecrementale() {
        return intervalloDecrementale;
    }

    public void setIntervalloDecrementale(String intervalloDecrementale) {
        this.intervalloDecrementale = intervalloDecrementale;
    }

    public String getIntervalloBase() {
        return intervalloBase;
    }

    public void setIntervalloBase(String intervalloBase) {
        this.intervalloBase = intervalloBase;
    }

    public float getDecrementoAutomaticoCifra() {
        return decrementoAutomaticoCifra;
    }

    public void setDecrementoAutomaticoCifra(float decrementoAutomaticoCifra) {
        this.decrementoAutomaticoCifra = decrementoAutomaticoCifra;
    }

    public float getPrezzoMin() {
        return prezzoMin;
    }

    public void setPrezzoMin(float prezzoMin) {
        this.prezzoMin = prezzoMin;
    }

    public float getPrezzoAttuale() {
        return prezzoAttuale;
    }

    public void setPrezzoAttuale(float prezzoAttuale) {
        this.prezzoAttuale = prezzoAttuale;
    }

    public String getCondizione() {
        return condizione;
    }

    public void setCondizione(String condizione) {
        this.condizione = condizione;
    }

    public String getId_venditore() {
        return id_venditore;
    }

    public void setId_venditore(String id_venditore) {
        this.id_venditore = id_venditore;
    }
}
