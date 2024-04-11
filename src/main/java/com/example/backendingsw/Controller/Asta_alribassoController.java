package com.example.backendingsw.Controller;

import com.example.backendingsw.DTO.Asta_allinglese_DTO;
import com.example.backendingsw.DTO.Asta_alribasso_DTO;
import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.Asta_alribasso;
import com.example.backendingsw.Service.Interfaces.I_Asta_alribasso_Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/asta_alribassoController")
public class Asta_alribassoController {

    @Autowired
    @Qualifier("Impl_Asta_alribasso_Service")
    private I_Asta_alribasso_Service i_asta_alribasso_service;

    @GetMapping("/getAste_alRibassoNuove")
    public List<Asta_alribasso_DTO> getAste_alRibassoScadenzaRecente(){
        List<Asta_alribasso> list_asta_alribasso = i_asta_alribasso_service.findByCondizioneOrderByIdDesc("aperta");

        if (!list_asta_alribasso.isEmpty()) {
            List<Asta_alribasso_DTO> listAsteAlribassoDTO = new ArrayList<>();
            for (Asta_alribasso asta : list_asta_alribasso) {
                Asta_alribasso_DTO astaDTO = convertAsta_alribassoDTO(asta);
                listAsteAlribassoDTO.add(astaDTO);
            }
            return listAsteAlribassoDTO;
        } else {
            System.out.println("Non sono state trovate aste al ribasso");
            return new ArrayList<>();
        }

        //else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
    }

    @GetMapping("/getAste_alribassoNomeCategoria/{nomeCategoria}")
    public List<Asta_alribasso_DTO> getAste_alribassoNomeCategoria(@PathVariable String nomeCategoria){
        List<Asta_alribasso> list_asta_alribasso = i_asta_alribasso_service.findByCategorieNomeAndCondizioneAperta(nomeCategoria);

        if (!list_asta_alribasso.isEmpty()) {
            List<Asta_alribasso_DTO> listAsteAlribassoDTO = new ArrayList<>();
            for (Asta_alribasso asta : list_asta_alribasso) {
                Asta_alribasso_DTO astaDTO = convertAsta_alribassoDTO(asta);
                listAsteAlribassoDTO.add(astaDTO);
            }
            return listAsteAlribassoDTO;
        } else {
            System.out.println("Non sono state trovate aste al ribasso");
            return new ArrayList<>();
        }

        //else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
    }

    @GetMapping("/acquistaAstaAlRibasso/{idAstaAlRibasso}/{indirizzo_email}/{prezzoAcquisto}")
    public int acquistaAstaAlRibasso(@PathVariable Long idAstaAlRibasso, @PathVariable String indirizzo_email, @PathVariable String prezzoAcquisto){
        try {
            float prezzo = Float.parseFloat(prezzoAcquisto) ;
            int numeroRitorno = i_asta_alribasso_service.acquistaAstaAlRibasso(idAstaAlRibasso, indirizzo_email, prezzo);
            return numeroRitorno;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @GetMapping("/trovaAstaRibasso/{idAstaRibasso}")
    public Asta_alribasso_DTO findAsta_alribassoById(@PathVariable Long idAstaRibasso){
        try{
            Asta_alribasso astaRecuperata = i_asta_alribasso_service.findAsta_alribassoById(idAstaRibasso);
            Asta_alribasso_DTO astaRecuperataDTO = convertAsta_alribassoDTO(astaRecuperata);
            return astaRecuperataDTO;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Autowired
    private ModelMapper modelMapper;
    private Asta_alribasso_DTO convertAsta_alribassoDTO(Asta_alribasso asta_alribasso){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Asta_alribasso_DTO asta_alribasso_DTO = new Asta_alribasso_DTO();
        asta_alribasso_DTO = modelMapper.map(asta_alribasso, Asta_alribasso_DTO.class);
        return asta_alribasso_DTO;
    }
}
