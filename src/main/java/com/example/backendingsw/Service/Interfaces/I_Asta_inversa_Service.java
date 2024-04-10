package com.example.backendingsw.Service.Interfaces;

import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.Asta_inversa;

import java.util.List;

public interface I_Asta_inversa_Service {
    public List<Asta_inversa> findByCondizioneOrderByDataDiScadenzaAsc(String condizione);
    public List<Asta_inversa> findByCondizioneOrderByIdDesc(String condizione);
    public List<Asta_inversa> findByCategorieNomeAndCondizioneAperta(String nomeCategoria);
}
