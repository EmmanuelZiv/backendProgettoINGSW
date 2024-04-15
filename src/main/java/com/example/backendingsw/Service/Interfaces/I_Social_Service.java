package com.example.backendingsw.Service.Interfaces;

import com.example.backendingsw.Model.Acquirente;
import com.example.backendingsw.Model.SocialAcquirente;
import com.example.backendingsw.Model.SocialVenditore;
import com.example.backendingsw.Model.Venditore;

import java.util.List;
import java.util.Optional;

public interface I_Social_Service {
    public List<SocialAcquirente> findSocialAcquirente(String email);

    public Optional<SocialAcquirente> insertSocialAcquirente(String nome,String link,String indirizzo_email);

    public void deleteSocialAcquirente(String nome,String link,String indirizzo_email);

    public void updateSocialAcquirente(String oldNome,String oldLink,String newNome,String newLink);


    //versione venditore

    public List<SocialVenditore> findSocialVenditore(String email);

    public Optional<SocialVenditore> insertSocialVenditore(String nome,String link,String indirizzo_email);

    public void deleteSocialVenditore(String nome,String link,String indirizzo_email);

    public void updateSocialVenditore(String oldNome,String oldLink,String newNome,String newLink);


}
