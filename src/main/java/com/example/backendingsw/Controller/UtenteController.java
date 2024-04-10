package com.example.backendingsw.Controller;


import com.example.backendingsw.DTO.Acquirente_DTO;
import com.example.backendingsw.DTO.Venditore_DTO;
import com.example.backendingsw.Model.Acquirente;
import com.example.backendingsw.Model.Venditore;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.backendingsw.Repository.UtenteRepository;
import org.modelmapper.ModelMapper;
import com.example.backendingsw.Service.Interfaces.I_Utente_Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/utenteController")
public class UtenteController {

    @Autowired
    @Qualifier("Impl_Utente_Service")
    private I_Utente_Service i_utente_service;
    @Autowired
    private UtenteRepository utenteRepository;

    @GetMapping("/loginAcquirente/{indirizzo_email}/{password}")
    public Acquirente_DTO log_inAcquirente(@PathVariable String indirizzo_email, @PathVariable String password) throws ResponseStatusException{
        System.out.println("login acquirente con mail e password:" + indirizzo_email + password);
        try {
            Optional<Acquirente> acquirente = i_utente_service.loginAcquirente(indirizzo_email, password);

            if (acquirente.isPresent()) {
                System.out.println("acquirente è presente");
                Acquirente_DTO acquirente_dto = convertAcquirenteDto(acquirente.get());
                return acquirente_dto;
            }
            System.out.println("acquirente non è presente");
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
        }
        return null;
        //else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
    }
    @GetMapping("/findCategorieByIndirizzoEmailAcquirente/{indirizzo_email}")
    public ArrayList<String> findCategorieByIndirizzoEmailAcquirente(@PathVariable String indirizzo_email) {
        ArrayList<String> listaCategorie = i_utente_service.findCategorieByIndirizzoEmailAcquirente(indirizzo_email);
            return listaCategorie;
    }


    @GetMapping("/loginVenditore/{indirizzo_email}/{password}")
    public Venditore_DTO log_inVenditore(@PathVariable String indirizzo_email, @PathVariable String password) throws ResponseStatusException{
        System.out.println("login venditore con mail e password:" + indirizzo_email + password);

        try {
            Optional<Venditore> venditore = i_utente_service.loginVenditore(indirizzo_email, password);
            System.out.println("venditore no!");
            if(venditore.isPresent()) {
                System.out.println("venditore è presente");
                Venditore_DTO venditore_dto = convertVenditoreDto(venditore.get());
                return venditore_dto;
            }
            System.out.println("venditore non è presente");
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
        }

        return null;

    }
    @GetMapping("/findCategorieByIndirizzoEmailVenditore/{indirizzo_email}")
    public ArrayList<String> findCategorieByIndirizzoEmailVenditore(@PathVariable String indirizzo_email) {
        ArrayList<String> listaCategorie = i_utente_service.findCategorieByIndirizzoEmailVenditore(indirizzo_email);
        return listaCategorie;
    }

    // POST: Aggiungi un nuovo acquirente
    @PostMapping
    public Acquirente createAcquirente(@RequestBody Acquirente acquirente) {
        return utenteRepository.save(acquirente);
    }





    @Autowired
    private ModelMapper modelMapper;
    private Acquirente convertAcquirenteEntity(Acquirente_DTO acquirente_dto){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Acquirente acquirente = new Acquirente();
        acquirente = modelMapper.map(acquirente_dto, Acquirente.class);

        return acquirente;
    }
    private Acquirente_DTO convertAcquirenteDto(Acquirente acquirente){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Acquirente_DTO acquirente_dto = new Acquirente_DTO();
        acquirente_dto = modelMapper.map(acquirente, Acquirente_DTO.class);

        return acquirente_dto;
    }
    // Metodo per convertire un Venditore in un Venditore_DTO
    private Venditore_DTO convertVenditoreDto(Venditore venditore){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Venditore_DTO venditore_dto = new Venditore_DTO();
        venditore_dto = modelMapper.map(venditore, Venditore_DTO.class);
        return venditore_dto;
    }
    // Metodo per convertire un Venditore_DTO in un Venditore
    private Venditore convertVenditoreEntity(Venditore_DTO venditore_dto){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Venditore venditore = new Venditore();
        venditore = modelMapper.map(venditore_dto, Venditore.class);
        return venditore;
    }
}