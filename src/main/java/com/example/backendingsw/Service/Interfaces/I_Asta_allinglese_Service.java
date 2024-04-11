package com.example.backendingsw.Service.Interfaces;

import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.Asta_inversa;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface I_Asta_allinglese_Service {
    public List<Asta_allinglese> findByCondizioneOrderByIntervalloTempoOfferteAsc(String condizione);
    public List<Asta_allinglese> findByCondizioneOrderByIdDesc(String condizione);
    public List<Asta_allinglese> findByCategorieNomeAndCondizioneAperta(String nomeCategoria);
    public Integer partecipaAstaInglese(Long idAstaInglese, String indirizzo_email, float offerta, Timestamp tempo_offerta, String stato);
    public Asta_allinglese findAsta_allingleseById(Long idAstaInglese);
    public Integer verificaAstaIngleseInPreferiti(String indirizzo_email, Long idAstaInglese);
    public Integer inserimentoAstaInPreferiti(Long idAstaInglese, String indirizzo_email);
    public Integer eliminazioneAstaInPreferiti(Long idAstaInglese, String indirizzo_email);
    public ArrayList<Asta_allinglese> getAsteInglesePreferite(String indirizzo_email);
}
