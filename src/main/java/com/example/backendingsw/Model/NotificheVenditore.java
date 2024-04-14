package com.example.backendingsw.Model;

import jakarta.persistence.*;


@Entity
@Table(name = "notificheVenditore")
public class NotificheVenditore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titolo", nullable = false)
    private String titolo;

    @Column(name = "commento", columnDefinition = "TEXT")
    private String commento;

    @Column(name = "id_venditore", nullable = false)
    private String id_venditore;


    public NotificheVenditore() {
    }

    public NotificheVenditore(String titolo, String commento, String id_venditore) {
        this.titolo = titolo;
        this.commento = commento;
        this.id_venditore = id_venditore;
    }

    // Getter e setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }
    public String getId_venditore() {
        return id_venditore;
    }

    public void setId_venditore(String id_venditore) {
        this.id_venditore = id_venditore;
    }
}