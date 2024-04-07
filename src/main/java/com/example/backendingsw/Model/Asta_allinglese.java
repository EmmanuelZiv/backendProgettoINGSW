package com.example.backendingsw.Model;
import jakarta.persistence.*;


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

    @Lob
    @Column(name = "path_immagine")
    private byte[] pathImmagine;

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

    public Asta_allinglese() {
        // Costruttore vuoto richiesto da JPA
    }

    public Asta_allinglese(String nome, String descrizione, byte[] pathImmagine, float baseAsta, String intervalloTempoOfferte, String intervalloOfferteBase, float rialzoMin, float prezzoAttuale, String condizione, String idVenditore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.pathImmagine = pathImmagine;
        this.baseAsta = baseAsta;
        this.intervalloTempoOfferte = intervalloTempoOfferte;
        this.intervalloOfferteBase = intervalloOfferteBase;
        this.rialzoMin = rialzoMin;
        this.prezzoAttuale = prezzoAttuale;
        this.condizione = condizione;
        this.idVenditore = idVenditore;
    }

    // Getters and setters
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

    public byte[] getPathImmagine() {
        return pathImmagine;
    }

    public void setPathImmagine(byte[] pathImmagine) {
        this.pathImmagine = pathImmagine;
    }

    public float getBaseAsta() {
        return baseAsta;
    }

    public void setBaseAsta(float baseAsta) {
        this.baseAsta = baseAsta;
    }

    public String getIntervalloTempoOfferte() {
        return intervalloTempoOfferte;
    }

    public void setIntervalloTempoOfferte(String intervalloTempoOfferte) {
        this.intervalloTempoOfferte = intervalloTempoOfferte;
    }

    public String getIntervalloOfferteBase() {
        return intervalloOfferteBase;
    }

    public void setIntervalloOfferteBase(String intervalloOfferteBase) {
        this.intervalloOfferteBase = intervalloOfferteBase;
    }

    public float getRialzoMin() {
        return rialzoMin;
    }

    public void setRialzoMin(float rialzoMin) {
        this.rialzoMin = rialzoMin;
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

    public String getIdVenditore() {
        return idVenditore;
    }

    public void setIdVenditore(String idVenditore) {
        this.idVenditore = idVenditore;
    }
}
