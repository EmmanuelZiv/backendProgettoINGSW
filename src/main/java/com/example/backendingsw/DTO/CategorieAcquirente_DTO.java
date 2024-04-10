package com.example.backendingsw.DTO;

public class CategorieAcquirente_DTO {
    private String nome;
    private String indirizzoEmail;

    public CategorieAcquirente_DTO() {
    }

    public CategorieAcquirente_DTO(String nome, String indirizzoEmail) {
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

