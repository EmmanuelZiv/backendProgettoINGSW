package com.example.backendingsw.DTO;

public class AsteCategorieInversa_DTO {
    private Long astaId;
    private String nomeCategoria;

    public AsteCategorieInversa_DTO() {
    }

    public AsteCategorieInversa_DTO(Long astaId, String nomeCategoria) {
        this.astaId = astaId;
        this.nomeCategoria = nomeCategoria;
    }

    public Long getAstaId() {
        return astaId;
    }

    public void setAstaId(Long astaId) {
        this.astaId = astaId;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
}
