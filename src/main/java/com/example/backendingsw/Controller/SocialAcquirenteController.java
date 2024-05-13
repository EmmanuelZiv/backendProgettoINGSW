package com.example.backendingsw.Controller;

import com.example.backendingsw.DTO.SocialAcquirente_DTO;
import com.example.backendingsw.Model.SocialAcquirente;
import com.example.backendingsw.Repository.SocialAcquirenteRepository;
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
    @Autowired
    private SocialAcquirenteRepository socialAcquirenteRepository;

    @GetMapping("/findSocialAcquirente/{indirizzo_email}")
    public List<SocialAcquirente_DTO> findSocialAcquirente(@PathVariable String indirizzo_email){
        System.out.println("find  Socialacquirente con mail :" + indirizzo_email);

        List<SocialAcquirente> socialAcquirenteList= i_social_service.findSocialAcquirente(indirizzo_email);
        List<SocialAcquirente_DTO> socialAcquirenteDTOList=new ArrayList<>();

        if(socialAcquirenteList==null){System.out.println("Lista social acquirente è null");return socialAcquirenteDTOList;}

        if(!socialAcquirenteList.isEmpty()) {


            for(SocialAcquirente socialAcquirente:socialAcquirenteList){
                //SocialAcquirente_DTO socialAcquirenteDTO=convertSocialAcquirenteToDTO(socialAcquirente);
                SocialAcquirente_DTO socialAcquirenteDTO=convertSocialAcquirenteDto(socialAcquirente);
                socialAcquirenteDTOList.add(socialAcquirenteDTO);
            }
            return socialAcquirenteDTOList;
        }else {
            System.out.println("lista social acquirente vuota con email:"+indirizzo_email);
            return socialAcquirenteDTOList;//lista vuota
            //else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
            }
        }

    @PostMapping("/insertSocialAcquirenteRegistrazione/{listaSocialDTO}")
        public void insertSocialAcquirenteRegistrazione(@RequestBody ArrayList<SocialAcquirente_DTO> listaSocialDTO){
            System.out.println("Siamo in insert Social acquirente registrazione nel backend " + listaSocialDTO.size());
            try{
                for(SocialAcquirente_DTO socialDTO:listaSocialDTO){
                    SocialAcquirente social =convertSocialAcquirenteEntity(socialDTO);
                    Optional<SocialAcquirente> socialInserito=i_social_service.insertSocialAcquirente(social.getNome(), social.getLink(), social.getAcquirente().getIndirizzo_emailAcquirente());
                    if (socialInserito.isPresent()) {
                        System.out.println("Social inserito è presente");

                    } else {
                        System.out.println("Social non inserito");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return ;
        }


        @PostMapping("/insertSocialAcquirente/{nome}/{link}/{indirizzo_email}")
        public SocialAcquirente_DTO insertSocialAcquirente(@PathVariable String nome,@PathVariable String link,@PathVariable String indirizzo_email){

        System.out.println("Siamo in insert Social acquirente nel backend con nome:"+nome+"link:"+link+"email:"+indirizzo_email);
        try {
            Optional<SocialAcquirente> socialInserito=i_social_service.insertSocialAcquirente(nome, link, indirizzo_email);
            if (socialInserito.isPresent()) {
                System.out.println("Social inserito è presente");
                SocialAcquirente_DTO socialAcquirente_dto= convertSocialAcquirenteDto(socialInserito.get());
                return socialAcquirente_dto;
            } else {
                System.out.println("Social non inserito");
            }
        }catch (Exception e){
                e.printStackTrace();
            }
        return null;

        }


     @DeleteMapping("/deleteSocialAcquirente/{nome}/{link}/{indirizzo_email}")
    public void deleteSocialAcquirente(@PathVariable String nome, @PathVariable String link,@PathVariable String indirizzo_email) {
        System.out.println("Cancellazione Social Acquirente con nome: " + nome + " e link: " + link);
        try{
           i_social_service.deleteSocialAcquirente(nome,link,indirizzo_email);
            System.out.println("Social cancellato");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @PutMapping("/updateSocialAcquirente/{oldNome}/{oldLink}/{newNome}/{newLink}")
    public void updateSocialAcquirente(@PathVariable String oldNome, @PathVariable String oldLink,
                                       @PathVariable String newNome, @PathVariable String newLink) {
        System.out.println("Aggiornamento Social Acquirente con nome: " + oldNome + ", link: " + oldLink +
                ", nuovi dati: " + newNome + ", " + newLink);
        try {
            i_social_service.updateSocialAcquirente(oldNome, oldLink, newNome, newLink);
            System.out.println("Social aggiornato con successo");
        } catch (Exception e) {
            e.printStackTrace();
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
