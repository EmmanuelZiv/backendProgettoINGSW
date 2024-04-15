package com.example.backendingsw.Service.Interfaces;

import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.Asta_inversa;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public interface I_Asta_inversa_Service {
    public List<Asta_inversa> findByCondizioneOrderByDataDiScadenzaAsc(String condizione);
    public List<Asta_inversa> findByCondizioneOrderByIdDesc(String condizione);
    public List<Asta_inversa> findByCategorieNomeAndCondizioneAperta(String nomeCategoria);
    public List<Asta_inversa> findAsta_inversaApertaByEmail(String indirizzo_email);
    public List<Asta_inversa> findAsta_inversaChiusaByEmail(String indirizzo_email);
    public int partecipaAstaInversa(Long idAstaInversa, String indirizzo_email, float offerta, Timestamp tempo_offerta, String stato);
    public Asta_inversa findAsta_inversaById(Long idAstaInversa);
    public Integer verificaAstaInversaInPreferiti(String indirizzo_email, Long idAstaInversa);
    public Integer inserimentoAstaInPreferiti(Long idAstaInversa, String indirizzo_email);
    public Integer eliminazioneAstaInPreferiti(Long idAstaInversa, String indirizzo_email);
    public ArrayList<Asta_inversa> getAsteInversaPreferite(String indirizzo_email);
}
