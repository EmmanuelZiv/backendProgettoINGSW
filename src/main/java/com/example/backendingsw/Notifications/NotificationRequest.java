package com.example.backendingsw.Notifications;

public class NotificationRequest {
    private String titolo;
    private String corpo;
    private String tokenDestinatario;

    // Costruttore
    public NotificationRequest(String titolo, String corpo, String tokenDestinatario) {
        this.titolo = titolo;
        this.corpo = corpo;
        this.tokenDestinatario = tokenDestinatario;
    }

    // Metodi getter e setter
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getTokenDestinatario() {
        return tokenDestinatario;
    }

    public void setTokenDestinatario(String tokenDestinatario) {
        this.tokenDestinatario = tokenDestinatario;
    }
}

