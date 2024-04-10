package com.example.backendingsw.DTO;

public class AsteCategorieAlRibasso_DTO {
    private Long astaId;
    private String nomeCategoria;

    public AsteCategorieAlRibasso_DTO() {
    }

    public AsteCategorieAlRibasso_DTO(Long astaId, String nomeCategoria) {
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
