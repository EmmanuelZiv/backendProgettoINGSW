package com.example.backendingsw.Controller;


import com.example.backendingsw.DTO.*;
import com.example.backendingsw.Model.*;
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
                System.out.println("dati acquirente :"+ " nome:"+acquirente.get().getNome() + " cognome:"+ acquirente.get().getCognome() + " link:"+ acquirente.get().getLink() + " areageografica" + acquirente.get().getAreageografica());
                Acquirente_DTO acquirente_dto = convertAcquirenteDto(acquirente.get());
                System.out.println("dati acquirente dto:"+ " nome:"+acquirente_dto.getNome() + " cognome:"+ acquirente_dto.getCognome() + " link:"+ acquirente_dto.getLink() + " areageografica"+ acquirente_dto.getAreageografica());
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
                System.out.println("dati venditore :"+ " nome:"+venditore.get().getNome() + " cognome:"+ venditore.get().getCognome() + " link:"+ venditore.get().getLink() + " areageografica" + venditore.get().getAreageografica());
                Venditore_DTO venditore_dto = convertVenditoreDto(venditore.get());
                System.out.println("dati venditore dto:"+ " nome:"+venditore_dto.getNome() + " cognome:"+ venditore_dto.getCognome() + " link:"+ venditore_dto.getLink() + " areageografica" + venditore_dto.getAreageografica());
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




    @PutMapping("/updateAcquirente/{nome}/{cognome}/{bio}/{link}/{areageografica}/{indirizzo_email}")
    public void updateAcquirente(@PathVariable String nome, @PathVariable String cognome,
                                       @PathVariable String bio, @PathVariable String link,@PathVariable String areageografica,@PathVariable String indirizzo_email) {
        System.out.println("Aggiornamento  Acquirente con nome: " + nome + "cognome:"+cognome);
        try {
            i_utente_service.updateAcquirente(nome,cognome,bio,link,areageografica,indirizzo_email);
            System.out.println("Acquirente aggiornato con successo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/updatePasswordAcquirente/{password}/{indirizzo_email}")
    public void updatePasswordAcquirente(@PathVariable String password,@PathVariable String indirizzo_email) {
        System.out.println("Aggiornamento  Acquirente con password: " + password + " email:"+indirizzo_email);
        try {
            i_utente_service.updatePasswordAcquirente(password,indirizzo_email);
            System.out.println("Password Acquirente aggiornata con successo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/updateVenditore/{nome}/{cognome}/{bio}/{link}/{areageografica}/{indirizzo_email}")
    public void updateVenditore(@PathVariable String nome, @PathVariable String cognome,
                                 @PathVariable String bio, @PathVariable String link,@PathVariable String areageografica,@PathVariable String indirizzo_email) {
        System.out.println("Aggiornamento  Venditore con nome: " + nome + "cognome:"+cognome);
        try {
            i_utente_service.updateVenditore(nome,cognome,bio,link,areageografica,indirizzo_email);
            System.out.println("Venditore aggiornato con successo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/updatePasswordVenditore/{password}/{indirizzo_email}")
    public void updatePasswordVenditore(@PathVariable String password,@PathVariable String indirizzo_email) {
        System.out.println("Aggiornamento  Venditore con password: " + password + " email:"+indirizzo_email);
        try {
            i_utente_service.updatePasswordVenditore(password,indirizzo_email);
            System.out.println("Password Venditore aggiornata con successo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    // GET: Ottieni tutti gli acquirenti
    //auto creati da springboot, non usano le interfacce e non so se sia corretto
//    @GetMapping
//    public List<Acquirente> getAllAcquirenti() {
//        return utenteRepository.findAll();
//    }
//
//    // POST: Aggiungi un nuovo acquirente
//    @PostMapping
//    public Acquirente createAcquirente(@RequestBody Acquirente acquirente) {
//        return utenteRepository.save(acquirente);
//    }





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


    @GetMapping("/registrazioneAcquirenteDoppio/{indirizzo_email}")
    public Acquirente_DTO ricerca_inAcquirente(@PathVariable String indirizzo_email) throws ResponseStatusException{
        System.out.println("ricerac acquirente con mail" + indirizzo_email );
        try {
            Optional<Acquirente> acquirente = i_utente_service.ricercaAcquirente(indirizzo_email);

            if (acquirente.isPresent()) {
                System.out.println("acquirente è presente");
                Acquirente_DTO acquirente_dto = convertAcquirenteDto(acquirente.get());
                return acquirente_dto;
            }
            System.out.println("acquirente non è presente");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
        //else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
    }

    @GetMapping("/registrazioneVenditoreDoppio/{indirizzo_email}/")
    public Venditore_DTO ricerca_inVenditore(@PathVariable String indirizzo_email) throws ResponseStatusException{
        System.out.println("ricerca venditore con mail :" + indirizzo_email );

        try {
            Optional<Venditore> venditore = i_utente_service.ricercaVenditore(indirizzo_email);
            System.out.println("venditore no!");
            if(venditore.isPresent()) {
                System.out.println("venditore è presente");
                Venditore_DTO venditore_dto = convertVenditoreDto(venditore.get());
                return venditore_dto;
            }
            System.out.println("venditore non è presente");
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    @PostMapping("/insertVenditore/{venditore}")
    public Long InsertVenditore(@RequestBody Venditore_DTO venditore_dto){
        System.out.println("entrati in insertVenditore");
        try{
            i_utente_service.insertVenditore(venditore_dto.getNome(), venditore_dto.getCognome(),venditore_dto.getIndirizzo_email(),venditore_dto.getPassword(),venditore_dto.getBio(),venditore_dto.getAreageografica(),venditore_dto.getLink());
            return 1L;
        }catch (Exception e){
            System.out.println("eccezione in inserimento venditore");
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/insertAcquirente/{acquirente}")
    public Long InsertAcquirente(@RequestBody Acquirente_DTO acquirente_dto){
        System.out.println("entrati in insertAcquirente");
        try{
            i_utente_service.insertAcquirente(acquirente_dto.getNome(), acquirente_dto.getCognome(),acquirente_dto.getIndirizzo_email(),acquirente_dto.getPassword(),acquirente_dto.getBio(),acquirente_dto.getAreageografica(),acquirente_dto.getLink());
            return 1L;
        }catch (Exception e){
            System.out.println("eccezione in inserimento acquirente");
            e.printStackTrace();
            return null;
        }
    }
    @PostMapping("utenteController/saveCategorieAcquirente/{email}/{lista_categorie}")
    public void saveCategorieAcquirente(@PathVariable String indirizzo_email,@RequestParam(value = "lista_categorie", required = false) ArrayList<String> lista_categorie){
        System.out.println("entrati salva categoria acquirente");
        try{
                for(String categoria:lista_categorie) {
                    i_utente_service.insertCategorieAcquirente(indirizzo_email, categoria);
                    System.out.println("inserita categoria " + categoria );
                }
            return ;
        }catch (Exception e){
            System.out.println("eccezione ");
            e.printStackTrace();
        }
    }

    @PostMapping("utenteController/saveCategorieVenditore/{email}/{lista_categorie}")
    public void saveCategorieVenditore(@PathVariable String indirizzo_email,@RequestParam(value = "lista_categorie", required = false) ArrayList<String> lista_categorie){
        System.out.println("entrati salva categoria Venditore");
        try{
            for(String categoria:lista_categorie) {
                i_utente_service.insertCategorieVenditore(indirizzo_email, categoria);
                System.out.println("inserita categoria " + categoria );
            }
            return ;
        }catch (Exception e){
            System.out.println("eccezione ");
            e.printStackTrace();
        }
    }
}
