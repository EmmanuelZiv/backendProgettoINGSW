package com.example.backendingsw.Service.Implementations;

import com.example.backendingsw.Model.Acquirente;
import com.example.backendingsw.Model.SocialAcquirente;
import com.example.backendingsw.Repository.SocialAcquirenteRepository;
import com.example.backendingsw.Service.Interfaces.I_Social_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("Impl_Social_Service")
public class Impl_Social_Service implements I_Social_Service {

    @Autowired // crea l'oggetto
    private SocialAcquirenteRepository socialAcquirenteRepository;
//    @Autowired
//    private SocialVenditoreRepository socialVenditoreRepository;

    @Override
    public List<SocialAcquirente> findSocialAcquirente(String email){
        System.out.println("email in impl_Socialacquirente_Service" + email);
        return socialAcquirenteRepository.findSocialAcquirente(email);
    }

    public Optional<SocialAcquirente> insertSocialAcquirente(String nome,String link,String indirizzo_email){
        System.out.println("impl_socialAcquirente_Service ha nome:"+nome +"link:"+ link + "email:"+ indirizzo_email);
        return socialAcquirenteRepository.insertSocialAcquirente(nome,link,indirizzo_email);
    }

    public void deleteSocialAcquirente(String nome, String link,String indrizzo_email){
        System.out.println("impl_socialAcquirente_Service ha nome:"+nome +"link:"+ link +"email:"+indrizzo_email);
       socialAcquirenteRepository.deleteSocialAcquirente(nome,link,indrizzo_email);
    }

    public void updateSocialAcquirente(String oldNome,String oldLink,String newNome,String newLink){
        System.out.println("impl_socialAcquirente_Service ha newNome:"+newNome+" newLink:"+newLink + " oldNome:"+oldNome+" oldLink:"+oldLink);
        socialAcquirenteRepository.updateSocialAcquirente(oldNome,oldLink,newNome,newLink);

    }


//    @Override
//    public List<SocialVenditore> findSocialVenditore(String email){
//        System.out.println("email in impl_Socialvenditore_Service" + email);
//        return socialVenditoreRepository.findSocialVenditore(email);
//
//    }


}
