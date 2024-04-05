package com.example.backendingsw.Controller;


import com.example.backendingsw.DTO.Acquirente_DTO;
import com.example.backendingsw.Model.Acquirente;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import com.example.backendingsw.Repository.AcquirenteRepository;
import org.modelmapper.ModelMapper;
import com.example.backendingsw.Service.Interfaces.I_Acquirente_Service;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acquirenteController")
public class AcquirenteController {

    @Autowired
    @Qualifier("Impl_Acquirente_Service")
    private I_Acquirente_Service i_acquirente_service;
    @Autowired
    private AcquirenteRepository acquirenteRepository;

    @GetMapping("/login/{indirizzo_email}/{password}")
    public Acquirente_DTO log_in(@PathVariable String indirizzo_email, @PathVariable String password){
        System.out.println("ok");

        Optional<Acquirente> acquirente= i_acquirente_service.login(indirizzo_email,password);

        if(acquirente.isPresent()) {
            Acquirente_DTO acquirente_dto =convertDto(acquirente.get());
            return acquirente_dto;
        }
        return null;
        //else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
    }

    // GET: Ottieni tutti gli acquirenti
    @GetMapping
    public List<Acquirente> getAllAcquirenti() {
        return acquirenteRepository.findAll();
    }

    // POST: Aggiungi un nuovo acquirente
    @PostMapping
    public Acquirente createAcquirente(@RequestBody Acquirente acquirente) {
        return acquirenteRepository.save(acquirente);
    }

    @Autowired
    private ModelMapper modelMapper;
    private Acquirente convertEntity(Acquirente_DTO acquirente_dto){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Acquirente acquirente = new Acquirente();
        acquirente = modelMapper.map(acquirente_dto, Acquirente.class);

        return acquirente;
    }
    private Acquirente_DTO convertDto(Acquirente acquirente){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Acquirente_DTO acquirente_dto = new Acquirente_DTO();
        acquirente_dto = modelMapper.map(acquirente, Acquirente_DTO.class);

        return acquirente_dto;
    }
}
