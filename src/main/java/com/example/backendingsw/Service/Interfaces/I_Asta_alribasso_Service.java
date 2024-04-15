package com.example.backendingsw.Service.Interfaces;

import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.Asta_alribasso;
import com.example.backendingsw.Model.Asta_inversa;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public interface I_Asta_alribasso_Service {
    public List<Asta_alribasso> findByCondizioneOrderByIdDesc(String condizione);
    public List<Asta_alribasso> findByCategorieNomeAndCondizioneAperta(String nomeCategoria);
    public List<Asta_alribasso> findAsta_alribassoApertaByEmail(String indirizzo_email);
    public List<Asta_alribasso> findAsta_alribassoChiusaByEmail(String indirizzo_email);
    public int acquistaAstaAlRibasso(Long idAstaAlRibasso, String indirizzo_email, float prezzoAcquisto);
    public Asta_alribasso findAsta_alribassoById(Long idAstaRibasso);
    public Integer verificaAstaAlRibassoInPreferiti(String indirizzo_email, Long idAstaRibasso);
    public Integer inserimentoAstaInPreferiti(Long idAstaRibasso, String indirizzo_email);
    public Integer eliminazioneAstaInPreferiti(Long idAstaRibasso, String indirizzo_email);
    public ArrayList<Asta_alribasso> getAsteRibassoPreferite(String indirizzo_email);
}
