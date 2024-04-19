package com.example.backendingsw.Service.Implementations;

import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.Asta_alribasso;
import com.example.backendingsw.Repository.Asta_alribassoRepository;
import com.example.backendingsw.Service.Interfaces.I_Asta_alribasso_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("Impl_Asta_alribasso_Service")
public class Impl_Asta_alribasso_Service implements I_Asta_alribasso_Service {
    @Autowired
    private Asta_alribassoRepository asta_alribassoRepository;

    @Override
    public List<Asta_alribasso> findByCondizioneOrderByIdDesc(String condizione){
        return asta_alribassoRepository.findByCondizioneOrderByIdDesc("aperta");
    }
    @Override
    public List<Asta_alribasso> findByCategorieNomeAndCondizioneAperta(String nomeCategoria){
        return asta_alribassoRepository.findByCategorieNomeAndCondizioneAperta(nomeCategoria);
    }

    @Override
    public List<Asta_alribasso> findAsta_alribassoApertaByEmail(String indirizzo_email){
        return asta_alribassoRepository.findAsta_alribassoApertaByEmail(indirizzo_email);
    }
    @Override
    public List<Asta_alribasso> findAsta_alribassoChiusaByEmail(String indirizzo_email){
        return asta_alribassoRepository.findAsta_alribassoChiusaByEmail(indirizzo_email);
    }

    @Override
    public int acquistaAstaAlRibasso(Long idAstaAlRibasso, String indirizzo_email, float prezzoAcquisto){
        try{
            return asta_alribassoRepository.acquistaAstaAlRibasso(idAstaAlRibasso, indirizzo_email, prezzoAcquisto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante l'inserimento dell'asta al ribasso nel database.");
        }
    }
    @Override
    public Asta_alribasso findAsta_alribassoById(Long idAstaRibasso){
        return asta_alribassoRepository.findAsta_alribassoById(idAstaRibasso);
    }
    @Override
    public Integer verificaAstaAlRibassoInPreferiti(String indirizzo_email, Long idAstaRibasso){
        return asta_alribassoRepository.verificaAstaAlRibassoInPreferiti(indirizzo_email,idAstaRibasso);
    }
    @Override
    public Integer inserimentoAstaInPreferiti(Long idAstaRibasso, String indirizzo_email){
        return asta_alribassoRepository.inserimentoAstaInPreferiti(idAstaRibasso,indirizzo_email);
    }
    @Override
    public Integer eliminazioneAstaInPreferiti(Long idAstaRibasso, String indirizzo_email){
        return asta_alribassoRepository.eliminazioneAstaInPreferiti(idAstaRibasso,indirizzo_email);
    }
    @Override
    public ArrayList<Asta_alribasso> getAsteRibassoPreferite(String indirizzo_email){
        return asta_alribassoRepository.getAsteRibassoPreferite(indirizzo_email);
    }

    @Override
    public Integer insertCategorieAstaRibasso(Long id_asta_alribasso, String nomeCategoria){
        return asta_alribassoRepository.insertCategorieAstaRibasso(id_asta_alribasso,nomeCategoria);
    }
    @Override
    public void insert(String nome, String descrizione, byte[] path_immagine, float prezzoBase, String intervalloDecrementale,
                       float decrementoAutomaticoCifra ,float prezzoMin, float prezzoAttuale, String condizione, String id_venditore){
        asta_alribassoRepository.insert(nome, descrizione, path_immagine, prezzoBase, intervalloDecrementale, decrementoAutomaticoCifra, prezzoMin, prezzoAttuale, condizione, id_venditore);
    }
    @Override
    public Long getLastInsertedId(){
        return asta_alribassoRepository.getLastInsertedId();
    }
    @Override
    public ArrayList<Asta_alribasso> findByNomeAndCategorieAndCondizioneOrderByPrezzo(String nome, ArrayList<String> categorie, String ordinamento){
        return asta_alribassoRepository.findByNomeAndCategorieAndCondizioneOrderByPrezzo(nome, categorie,ordinamento);
    }
    @Override
    public ArrayList<Asta_alribasso> findByNomeAndCondizioneOrderByPrezzo(String nome, String ordinamento){
        return asta_alribassoRepository.findByNomeAndCondizioneOrderByPrezzo(nome,ordinamento);
    }
    @Override
    public ArrayList<Asta_alribasso> findByCategorieAndCondizioneOrderByPrezzo(ArrayList<String> categorie, String ordinamento){
        return asta_alribassoRepository.findByCategorieAndCondizioneOrderByPrezzo(categorie,ordinamento);
    }
    @Override
    public ArrayList<Asta_alribasso> findByCondizioneOrderByPrezzo(String ordinamento){
        return asta_alribassoRepository.findByCondizioneOrderByPrezzo(ordinamento);
    }
}
