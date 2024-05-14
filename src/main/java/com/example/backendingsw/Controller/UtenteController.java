package com.example.backendingsw.Controller;


import com.example.backendingsw.DTO.*;
import com.example.backendingsw.Model.*;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.backendingsw.Repository.UtenteRepository;
import org.modelmapper.ModelMapper;
import com.example.backendingsw.Service.Interfaces.I_Utente_Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/utenteController")
public class UtenteController {

    private static final Logger logger = LoggerFactory.getLogger(UtenteController.class);

    @Autowired
    @Qualifier("Impl_Utente_Service")
    private I_Utente_Service i_utente_service;
    @Autowired
    private UtenteRepository utenteRepository;

    @GetMapping("/loginAcquirente/{indirizzo_email}/{password}")
    public Acquirente_DTO log_inAcquirente(@PathVariable String indirizzo_email, @PathVariable String password) throws ResponseStatusException{
        try {
            Optional<Acquirente> acquirente = i_utente_service.loginAcquirente(indirizzo_email, password);

            if (acquirente.isPresent()) {
                Acquirente_DTO acquirente_dto = convertAcquirenteDto(acquirente.get());
                return acquirente_dto;
            }
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
        }
        return null;
    }
    @GetMapping("/findCategorieByIndirizzoEmailAcquirente/{indirizzo_email}")
    public ArrayList<String> findCategorieByIndirizzoEmailAcquirente(@PathVariable String indirizzo_email) {
        ArrayList<String> listaCategorie = i_utente_service.findCategorieByIndirizzoEmailAcquirente(indirizzo_email);
        return listaCategorie;
    }


    @GetMapping("/loginVenditore/{indirizzo_email}/{password}")
    public Venditore_DTO log_inVenditore(@PathVariable String indirizzo_email, @PathVariable String password) throws ResponseStatusException{
        try {
            Optional<Venditore> venditore = i_utente_service.loginVenditore(indirizzo_email, password);
            if(venditore.isPresent()) {
                Venditore_DTO venditore_dto = convertVenditoreDto(venditore.get());
                return venditore_dto;
            }
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
        }

        return null;

    }
    @GetMapping("/findCategorieByIndirizzoEmailVenditore/{indirizzo_email}")
    public ArrayList<String> findCategorieByIndirizzoEmailVenditore(@PathVariable String indirizzo_email) {
        ArrayList<String> listaCategorie = i_utente_service.findCategorieByIndirizzoEmailVenditore(indirizzo_email);
        return listaCategorie;
    }


    @PutMapping("/updateAcquirente")
    public void updateAcquirente(@RequestBody Acquirente_DTO acquirente_dto) {
        try {
            i_utente_service.updateAcquirente(acquirente_dto.getNome(),acquirente_dto.getCognome(), acquirente_dto.getBio(), acquirente_dto.getLink(), acquirente_dto.getAreageografica(), acquirente_dto.getIndirizzo_email());
        } catch (Exception e) {
            logger.error("Error during updateAcquirente", e);
        }
    }

    @PutMapping("/updatePasswordAcquirente/{password}/{indirizzo_email}")
    public void updatePasswordAcquirente(@PathVariable String password,@PathVariable String indirizzo_email) {
        try {
            i_utente_service.updatePasswordAcquirente(password,indirizzo_email);
        } catch (Exception e) {
            logger.error("Error during updatePasswordAcquirente", e);
        }
    }

    @PutMapping("/updateVenditore")
    public void updateVenditore(@RequestBody Venditore_DTO venditore_dto) {
        try {
            i_utente_service.updateVenditore(venditore_dto.getNome(), venditore_dto.getCognome(), venditore_dto.getBio(), venditore_dto.getLink(), venditore_dto.getAreageografica(), venditore_dto.getIndirizzo_email());
        } catch (Exception e) {
            logger.error("Error during updateVenditore", e);
        }
    }

    @PutMapping("/updatePasswordVenditore/{password}/{indirizzo_email}")
    public void updatePasswordVenditore(@PathVariable String password,@PathVariable String indirizzo_email) {
        try {
            i_utente_service.updatePasswordVenditore(password,indirizzo_email);
        } catch (Exception e) {
            logger.error("Error during updatePasswordVenditore", e);
        }
    }


    @PutMapping("/setTokenAcquirente/{indirizzo_email}/{token}")
    public int setTokenAcquirente(@PathVariable String indirizzo_email, @PathVariable String token){
        try{
            int valore = utenteRepository.createAndInsertTokenAcquirente(token,indirizzo_email);
            if (valore > 0) {
                return valore;
            } else {
                return -1;
            }
        }catch (Exception e){
            logger.error("Error during setTokenAcquirente", e);
            return -1;
        }
    }

    @PutMapping("/removeTokenFromAcquirente/{indirizzo_email}")
    public int removeTokenFromAcquirente(@PathVariable String indirizzo_email){
        try{
            int valore = utenteRepository.removeTokenFromAcquirente(indirizzo_email);
            if (valore > 0) {
                return valore;
            } else {
                return -1;
            }
        }catch (Exception e){
            logger.error("Error during removeTokenFromAcquirente", e);
            return -1;
        }
    }
    @PutMapping("/setTokenVenditore/{indirizzo_email}/{token}")
    public int setTokenVenditore(@PathVariable String indirizzo_email, @PathVariable String token){
        try{
            int valore = utenteRepository.createAndInsertTokenVenditore(token, indirizzo_email);
            return valore;
        }catch (Exception e){
            logger.error("Error during setTokenVenditore", e);
            return -1;
        }
    }

    @PutMapping("/removeTokenFromVenditore/{indirizzo_email}")
    public int removeTokenFromVenditore(@PathVariable String indirizzo_email){
        try{
            int valore = utenteRepository.removeTokenFromVenditore( indirizzo_email);
            if (valore > 0) {
                return valore;
            } else {
                return -1;
            }
        }catch (Exception e){
            logger.error("Error during removeTokenFromVenditore", e);
            return -1;
        }
    }

    @GetMapping("/loginAcquirenteConToken/{token}")
    public Acquirente_DTO loginAcquirenteConToken(@PathVariable String token) throws ResponseStatusException{
        try {
            Optional<Acquirente> acquirente = i_utente_service.loginAcquirenteConToken(token);
            if (acquirente.isPresent()) {
                Acquirente_DTO acquirente_dto = convertAcquirenteDto(acquirente.get());
                return acquirente_dto;
            }
        }catch (Exception e){
            logger.error("Error during loginAcquirenteConToken", e);
        }
        return null;
    }

    @GetMapping("/loginVenditoreConToken/{token}")
    public Venditore_DTO loginVenditoreConToken(@PathVariable String token) throws ResponseStatusException{
        try {
            Optional<Venditore> venditore = i_utente_service.loginVenditoreConToken(token);
            if(venditore.isPresent()) {
                Venditore_DTO venditore_dto = convertVenditoreDto(venditore.get());
                return venditore_dto;
            }
        }catch (Exception e){
            logger.error("Error during loginVenditoreConToken", e);
        }

        return null;

    }

    @GetMapping("/getAcquirenteByIndirizzo_email/{indirizzo_email}")
    public Acquirente_DTO getAcquirenteByIndirizzo_email(@PathVariable String indirizzo_email) throws ResponseStatusException{
        try {
            Acquirente acquirente = i_utente_service.getAcquirenteByIndirizzo_email(indirizzo_email);

            if (acquirente != null) {
                Acquirente_DTO acquirente_dto = convertAcquirenteDto(acquirente);
                return acquirente_dto;
            }
        }catch (Exception e){
            logger.error("Error during getAcquirenteByIndirizzoEmail", e);
        }
        return null;
    }
    @GetMapping("/getVenditoreByIndirizzo_email/{indirizzo_email}")
    public Venditore_DTO getVenditoreByIndirizzo_email(@PathVariable String indirizzo_email) throws ResponseStatusException{
        try {
            Venditore venditore = i_utente_service.getVenditoreByIndirizzo_email(indirizzo_email);
            if(venditore!=null) {
                Venditore_DTO venditore_dto = convertVenditoreDto(venditore);
                return venditore_dto;
            }
        }catch (Exception e){
            logger.error("Error during getVenditoreByIndirizzoEmail", e);
        }

        return null;

    }

    @Autowired
    private ModelMapper modelMapper;

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



    @GetMapping("/registrazioneAcquirenteDoppio/{indirizzo_email}")
    public Acquirente_DTO ricerca_inAcquirente(@PathVariable String indirizzo_email) throws ResponseStatusException{
        try {
            Optional<Acquirente> acquirente = i_utente_service.ricercaAcquirente(indirizzo_email);

            if (acquirente.isPresent()) {
                Acquirente_DTO acquirente_dto = convertAcquirenteDto(acquirente.get());
                return acquirente_dto;
            }
        }catch (Exception e){
            logger.error("Error during ricercaInAcquirente", e);
        }
        return null;
    }

    @GetMapping("/registrazioneVenditoreDoppio/{indirizzo_email}/")
    public Venditore_DTO ricerca_inVenditore(@PathVariable String indirizzo_email) throws ResponseStatusException{
        try {
            Optional<Venditore> venditore = i_utente_service.ricercaVenditore(indirizzo_email);
            if(venditore.isPresent()) {
                Venditore_DTO venditore_dto = convertVenditoreDto(venditore.get());
                return venditore_dto;
            }
        }catch (Exception e){
            logger.error("Error during ricercaInVenditore", e);
        }

        return null;

    }

    @PostMapping("/insertVenditore")
    public Long InsertVenditore(@RequestBody Venditore_DTO venditore_dto){
        try{
            i_utente_service.insertVenditore(venditore_dto.getNome(), venditore_dto.getCognome(),venditore_dto.getIndirizzo_email(),venditore_dto.getPassword(),venditore_dto.getBio(),venditore_dto.getLink(),venditore_dto.getAreageografica());
            return 1L;
        }catch (Exception e){
            logger.error("Error during insertVenditore", e);
            return null;
        }
    }

    @PostMapping("/insertAcquirente")
    public Long InsertAcquirente(@RequestBody Acquirente_DTO acquirente_dto){
        try{
            i_utente_service.insertAcquirente(acquirente_dto.getNome(), acquirente_dto.getCognome(),acquirente_dto.getIndirizzo_email(),acquirente_dto.getPassword(),acquirente_dto.getBio(),acquirente_dto.getLink(),acquirente_dto.getAreageografica());
            return 1L;
        }catch (Exception e){
            logger.error("Error during insertAcquirente", e);
            return null;
        }
    }
    @PostMapping("/saveCategorieAcquirente/{indirizzo_email}")
    public void saveCategorieAcquirente(@PathVariable String indirizzo_email,@RequestParam(value = "lista_categorie", required = false) ArrayList<String> lista_categorie){
        try{
            for(String categoria:lista_categorie) {
                i_utente_service.insertCategorieAcquirente(indirizzo_email, categoria);
            }
        }catch (Exception e){
            logger.error("Error during saveCategorieAcquirente", e);
        }
    }

    @PostMapping("/saveCategorieVenditore/{indirizzo_email}")
    public void saveCategorieVenditore(@PathVariable String indirizzo_email,@RequestParam(value = "lista_categorie", required = false) ArrayList<String> lista_categorie){
        try{
            for(String categoria:lista_categorie) {
                i_utente_service.insertCategorieVenditore(indirizzo_email, categoria);
            }
        }catch (Exception e){
            logger.error("Error during saveCategorieVenditore", e);
        }
    }
}
