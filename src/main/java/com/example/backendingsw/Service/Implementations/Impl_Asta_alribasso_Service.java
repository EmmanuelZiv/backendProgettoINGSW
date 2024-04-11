package com.example.backendingsw.Service.Implementations;

import com.example.backendingsw.Model.Asta_alribasso;
import com.example.backendingsw.Repository.Asta_alribassoRepository;
import com.example.backendingsw.Service.Interfaces.I_Asta_alribasso_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
