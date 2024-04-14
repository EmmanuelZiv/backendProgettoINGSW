package com.example.backendingsw.DTO;

public class NotificheVenditore_DTO {
    private Long id;
    private String titolo;
    private String commento;
    private String id_venditore;

    // Costruttori
    public NotificheVenditore_DTO() {
    }

    public NotificheVenditore_DTO(Long id, String titolo, String commento, String id_venditore) {
        this.id = id;
        this.titolo = titolo;
        this.commento = commento;
        this.id_venditore = id_venditore;
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

    // Getter e setter per idVenditore
    public String getId_venditore() {
        return id_venditore;
    }

    public void setId_venditore(String id_venditore) {
        this.id_venditore = id_venditore;
    }
}
