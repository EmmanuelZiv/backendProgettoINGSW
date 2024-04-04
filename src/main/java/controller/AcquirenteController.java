package controller;


import model.Acquirente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.AcquirenteRepository;

import java.util.List;

@RestController
@RequestMapping("/api/acquirenti")
public class AcquirenteController {

    @Autowired
    private AcquirenteRepository acquirenteRepository;

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

    // Qui puoi aggiungere altri metodi per gestire richieste specifiche (es. GET by ID, DELETE, ecc.)
}
