package com.example.backendingsw.Service.Interfaces;

import com.example.backendingsw.Model.Asta_inversa;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public interface I_Asta_inversa_Service {
    public List<Asta_inversa> findByCondizioneOrderByDataDiScadenzaAsc(String condizione);
    public List<Asta_inversa> findByCondizioneOrderByIdDesc(String condizione);
    public List<Asta_inversa> findByCategorieNomeAndCondizioneAperta(String nomeCategoria);
    public int partecipaAstaInversa(Long idAstaInversa, String indirizzo_email, float offerta, Timestamp tempo_offerta, String stato);
    public Asta_inversa findAsta_inversaById(Long idAstaInversa);
    public Integer verificaAstaInversaInPreferiti(String indirizzo_email, Long idAstaInversa);
    public Integer inserimentoAstaInPreferiti(Long idAstaInversa, String indirizzo_email);
    public Integer eliminazioneAstaInPreferiti(Long idAstaInversa, String indirizzo_email);
    public ArrayList<Asta_inversa> getAsteInversaPreferite(String indirizzo_email);
    //public Long insertAstaInversa(String nome, String descrizione, byte[] path_immagine, float prezzoMax, float prezzoAttuale, String dataDiScadenza, String condizione, String id_acquirente);
    public Integer insertCategorieAstaInversa(Long id_asta_inversa, String nomeCategoria);
    public Asta_inversa save(Asta_inversa asta);
}
