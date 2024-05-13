package com.example.backendingsw.Model;
import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "asta_allinglese")
public class Asta_allinglese {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descrizione", length = 250)
    private String descrizione;


    @Column(name = "path_immagine")
    private byte[] path_immagine;

    @Column(name = "baseAsta", nullable = false)
    private float baseAsta;

    @Column(name = "intervalloTempoOfferte", nullable = false)
    private String intervalloTempoOfferte;

    @Column(name = "intervalloOfferteBase", nullable = false)
    private String intervalloOfferteBase;

    @Column(name = "rialzoMin", nullable = false)
    private float rialzoMin;

    @Column(name = "prezzoAttuale", nullable = false)
    private float prezzoAttuale;

    @Column(name = "condizione", nullable = false)
    private String condizione;

    @Column(name = "id_venditore", nullable = false)
    private String idVenditore;

    @OneToMany(mappedBy = "asta", cascade = CascadeType.ALL)
    private Set<AsteCategorieAllInglese> categorie;

    public Asta_allinglese() {
        // Costruttore vuoto richiesto da JPA
    }

    public Asta_allinglese(String nome, String descrizione, byte[] path_immagine, float baseAsta, String intervalloTempoOfferte, String intervalloOfferteBase, float rialzoMin, float prezzoAttuale, String condizione, String idVenditore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.path_immagine = path_immagine;
        this.baseAsta = baseAsta;
        this.intervalloTempoOfferte = intervalloTempoOfferte;
        this.intervalloOfferteBase = intervalloOfferteBase;
        this.rialzoMin = rialzoMin;
        this.prezzoAttuale = prezzoAttuale;
        this.condizione = condizione;
        this.idVenditore = idVenditore;
    }

    // Getters and setters
    public Long getIdInglese() {
        return id;
    }

    public void setIdInglese(Long id) {
        this.id = id;
    }

    public String getNomeInglese() {
        return nome;
    }

    public void setNomeInglese(String nome) {
        this.nome = nome;
    }

    public String getDescrizioneInglese() {
        return descrizione;
    }

    public void setDescrizioneInglese(String descrizione) {
        this.descrizione = descrizione;
    }

    public byte[] getPath_immagineInglese() {
        return path_immagine;
    }

    public void setPath_immagineInglese(byte[] path_immagine) {
        this.path_immagine = path_immagine;
    }

    public float getBaseAstaInglese() {
        return baseAsta;
    }

    public void setBaseAstaInglese(float baseAsta) {this.baseAsta = baseAsta;}

    public String getIntervalloTempoOfferteInglese() {
        return intervalloTempoOfferte;
    }

    public void setIntervalloTempoOfferteInglese(String intervalloTempoOfferte) {
        this.intervalloTempoOfferte = intervalloTempoOfferte;
    }

    public String getIntervalloOfferteBaseInglese() {
        return intervalloOfferteBase;
    }

    public void setIntervalloOfferteBaseInglese(String intervalloOfferteBase) {
        this.intervalloOfferteBase = intervalloOfferteBase;
    }

    public float getRialzoMinInglese() {
        return rialzoMin;
    }

    public void setRialzoMinInglese(float rialzoMin) {
        this.rialzoMin = rialzoMin;
    }

    public float getPrezzoAttualeInglese() {
        return prezzoAttuale;
    }

    public void setPrezzoAttualeInglese(float prezzoAttuale) {
        this.prezzoAttuale = prezzoAttuale;
    }

    public String getCondizioneInglese() {
        return condizione;
    }

    public void setCondizioneInglese(String condizione) {
        this.condizione = condizione;
    }

    public String getIdVenditoreInglese() {
        return idVenditore;
    }

    public void setIdVenditoreInglese(String idVenditore) {
        this.idVenditore = idVenditore;
    }
}
