package com.example.backendingsw.Service.Implementations;

import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.Asta_inversa;
import com.example.backendingsw.Repository.Asta_allingleseRepository;
import com.example.backendingsw.Service.Interfaces.I_Asta_allinglese_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service("Impl_Asta_allinglese_Service")
public class Impl_Asta_allinglese_Service implements I_Asta_allinglese_Service {

    @Autowired // crea l'oggetto
    private Asta_allingleseRepository asta_allingleseRepository;

    @Override
    public List<Asta_allinglese> findByCondizioneOrderByIntervalloTempoOfferteAsc(String condizione){
        return asta_allingleseRepository.findFirst5ByCondizioneOrderByIntervalloTempoOfferteAsc("aperta");
    }
    @Override
    public List<Asta_allinglese> findByCondizioneOrderByIdDesc(String condizione){
        return asta_allingleseRepository.findFirst5ByCondizioneOrderByIdDesc("aperta");
    }
    @Override
    public List<Asta_allinglese> findByCategorieNomeAndCondizioneAperta(String nomeCategoria){
        return asta_allingleseRepository.findFirst5ByCategorieNomeAndCondizioneAperta(nomeCategoria);
    }

    @Override
    public List<Asta_allinglese> findAsta_allIngleseApertaByEmail(String indirizzo_email){
        return asta_allingleseRepository.findAsta_allingleseApertaByEmail(indirizzo_email);
    }
    @Override
    public List<Asta_allinglese> findAsta_allIngleseChiusaByEmail(String indirizzo_email){
        return asta_allingleseRepository.findAsta_allingleseChiusaByEmail(indirizzo_email);
    }

    @Override
    public Integer partecipaAstaInglese(Long idAstaInglese, String indirizzo_email, float offerta, Timestamp tempo_offerta, String stato){
        return asta_allingleseRepository.partecipaAstaInglese(idAstaInglese,indirizzo_email,offerta,tempo_offerta,stato);
    }
    @Override
    public Asta_allinglese findAsta_allingleseById(Long idAstaInglese){
        return asta_allingleseRepository.findAsta_allingleseById(idAstaInglese);
    }
    @Override
    public Integer verificaAstaIngleseInPreferiti(String indirizzo_email, Long idAstaInglese){
        return asta_allingleseRepository.verificaAstaIngleseInPreferiti(indirizzo_email, idAstaInglese);
    }
    @Override
    public Integer inserimentoAstaInPreferiti(Long idAstaInglese, String indirizzo_email){
        return asta_allingleseRepository.inserimentoAstaInPreferiti(idAstaInglese,indirizzo_email);
    }
    @Override
    public Integer eliminazioneAstaInPreferiti(Long idAstaInglese, String indirizzo_email){
        return asta_allingleseRepository.eliminazioneAstaInPreferiti(idAstaInglese,indirizzo_email);
    }
    @Override
    public ArrayList<Asta_allinglese> getAsteInglesePreferite(String indirizzo_email){
        return asta_allingleseRepository.getAsteInglesePreferite(indirizzo_email);
    }
//    @Override
//    public List<Object> getAste_allingleseScadenzaRecente(){
//        return asta_allingleseRepository.getAste_allingleseScadenzaRecente();
//    }



}
