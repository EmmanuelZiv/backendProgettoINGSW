package com.example.backendingsw.Service.Implementations;

import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.Asta_inversa;
import com.example.backendingsw.Repository.Asta_inversaRepository;
import com.example.backendingsw.Service.Interfaces.I_Asta_inversa_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("Impl_Asta_inversa_Service")
public class Impl_Asta_inversa_Service implements I_Asta_inversa_Service {

    @Autowired // crea l'oggetto
    private Asta_inversaRepository asta_inversaRepository;

    @Override
    public List<Asta_inversa> findByCondizioneOrderByDataDiScadenzaAsc(String condizione){
        return asta_inversaRepository.findByCondizioneOrderByDataDiScadenzaAsc("aperta");
    }
    @Override
    public List<Asta_inversa> findByCondizioneOrderByIdDesc(String condizione){
        return asta_inversaRepository.findByCondizioneOrderByIdDesc("aperta");
    }
    @Override
    public List<Asta_inversa> findByCategorieNomeAndCondizioneAperta(String nomeCategoria){
        return asta_inversaRepository.findByCategorieNomeAndCondizioneAperta(nomeCategoria);
    }
}
