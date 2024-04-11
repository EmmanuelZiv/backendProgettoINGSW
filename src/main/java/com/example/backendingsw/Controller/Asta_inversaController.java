package com.example.backendingsw.Controller;

import com.example.backendingsw.DTO.Asta_inversa_DTO;
import com.example.backendingsw.Model.Asta_inversa;
import com.example.backendingsw.Service.Interfaces.I_Asta_inversa_Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/asta_inversaController")
public class Asta_inversaController {

    @Autowired
    @Qualifier("Impl_Asta_inversa_Service")
    private I_Asta_inversa_Service i_asta_inversa_service;


    @GetMapping("/getAste_inversaScadenzaRecente")
    public List<Asta_inversa_DTO> getAste_inversaScadenzaRecente(){
        List<Asta_inversa> list_asta_inversa = i_asta_inversa_service.findByCondizioneOrderByDataDiScadenzaAsc("aperta");

        if (!list_asta_inversa.isEmpty()) {
            List<Asta_inversa_DTO> listAsteInversaDTO = new ArrayList<>();
            for (Asta_inversa asta : list_asta_inversa) {
                Asta_inversa_DTO astaDTO = convertAsta_inversaDTO(asta);
                listAsteInversaDTO.add(astaDTO);
            }
            return listAsteInversaDTO;
        } else {
            System.out.println("Non sono state trovate aste inversa");
            return new ArrayList<>();
        }

        //else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
    }
    @GetMapping("/getAste_inversaNuove")
    public List<Asta_inversa_DTO> getAste_inversaScadenzaNuove(){
        List<Asta_inversa> list_asta_inversa = i_asta_inversa_service.findByCondizioneOrderByIdDesc("aperta");

        if (!list_asta_inversa.isEmpty()) {
            List<Asta_inversa_DTO> listAsteInversaDTO = new ArrayList<>();
            for (Asta_inversa asta : list_asta_inversa) {
                Asta_inversa_DTO astaDTO = convertAsta_inversaDTO(asta);
                listAsteInversaDTO.add(astaDTO);
            }
            return listAsteInversaDTO;
        } else {
            System.out.println("Non sono state trovate aste inversa");
            return new ArrayList<>();
        }

        //else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
    }
    @GetMapping("/getAste_inversaNomeCategoria/{nomeCategoria}")
    public List<Asta_inversa_DTO> getAste_inversaNomeCategoria(@PathVariable String nomeCategoria){
        List<Asta_inversa> list_asta_inversa = i_asta_inversa_service.findByCategorieNomeAndCondizioneAperta(nomeCategoria);

        if (!list_asta_inversa.isEmpty()) {
            List<Asta_inversa_DTO> listAsteinversaDTO = new ArrayList<>();
            for (Asta_inversa asta : list_asta_inversa) {
                Asta_inversa_DTO astaDTO = convertAsta_inversaDTO(asta);
                listAsteinversaDTO.add(astaDTO);
            }
            return listAsteinversaDTO;
        } else {
            System.out.println("Non sono state trovate aste inverse");
            return new ArrayList<>();
        }

        //else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
    }
    @PostMapping("/partecipaAstaInversa/{idAstaInversa}/{indirizzo_email}/{offerta}/{tempo_offerta}/{stato}")
    public int partecipaAstaInversa(@PathVariable Long idAstaInversa,@PathVariable String indirizzo_email,@PathVariable String offerta,@PathVariable String tempo_offerta,@PathVariable String stato){
        try {
            System.out.println("id : " + idAstaInversa + ", email: " + indirizzo_email + " ,offerta: " + offerta + " ,tempoofferta: " + tempo_offerta + " ,stato: " + stato);
            float offertaF = Float.parseFloat(offerta);
            Timestamp orario = Timestamp.valueOf(tempo_offerta);
            int numeroRitorno = i_asta_inversa_service.partecipaAstaInversa(idAstaInversa, indirizzo_email, offertaF, orario, stato);
            return numeroRitorno;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    @GetMapping("/trovaAstaInversa/{idAstaInversa}")
    public Asta_inversa_DTO findAsta_inversaById(@PathVariable  Long idAstaInversa){
        try{
            Asta_inversa astaRecuperata = i_asta_inversa_service.findAsta_inversaById(idAstaInversa);
            Asta_inversa_DTO astaRecuperataDTO = convertAsta_inversaDTO(astaRecuperata);
            return astaRecuperataDTO;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/verificaAstaInversaInPreferiti/{indirizzo_email}/{idAstaInversa}")
    public Integer verificaAstaInversaInPreferiti(@PathVariable String indirizzo_email,@PathVariable Long idAstaInversa){
        try{
            Integer verifica = i_asta_inversa_service.verificaAstaInversaInPreferiti(indirizzo_email, idAstaInversa);
            System.out.println("valore di verifica" + verifica);
            return verifica;
        }catch (Exception e){
            System.out.println("eccezione in verifica preferiti");
            e.printStackTrace();
            return -1;
        }
    }
    @PostMapping("/inserimentoAstaInPreferiti/{idAstaInversa}/{indirizzo_email}")
    public Integer inserimentoAstaInPreferiti(@PathVariable Long idAstaInversa,@PathVariable String indirizzo_email){
        try{
            Integer inserimento = i_asta_inversa_service.inserimentoAstaInPreferiti(idAstaInversa,indirizzo_email);
            System.out.println("valore di inserimento" + inserimento);
            return inserimento;
        }catch (Exception e){
            System.out.println("eccezione in inserimento preferiti");
            e.printStackTrace();
            return -1;
        }
    }
    @DeleteMapping("/eliminazioneAstaInPreferiti/{idAstaInversa}/{indirizzo_email}")
    public Integer eliminazioneAstaInPreferiti(@PathVariable Long idAstaInversa,@PathVariable String indirizzo_email){
        try{
            Integer rimozione = i_asta_inversa_service.eliminazioneAstaInPreferiti(idAstaInversa,indirizzo_email);
            System.out.println("valore di rimozione" + rimozione);
            return rimozione;
        }catch (Exception e){
            System.out.println("eccezione in rimozione preferiti");
            e.printStackTrace();
            return -1;
        }
    }
    @GetMapping("/getAsteInversaPreferite/{indirizzo_email}")
    public ArrayList<Asta_inversa_DTO> getAste_inversaScadenzaRecente(@PathVariable String indirizzo_email){
        List<Asta_inversa> list_asta_inversa = i_asta_inversa_service.getAsteInversaPreferite(indirizzo_email);

        if (!list_asta_inversa.isEmpty()) {
            ArrayList<Asta_inversa_DTO> listAsteInversaDTO = new ArrayList<>();
            for (Asta_inversa asta : list_asta_inversa) {
                Asta_inversa_DTO astaDTO = convertAsta_inversaDTO(asta);
                listAsteInversaDTO.add(astaDTO);
            }
            return listAsteInversaDTO;
        } else {
            System.out.println("Non sono state trovate aste inversa");
            return new ArrayList<>();
        }

        //else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
    }
    @Autowired
    private ModelMapper modelMapper;
    private Asta_inversa_DTO convertAsta_inversaDTO(Asta_inversa asta_inversa){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Asta_inversa_DTO asta_inversa_DTO = new Asta_inversa_DTO();
        asta_inversa_DTO = modelMapper.map(asta_inversa, Asta_inversa_DTO.class);
        return asta_inversa_DTO;
    }

}
