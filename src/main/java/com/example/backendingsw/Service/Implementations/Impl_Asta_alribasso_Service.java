package com.example.backendingsw.Service.Implementations;

import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.Asta_alribasso;
import com.example.backendingsw.Repository.Asta_allingleseRepository;
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
}
