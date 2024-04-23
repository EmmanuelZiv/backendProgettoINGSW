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

    @Override
    public ArrayList<Asta_allinglese> getAsteInglesiPartecipate(String indirizzo_email){
        return asta_allingleseRepository.getAsteInglesiPartecipate(indirizzo_email);
    }


//    @Override
//    public List<Object> getAste_allingleseScadenzaRecente(){
//        return asta_allingleseRepository.getAste_allingleseScadenzaRecente();
//    }
    @Override
    public Asta_allinglese save(Asta_allinglese astaAllinglese){
        return asta_allingleseRepository.save(astaAllinglese);
    }
    @Override
    public Integer insertCategorieAstaInglese(Long id_asta_allinglese, String nomeCategoria){
        return asta_allingleseRepository.insertCategorieAstaInglese(id_asta_allinglese,nomeCategoria);
    }
    @Override
    public void insert(String nome, String descrizione, byte[] path_immagine, float baseAsta, String intervalloTempoOfferte, float rialzoMin ,float prezzoAttuale, String condizione, String id_venditore){
            asta_allingleseRepository.insert(nome,descrizione,path_immagine,baseAsta,intervalloTempoOfferte,rialzoMin,prezzoAttuale,condizione,id_venditore);
    }

    @Override
    public Long getLastInsertedId(){
        return asta_allingleseRepository.getLastInsertedId();
    }
    @Override
    public String getEmailVincente(Long idAstaInglese){
        return asta_allingleseRepository.getEmailVincente(idAstaInglese);
    }
    @Override
    public ArrayList<Asta_allinglese> findByNomeAndCategorieAndCondizioneOrderByPrezzo(String nome, ArrayList<String> categorie, String ordinamento){
        return asta_allingleseRepository.findByNomeAndCategorieAndCondizioneOrderByPrezzo(nome, categorie, ordinamento);
    }
    @Override
    public ArrayList<Asta_allinglese> findByNomeAndCondizioneOrderByPrezzo(String nome, String ordinamento){
        return asta_allingleseRepository.findByNomeAndCondizioneOrderByPrezzo(nome,ordinamento);
    }
    @Override
    public ArrayList<Asta_allinglese> findByCategorieAndCondizioneOrderByPrezzo(ArrayList<String> categorie, String ordinamento){
        return asta_allingleseRepository.findByCategorieAndCondizioneOrderByPrezzo(categorie, ordinamento);
    }
    @Override
    public ArrayList<Asta_allinglese> findByCondizioneOrderByPrezzo(String ordinamento){
        return asta_allingleseRepository.findByCondizioneOrderByPrezzo(ordinamento);
    }

}
