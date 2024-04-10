package com.example.backendingsw.Controller;

import com.example.backendingsw.DTO.Asta_allinglese_DTO;
import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Service.Interfaces.I_Asta_allinglese_Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/asta_allingleseController")
public class Asta_allingleseController {

    @Autowired
    @Qualifier("Impl_Asta_allinglese_Service")
    private I_Asta_allinglese_Service i_asta_allinglese_service;


    @GetMapping("/getAste_allingleseScadenzaRecente")
    public List<Asta_allinglese_DTO> getAste_allingleseScadenzaRecente(){
        List<Asta_allinglese> list_asta_allinglese = i_asta_allinglese_service.findByCondizioneOrderByIntervalloTempoOfferteAsc("aperta");

        if (!list_asta_allinglese.isEmpty()) {
            List<Asta_allinglese_DTO> listAsteAllingleseDTO = new ArrayList<>();
            for (Asta_allinglese asta : list_asta_allinglese) {
                Asta_allinglese_DTO astaDTO = convertAsta_allingleseDTO(asta);
                listAsteAllingleseDTO.add(astaDTO);
            }
            return listAsteAllingleseDTO;
        } else {
            System.out.println("Non sono state trovate aste all'inglese");
            return new ArrayList<>();
        }

        //else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
    }

    @GetMapping("/getAste_allingleseNuove")
    public List<Asta_allinglese_DTO> getAste_allingleseNuove(){
        List<Asta_allinglese> list_asta_allinglese = i_asta_allinglese_service.findByCondizioneOrderByIdDesc("aperta");

        if (!list_asta_allinglese.isEmpty()) {
            List<Asta_allinglese_DTO> listAsteAllingleseDTO = new ArrayList<>();
            for (Asta_allinglese asta : list_asta_allinglese) {
                Asta_allinglese_DTO astaDTO = convertAsta_allingleseDTO(asta);
                listAsteAllingleseDTO.add(astaDTO);
            }
            return listAsteAllingleseDTO;
        } else {
            System.out.println("Non sono state trovate aste all'inglese");
            return new ArrayList<>();
        }

        //else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
    }

    @GetMapping("/getAste_allingleseNomeCategoria/{nomeCategoria}")
    public List<Asta_allinglese_DTO> getAste_allingleseNomeCategoria(@PathVariable String nomeCategoria){
        List<Asta_allinglese> list_asta_allinglese = i_asta_allinglese_service.findByCategorieNomeAndCondizioneAperta(nomeCategoria);

        if (!list_asta_allinglese.isEmpty()) {
            List<Asta_allinglese_DTO> listAsteAllingleseDTO = new ArrayList<>();
            for (Asta_allinglese asta : list_asta_allinglese) {
                Asta_allinglese_DTO astaDTO = convertAsta_allingleseDTO(asta);
                listAsteAllingleseDTO.add(astaDTO);
            }
            return listAsteAllingleseDTO;
        } else {
            System.out.println("Non sono state trovate aste all'inglese");
            return new ArrayList<>();
        }

        //else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
    }

    @Autowired
    private ModelMapper modelMapper;
    // Metodo per convertire un Asta_allinglese in un Asta_allinglese_DTO
    private Asta_allinglese_DTO convertAsta_allingleseDTO(Asta_allinglese asta_allinglese){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Asta_allinglese_DTO asta_allinglese_DTO = new Asta_allinglese_DTO();
        asta_allinglese_DTO = modelMapper.map(asta_allinglese, Asta_allinglese_DTO.class);
        return asta_allinglese_DTO;
    }

}