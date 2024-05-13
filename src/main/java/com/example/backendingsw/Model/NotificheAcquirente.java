package com.example.backendingsw.Model;

import jakarta.persistence.*;


@Entity
@Table(name = "notificheAcquirente")
public class NotificheAcquirente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titolo", nullable = false)
    private String titolo;

    @Column(name = "commento", columnDefinition = "TEXT")
    private String commento;

    @Column(name = "id_acquirente", nullable = false)
    private String id_acquirente;


    public NotificheAcquirente() {
    }

    public NotificheAcquirente(String titolo, String commento, String id_acquirente) {
        this.titolo = titolo;
        this.commento = commento;
        this.id_acquirente = id_acquirente;
    }

    // Getter e setter
    public Long getIdNotificaAcquirente() {
        return id;
    }

    public void setIdNotificaAcquirente(Long id) {
        this.id = id;
    }
    public String getTitoloNotificaAcquirente() {
        return titolo;
    }

    public void setTitoloNotificaAcquirente(String titolo) {
        this.titolo = titolo;
    }
    public String getCommentoNotificaAcquirente() {
        return commento;
    }

    public void setCommentoNotificaAcquirente(String commento) {
        this.commento = commento;
    }
    public String getId_acquirente() {
        return id_acquirente;
    }

    public void setId_acquirente(String id_acquirente) {
        this.id_acquirente = id_acquirente;
    }
}
