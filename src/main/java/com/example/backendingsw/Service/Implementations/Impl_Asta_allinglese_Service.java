package com.example.backendingsw.Service.Implementations;

import com.example.backendingsw.Model.Acquirente;
import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Repository.Asta_allingleseRepository;
import com.example.backendingsw.Repository.UtenteRepository;
import com.example.backendingsw.Service.Interfaces.I_Asta_allinglese_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("Impl_Asta_allinglese_Service")
public class Impl_Asta_allinglese_Service implements I_Asta_allinglese_Service {

    @Autowired // crea l'oggetto
    private Asta_allingleseRepository asta_allingleseRepository;

    @Override
    public List<Asta_allinglese> findByCondizioneOrderByIntervalloTempoOfferteAsc(String condizione){
        return asta_allingleseRepository.findByCondizioneOrderByIntervalloTempoOfferteAsc("aperta");
    }
//    @Override
//    public List<Object> getAste_allingleseScadenzaRecente(){
//        return asta_allingleseRepository.getAste_allingleseScadenzaRecente();
//    }



}
