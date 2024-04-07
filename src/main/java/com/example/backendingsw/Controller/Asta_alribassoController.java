package com.example.backendingsw.Controller;

import com.example.backendingsw.DTO.Asta_allinglese_DTO;
import com.example.backendingsw.DTO.Asta_alribasso_DTO;
import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.Asta_alribasso;
import com.example.backendingsw.Service.Interfaces.I_Asta_allinglese_Service;
import com.example.backendingsw.Service.Interfaces.I_Asta_alribasso_Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
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
            System.out.println("Non sono state trovate aste all'inglese");
            return new ArrayList<>();
        }

        //else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
    }
    @Autowired
    private ModelMapper modelMapper;
    // Metodo per convertire un Asta_allinglese in un Asta_allinglese_DTO
    private Asta_alribasso_DTO convertAsta_alribassoDTO(Asta_alribasso asta_alribasso){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Asta_alribasso_DTO asta_alribasso_DTO = new Asta_alribasso_DTO();
        asta_alribasso_DTO = modelMapper.map(asta_alribasso, Asta_alribasso_DTO.class);
        return asta_alribasso_DTO;
    }
}
