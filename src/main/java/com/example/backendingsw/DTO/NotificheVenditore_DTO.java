package com.example.backendingsw.DTO;

public class NotificheVenditore_DTO {
    private Long id;
    private String titolo;
    private String commento;
    private String id_venditore;

    // Costruttori
    public NotificheVenditore_DTO() {
    }

    public NotificheVenditore_DTO(Long idNotifica, String titoloV, String commentoV, String idV) {
        this.id = idNotifica;
        this.titolo = titoloV;
        this.commento = commentoV;
        this.id_venditore = idV;
    }

    // Getter e setter per id
    public Long getId() {
        return id;
    }

    public void setId(Long idNotifica) {
        id = idNotifica;
    }

    // Getter e setter per titolo
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titoloV) {
        titolo = titoloV;
    }

    // Getter e setter per commento
    public String getCommento() {
        return commento;
    }

    public void setCommento(String commentoV) {
        commento = commentoV;
    }

    // Getter e setter per idVenditore
    public String getId_venditore() {
        return id_venditore;
    }

    public void setId_venditore(String idV) {
        id_venditore = idV;
    }
}
