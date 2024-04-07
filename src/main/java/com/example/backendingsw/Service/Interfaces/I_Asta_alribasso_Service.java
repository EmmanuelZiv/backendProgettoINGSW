package com.example.backendingsw.Service.Interfaces;

import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.Asta_alribasso;

import java.util.List;

public interface I_Asta_alribasso_Service {
    public List<Asta_alribasso> findByCondizioneOrderByIdDesc(String condizione);
}
