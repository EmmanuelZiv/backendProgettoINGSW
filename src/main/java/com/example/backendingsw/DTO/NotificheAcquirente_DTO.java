package com.example.backendingsw.DTO;

public class NotificheAcquirente_DTO {
    private Long id;
    private String titolo;
    private String commento;
    private String id_acquirente;

    // Costruttori
    public NotificheAcquirente_DTO() {
    }

    public NotificheAcquirente_DTO(Long id, String titolo, String commento, String id_acquirente) {
        this.id = id;
        this.titolo = titolo;
        this.commento = commento;
        this.id_acquirente = id_acquirente;
    }

    // Getter e setter per id
    public Long getIdNotificaAcquirenteDTO() {
        return id;
    }

    public void setIdNotificaAcquirenteDTO(Long id) {
        this.id = id;
    }

    // Getter e setter per titolo
    public String getTitoloNotificaAcquirenteDTO() {
        return titolo;
    }

    public void setTitoloNotificaAcquirenteDTO(String titolo) {
        this.titolo = titolo;
    }

    // Getter e setter per commento
    public String getCommentoNotificaAcquirenteDTO() {
        return commento;
    }

    public void setCommentoNotificaAcquirenteDTO(String commento) {
        this.commento = commento;
    }

    // Getter e setter per idAcquirente
    public String getId_Acquirente() {
        return id_acquirente;
    }

    public void setId_Acquirente(String id_acquirente) {
        this.id_acquirente = id_acquirente;
    }
}

