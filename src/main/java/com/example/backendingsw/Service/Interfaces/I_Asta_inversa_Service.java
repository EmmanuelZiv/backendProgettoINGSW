package com.example.backendingsw.Service.Interfaces;

import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.Asta_inversa;

import java.sql.Timestamp;
import java.util.List;

public interface I_Asta_inversa_Service {
    public List<Asta_inversa> findByCondizioneOrderByDataDiScadenzaAsc(String condizione);
    public List<Asta_inversa> findByCondizioneOrderByIdDesc(String condizione);
    public List<Asta_inversa> findByCategorieNomeAndCondizioneAperta(String nomeCategoria);
    public int partecipaAstaInversa(Long idAstaInversa, String indirizzo_email, float offerta, Timestamp tempo_offerta, String stato);
    public Asta_inversa findAsta_inversaById(Long idAstaInversa);
}
