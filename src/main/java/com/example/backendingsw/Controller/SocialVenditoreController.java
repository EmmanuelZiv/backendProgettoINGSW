package com.example.backendingsw.Controller;

import com.example.backendingsw.DTO.SocialVenditore_DTO;
import com.example.backendingsw.Model.SocialVenditore;
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

    @GetMapping("/findSocialVenditore/{indirizzo_email}")
    public List<SocialVenditore_DTO> findSocialVenditore(@PathVariable String indirizzo_email){

        List<SocialVenditore> socialVenditoreList= i_social_service.findSocialVenditore(indirizzo_email);
        List<SocialVenditore_DTO> socialVenditoreDTOList=new ArrayList<>();

        if(socialVenditoreList==null){
            return socialVenditoreDTOList;
        }

        if(!socialVenditoreList.isEmpty()) {


            for(SocialVenditore socialVenditore:socialVenditoreList){
                //SocialAcquirente_DTO socialAcquirenteDTO=convertSocialAcquirenteToDTO(socialAcquirente);
                SocialVenditore_DTO socialVenditoreDTO=convertSocialVenditoreDto(socialVenditore);
                socialVenditoreDTOList.add(socialVenditoreDTO);
            }
            return socialVenditoreDTOList;
        }else {
            return socialVenditoreDTOList;//lista vuota
        }
    }





    @PostMapping("/insertSocialVenditore/{nome}/{link}/{indirizzo_email}")
    public SocialVenditore_DTO insertSocialVenditore(@PathVariable String nome,@PathVariable String link,@PathVariable String indirizzo_email){

        try {
            Optional<SocialVenditore> socialInserito=i_social_service.insertSocialVenditore(nome, link, indirizzo_email);
            if (socialInserito.isPresent()) {
                SocialVenditore_DTO socialVenditore_dto= convertSocialVenditoreDto(socialInserito.get());
                return socialVenditore_dto;
            }
        }catch (Exception e){
            logger.error("Error during insertSocialVenditore", e);
        }
        return null;

    }

    @PostMapping("/insertSocialVenditoreRegistrazione")
    public void insertSocialVenditoreRegistrazione(@RequestBody ArrayList<SocialVenditore_DTO> listaSocialDTO){
        try{
            for(SocialVenditore_DTO socialDTO:listaSocialDTO){
                SocialVenditore social =convertSocialVenditoreEntity(socialDTO);
                Optional<SocialVenditore> socialInserito=i_social_service.insertSocialVenditore(social.getNome(), social.getLink(), social.getVenditore().getIndirizzo_email());
            }
        }catch (Exception e){
            logger.error("Error during insertSocialVenditoreRegistrazione", e);
        }
    }
    @DeleteMapping("/deleteSocialVenditore/{nome}/{link}/{indirizzo_email}")
    public void deleteSocialVenditore(@PathVariable String nome, @PathVariable String link,@PathVariable String indirizzo_email) {
        try{
            i_social_service.deleteSocialVenditore(nome,link,indirizzo_email);
        }
        catch (Exception e){
            logger.error("Error during deleteSocialVenditore", e);
        }

    }

    @PutMapping("/updateSocialVenditore/{oldNome}/{oldLink}/{newNome}/{newLink}")
    public void updateSocialVenditore(@PathVariable String oldNome, @PathVariable String oldLink,@PathVariable String newNome, @PathVariable String newLink) {
        try {
            i_social_service.updateSocialVenditore(oldNome, oldLink, newNome, newLink);
        } catch (Exception e) {
            logger.error("Error during updateSocialVenditore", e);
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
        socialVenditore_dto.setIndirizzo_email(socialVenditore.getVenditore().getIndirizzo_email());//?

        return socialVenditore_dto;
    }




}
