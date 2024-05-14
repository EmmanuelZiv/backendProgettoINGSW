package com.example.backendingsw.Controller;

import com.example.backendingsw.DTO.SocialAcquirente_DTO;
import com.example.backendingsw.Model.SocialAcquirente;
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
@RequestMapping("/socialAcquirenteController")
public class SocialAcquirenteController {

    private static final Logger logger = LoggerFactory.getLogger(SocialAcquirenteController.class);

    @Autowired
    @Qualifier("Impl_Social_Service")
    private I_Social_Service i_social_service;

    @GetMapping("/findSocialAcquirente/{indirizzo_email}")
    public List<SocialAcquirente_DTO> findSocialAcquirente(@PathVariable String indirizzo_email){
        List<SocialAcquirente> socialAcquirenteList= i_social_service.findSocialAcquirente(indirizzo_email);
        List<SocialAcquirente_DTO> socialAcquirenteDTOList=new ArrayList<>();
        if(socialAcquirenteList==null){
            return socialAcquirenteDTOList;
        }
        //lista vuota
        if(!socialAcquirenteList.isEmpty()) {


            for(SocialAcquirente socialAcquirente:socialAcquirenteList){
                SocialAcquirente_DTO socialAcquirenteDTO=convertSocialAcquirenteDto(socialAcquirente);
                socialAcquirenteDTOList.add(socialAcquirenteDTO);
            }
        }
        return socialAcquirenteDTOList;
    }

    @PostMapping("/insertSocialAcquirenteRegistrazione")
        public void insertSocialAcquirenteRegistrazione(@RequestBody ArrayList<SocialAcquirente_DTO> listaSocialDTO){
            try{
                for(SocialAcquirente_DTO socialDTO:listaSocialDTO){
                    SocialAcquirente social =convertSocialAcquirenteEntity(socialDTO);
                    Optional<SocialAcquirente> socialInserito=i_social_service.insertSocialAcquirente(social.getNome(), social.getLink(), social.getAcquirente().getIndirizzo_emailAcquirente());

                }
            }catch (Exception e){
                logger.error("Error during insertSocialAcquirenteRegistrazione", e);
            }
        }


        @PostMapping("/insertSocialAcquirente/{nome}/{link}/{indirizzo_email}")
        public SocialAcquirente_DTO insertSocialAcquirente(@PathVariable String nome,@PathVariable String link,@PathVariable String indirizzo_email){

        try {
            Optional<SocialAcquirente> socialInserito=i_social_service.insertSocialAcquirente(nome, link, indirizzo_email);
            if (socialInserito.isPresent()) {
                SocialAcquirente_DTO socialAcquirente_dto= convertSocialAcquirenteDto(socialInserito.get());
                return socialAcquirente_dto;
            }
        }catch (Exception e){
            logger.error("Error during insertSocialAcquirente", e);
            }
        return null;

        }


     @DeleteMapping("/deleteSocialAcquirente/{nome}/{link}/{indirizzo_email}")
    public void deleteSocialAcquirente(@PathVariable String nome, @PathVariable String link,@PathVariable String indirizzo_email) {
        try{
           i_social_service.deleteSocialAcquirente(nome,link,indirizzo_email);
        }
        catch (Exception e){
            logger.error("Error during deleteSocialAcquirente", e);
        }

    }

    @PutMapping("/updateSocialAcquirente/{oldNome}/{oldLink}/{newNome}/{newLink}")
    public void updateSocialAcquirente(@PathVariable String oldNome, @PathVariable String oldLink,@PathVariable String newNome, @PathVariable String newLink) {
        try {
            i_social_service.updateSocialAcquirente(oldNome, oldLink, newNome, newLink);
        } catch (Exception e) {
            logger.error("Error during updateSocialAcquirente", e);
        }
    }






    @Autowired
    private ModelMapper modelMapper;

    private SocialAcquirente convertSocialAcquirenteEntity(SocialAcquirente_DTO socialAcquirente_dto){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        SocialAcquirente socialAcquirente = new SocialAcquirente();
        socialAcquirente = modelMapper.map(socialAcquirente_dto, SocialAcquirente.class);

        return socialAcquirente;
    }
    private SocialAcquirente_DTO convertSocialAcquirenteDto(SocialAcquirente socialAcquirente){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        SocialAcquirente_DTO socialAcquirente_dto = new SocialAcquirente_DTO();
        socialAcquirente_dto = modelMapper.map(socialAcquirente, SocialAcquirente_DTO.class);
        socialAcquirente_dto.setIndirizzo_email(socialAcquirente.getAcquirente().getIndirizzo_emailAcquirente());//?

        return socialAcquirente_dto;
    }




}
