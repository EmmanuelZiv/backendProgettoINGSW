package com.example.backendingsw.Controller;

import com.example.backendingsw.DTO.Asta_allinglese_DTO;
import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Service.Interfaces.I_Asta_allinglese_Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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


    @GetMapping("/getAste_allingleseApertaByEmail/{indirizzo_email}")
    public List<Asta_allinglese_DTO> getAsta_allingleseApertaByEmail(@PathVariable String indirizzo_email){
        List<Asta_allinglese> list_asta_allinglese = i_asta_allinglese_service.findAsta_allIngleseApertaByEmail(indirizzo_email);

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


    @GetMapping("/getAste_allingleseChiusaByEmail/{indirizzo_email}")
    public List<Asta_allinglese_DTO> getAsta_allingleseChiusaByEmail(@PathVariable String indirizzo_email){
        List<Asta_allinglese> list_asta_allinglese = i_asta_allinglese_service.findAsta_allIngleseChiusaByEmail(indirizzo_email);

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

    @PostMapping("/partecipaAstaInglese/{idAstaInglese}/{indirizzo_email}/{offerta}/{tempo_offerta}/{stato}")
    public Integer partecipaAstaInglese(@PathVariable Long idAstaInglese,@PathVariable String indirizzo_email,@PathVariable String offerta,@PathVariable String tempo_offerta,@PathVariable String stato){
        try {
            System.out.println("id : " + idAstaInglese + ", email: " + indirizzo_email + " ,offerta: " + offerta + " ,tempoofferta: " + tempo_offerta + " ,stato: " + stato);
            float offertaF = Float.parseFloat(offerta);
            Timestamp orario = Timestamp.valueOf(tempo_offerta);
            Integer numeroRitorno = i_asta_allinglese_service.partecipaAstaInglese(idAstaInglese, indirizzo_email, offertaF, orario, stato);
            return numeroRitorno;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    @GetMapping("/trovaAstaInglese/{idAstaInglese}")
    public Asta_allinglese_DTO findAsta_allingleseById(@PathVariable  Long idAstaInglese){
        try{
            Asta_allinglese astaRecuperata = i_asta_allinglese_service.findAsta_allingleseById(idAstaInglese);
            Asta_allinglese_DTO astaRecuperataDTO = convertAsta_allingleseDTO(astaRecuperata);
            return astaRecuperataDTO;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("/verificaAstaIngleseInPreferiti/{indirizzo_email}/{idAstaInglese}")
    public Integer verificaAstaIngleseInPreferiti(@PathVariable String indirizzo_email,@PathVariable Long idAstaInglese){
        try{
            Integer verifica = i_asta_allinglese_service.verificaAstaIngleseInPreferiti(indirizzo_email, idAstaInglese);
            System.out.println("valore di verifica" + verifica);
            return verifica;
        }catch (Exception e){
            System.out.println("eccezione in verifica preferiti");
            e.printStackTrace();
            return -1;
        }
    }
    @PostMapping("/inserimentoAstaInPreferiti/{idAstaInglese}/{indirizzo_email}")
    public Integer inserimentoAstaInPreferiti(@PathVariable Long idAstaInglese,@PathVariable String indirizzo_email){
        try{
            Integer inserimento = i_asta_allinglese_service.inserimentoAstaInPreferiti(idAstaInglese,indirizzo_email);
            System.out.println("valore di inserimento" + inserimento);
            return inserimento;
        }catch (Exception e){
            System.out.println("eccezione in inserimento preferiti");
            e.printStackTrace();
            return -1;
        }
    }
    @DeleteMapping("/eliminazioneAstaInPreferiti/{idAstaInglese}/{indirizzo_email}")
    public Integer eliminazioneAstaInPreferiti(@PathVariable Long idAstaInglese,@PathVariable String indirizzo_email){
        try{
            Integer rimozione = i_asta_allinglese_service.eliminazioneAstaInPreferiti(idAstaInglese,indirizzo_email);
            System.out.println("valore di rimozione" + rimozione);
            return rimozione;
        }catch (Exception e){
            System.out.println("eccezione in rimozione preferiti");
            e.printStackTrace();
            return -1;
        }
    }
    @GetMapping("/getAsteInglesePreferite/{indirizzo_email}")
    public ArrayList<Asta_allinglese_DTO> getAsteInglesePreferite(@PathVariable String indirizzo_email){
        try{
            ArrayList<Asta_allinglese> lista = i_asta_allinglese_service.getAsteInglesePreferite(indirizzo_email);
            if (!lista.isEmpty()) {
                ArrayList<Asta_allinglese_DTO> listAsteAllingleseDTO = new ArrayList<>();
                for (Asta_allinglese asta : lista) {
                    Asta_allinglese_DTO astaDTO = convertAsta_allingleseDTO(asta);
                    listAsteAllingleseDTO.add(astaDTO);
                }
                return listAsteAllingleseDTO;
            } else {
                System.out.println("Non sono state trovate aste all'inglese");
                return new ArrayList<>();
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
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
