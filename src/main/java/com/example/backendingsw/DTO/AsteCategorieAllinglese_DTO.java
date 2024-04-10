package com.example.backendingsw.DTO;

public class AsteCategorieAllinglese_DTO {
    private Long astaId;
    private String nomeCategoria;

    public AsteCategorieAllinglese_DTO() {
    }

    public AsteCategorieAllinglese_DTO(Long astaId, String nomeCategoria) {
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

