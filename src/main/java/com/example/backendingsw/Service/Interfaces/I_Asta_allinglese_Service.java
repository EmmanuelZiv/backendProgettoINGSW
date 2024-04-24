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
    public List<Asta_allinglese> findAsta_allIngleseApertaByEmail(String indirizzo_email);
    public List<Asta_allinglese> findAsta_allIngleseChiusaByEmail(String indirizzo_email);
    public Integer partecipaAstaInglese(Long idAstaInglese, String indirizzo_email, float offerta, Timestamp tempo_offerta, String stato);
    public Asta_allinglese findAsta_allingleseById(Long idAstaInglese);
    public Integer verificaAstaIngleseInPreferiti(String indirizzo_email, Long idAstaInglese);
    public Integer inserimentoAstaInPreferiti(Long idAstaInglese, String indirizzo_email);
    public Integer eliminazioneAstaInPreferiti(Long idAstaInglese, String indirizzo_email);
    public ArrayList<Asta_allinglese> getAsteInglesePreferite(String indirizzo_email);
    public ArrayList<Asta_allinglese> getAsteInglesiPartecipate(String indirizzo_email);
    public Asta_allinglese save(Asta_allinglese astaAllinglese);
    public Integer insertCategorieAstaInglese(Long id_asta_allinglese, String nomeCategoria);
    public void insert(Long id,String nome, String descrizione, byte[] path_immagine, float baseAsta, String intervalloTempoOfferte, float rialzoMin ,float prezzoAttuale, String condizione, String id_venditore);
    public Long getLastInsertedId();
    public String getEmailVincente(Long idAstaInglese);
    public ArrayList<Asta_allinglese> findByNomeAndCategorieAndCondizioneOrderByPrezzo(String nome, ArrayList<String> categorie, String ordinamento);
    public ArrayList<Asta_allinglese> findByNomeAndCondizioneOrderByPrezzo(String nome, String ordinamento);
    public ArrayList<Asta_allinglese> findByCategorieAndCondizioneOrderByPrezzo(ArrayList<String> categorie, String ordinamento);
    public ArrayList<Asta_allinglese> findByCondizioneOrderByPrezzo(String ordinamento);
}
