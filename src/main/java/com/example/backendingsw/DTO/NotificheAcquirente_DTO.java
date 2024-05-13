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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter e setter per titolo
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    // Getter e setter per commento
    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
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

