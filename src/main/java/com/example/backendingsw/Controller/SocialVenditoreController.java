package com.example.backendingsw.Controller;

import com.example.backendingsw.DTO.SocialAcquirente_DTO;
import com.example.backendingsw.DTO.SocialVenditore_DTO;
import com.example.backendingsw.DTO.Venditore_DTO;
import com.example.backendingsw.Model.Acquirente;
import com.example.backendingsw.Model.SocialAcquirente;
import com.example.backendingsw.Model.SocialAcquirenteID;
import com.example.backendingsw.Model.SocialVenditore;
import com.example.backendingsw.Repository.SocialAcquirenteRepository;
import com.example.backendingsw.Repository.SocialVenditoreRepository;
import com.example.backendingsw.Service.Interfaces.I_Social_Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/socialVenditoreController")
public class SocialVenditoreController {

    private static final Logger logger = LoggerFactory.getLogger(SocialVenditoreController.class);

    @Autowired
    @Qualifier("Impl_Social_Service")
    private I_Social_Service i_social_service;
    @Autowired
    private SocialVenditoreRepository socialVenditoreRepository;

    @GetMapping("/findSocialVenditore/{indirizzo_email}")
    public List<SocialVenditore_DTO> findSocialVenditore(@PathVariable String indirizzo_email){
        System.out.println("find  SocialVenditore con mail :" + indirizzo_email);

        List<SocialVenditore> socialVenditoreList= i_social_service.findSocialVenditore(indirizzo_email);
        List<SocialVenditore_DTO> socialVenditoreDTOList=new ArrayList<>();

        if(socialVenditoreList==null){System.out.println("Lista social Venditore è null");return socialVenditoreDTOList;}

        if(!socialVenditoreList.isEmpty()) {


            for(SocialVenditore socialVenditore:socialVenditoreList){
                //SocialAcquirente_DTO socialAcquirenteDTO=convertSocialAcquirenteToDTO(socialAcquirente);
                SocialVenditore_DTO socialVenditoreDTO=convertSocialVenditoreDto(socialVenditore);
                socialVenditoreDTOList.add(socialVenditoreDTO);
            }
            return socialVenditoreDTOList;
        }else {
            System.out.println("lista social Venditore vuota con email:"+indirizzo_email);
            return socialVenditoreDTOList;//lista vuota
            //else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
        }
    }





    @PostMapping("/insertSocialVenditore/{nome}/{link}/{indirizzo_email}")
    public SocialVenditore_DTO insertSocialVenditore(@PathVariable String nome,@PathVariable String link,@PathVariable String indirizzo_email){

        System.out.println("Siamo in insert Social Venditore nel backend con nome:"+nome+"link:"+link+"email:"+indirizzo_email);
        try {
            Optional<SocialVenditore> socialInserito=i_social_service.insertSocialVenditore(nome, link, indirizzo_email);
            if (socialInserito.isPresent()) {
                System.out.println("Social inserito è presente");
                SocialVenditore_DTO socialVenditore_dto= convertSocialVenditoreDto(socialInserito.get());
                return socialVenditore_dto;
            } else {
                System.out.println("Social non inserito");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }


    @DeleteMapping("/deleteSocialVenditore/{nome}/{link}/{indirizzo_email}")
    public void deleteSocialVenditore(@PathVariable String nome, @PathVariable String link,@PathVariable String indirizzo_email) {
        System.out.println("Cancellazione Social Venditore con nome: " + nome + " e link: " + link);
        try{
            i_social_service.deleteSocialVenditore(nome,link,indirizzo_email);
            System.out.println("Social cancellato");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @PutMapping("/updateSocialVenditore/{oldNome}/{oldLink}/{newNome}/{newLink}")
    public void updateSocialVenditore(@PathVariable String oldNome, @PathVariable String oldLink,
                                       @PathVariable String newNome, @PathVariable String newLink) {
        System.out.println("Aggiornamento Social Venditore con nome: " + oldNome + ", link: " + oldLink +
                ", nuovi dati: " + newNome + ", " + newLink);
        try {
            i_social_service.updateSocialVenditore(oldNome, oldLink, newNome, newLink);
            System.out.println("Social aggiornato con successo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    @Autowired
    private ModelMapper modelMapper;

    private SocialVenditore convertSocialVenditoreEntity(SocialVenditore_DTO socialVenditore_dto){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        SocialVenditore socialVenditore = new SocialVenditore();
        socialVenditore = modelMapper.map(socialVenditore_dto, SocialVenditore.class);

        return socialVenditore;
    }
    private SocialVenditore_DTO convertSocialVenditoreDto(SocialVenditore socialVenditore){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        SocialVenditore_DTO socialVenditore_dto = new SocialVenditore_DTO();
        socialVenditore_dto = modelMapper.map(socialVenditore, SocialVenditore_DTO.class);
        socialVenditore_dto.setIndirizzo_email(socialVenditore.getVenditore().getEmail());//?

        return socialVenditore_dto;
    }




}
