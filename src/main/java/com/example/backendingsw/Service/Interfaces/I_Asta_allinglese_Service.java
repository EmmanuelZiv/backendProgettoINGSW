package com.example.backendingsw.Service.Interfaces;

import com.example.backendingsw.Model.Asta_allinglese;

import java.util.List;
import java.util.Optional;

public interface I_Asta_allinglese_Service {
    public List<Asta_allinglese> findByCondizioneOrderByIntervalloTempoOfferteAsc(String condizione);
}
