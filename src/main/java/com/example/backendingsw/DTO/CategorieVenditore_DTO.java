package com.example.backendingsw.DTO;

public class CategorieVenditore_DTO {
    private String nome;
    private String indirizzoEmail;

    // Costruttore vuoto
    public CategorieVenditore_DTO() {
    }

    // Costruttore con parametri
    public CategorieVenditore_DTO(String nome, String indirizzoEmail) {
        this.nome = nome;
        this.indirizzoEmail = indirizzoEmail;
    }

    // Metodi getter e setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzoEmail() {
        return indirizzoEmail;
    }

    public void setIndirizzoEmail(String indirizzoEmail) {
        this.indirizzoEmail = indirizzoEmail;
    }
}
