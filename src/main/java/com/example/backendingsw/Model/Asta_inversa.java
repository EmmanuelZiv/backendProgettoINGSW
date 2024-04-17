package com.example.backendingsw.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "asta_inversa")
public class Asta_inversa {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "path_immagine")
    private byte[] path_immagine;

    @Column(name = "prezzoMax")
    private float prezzoMax;

    @Column(name = "prezzoAttuale")
    private float prezzoAttuale;

    @Column(name = "dataDiScadenza")
    private Timestamp dataDiScadenza;

    @Column(name = "condizione")
    private String condizione;

    @Column(name = "id_acquirente")
    private String id_acquirente; // considerando che id_acquirente è una stringa

    // Costruttori

    public Asta_inversa() {
    }

    public Asta_inversa(String nome, String descrizione, byte[] path_immagine, float prezzoMax, float prezzoAttuale,
                        Timestamp dataDiScadenza, String condizione, String id_acquirente) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.path_immagine = path_immagine;
        this.prezzoMax = prezzoMax;
        this.prezzoAttuale = prezzoAttuale;
        this.dataDiScadenza = dataDiScadenza;
        this.condizione = condizione;
        this.id_acquirente = id_acquirente;
    }

    // Metodi getter e setter

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

    public float getPrezzoMax() {
        return prezzoMax;
    }

    public void setPrezzoMax(float prezzoMax) {
        this.prezzoMax = prezzoMax;
    }

    public float getPrezzoAttuale() {
        return prezzoAttuale;
    }

    public void setPrezzoAttuale(float prezzoAttuale) {
        this.prezzoAttuale = prezzoAttuale;
    }

    public Timestamp getDataDiScadenza() {
        return dataDiScadenza;
    }

    public void setDataDiScadenza(Timestamp dataDiScadenza) {
        this.dataDiScadenza = dataDiScadenza;
    }

    public String getCondizione() {
        return condizione;
    }

    public void setCondizione(String condizione) {
        this.condizione = condizione;
    }

    public String getId_acquirente() {
        return id_acquirente;
    }

    public void setId_acquirente(String id_acquirente) {
        this.id_acquirente = id_acquirente;
    }
}
