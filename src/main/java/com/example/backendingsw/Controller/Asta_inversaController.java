package com.example.backendingsw.Controller;

import com.example.backendingsw.DTO.Asta_alribasso_DTO;
import com.example.backendingsw.DTO.Asta_inversa_DTO;
import com.example.backendingsw.Model.Asta_alribasso;
import com.example.backendingsw.Model.Asta_inversa;
import com.example.backendingsw.Service.Interfaces.I_Asta_inversa_Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;


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
                Asta_inversa_DTO astaDTO = convertiDaModelAaDto(asta);
                listAsteInversaDTO.add(astaDTO);
            }
            return listAsteInversaDTO;
        } else {
            return new ArrayList<>();
        }
    }
    @GetMapping("/getAste_inversaNuove")
    public List<Asta_inversa_DTO> getAste_inversaScadenzaNuove(){
        List<Asta_inversa> list_asta_inversa = i_asta_inversa_service.findByCondizioneOrderByIdDesc("aperta");

        if (!list_asta_inversa.isEmpty()) {
            List<Asta_inversa_DTO> listAsteInversaDTO = new ArrayList<>();
            for (Asta_inversa asta : list_asta_inversa) {
                Asta_inversa_DTO astaDTO = convertiDaModelAaDto(asta);
                listAsteInversaDTO.add(astaDTO);
            }
            return listAsteInversaDTO;
        } else {
            return new ArrayList<>();
        }
    }
    @GetMapping("/getAste_inversaNomeCategoria/{nomiCategorie}")
    public List<Asta_inversa_DTO> getAste_inversaNomeCategoria(@RequestParam("nomiCategorie") ArrayList<String> nomiCategorie){
        Set<Asta_inversa> asteUniche = new HashSet<>();

        for (String nomeCategoria : nomiCategorie) {
            List<Asta_inversa> astePerCategoria = i_asta_inversa_service.findByCategorieNomeAndCondizioneAperta(nomeCategoria);
            asteUniche.addAll(astePerCategoria);
        }

        List<Asta_inversa_DTO> listAsteInversaDTO = new ArrayList<>();
        for (Asta_inversa asta : asteUniche) {
            Asta_inversa_DTO astaDTO = convertiDaModelAaDto(asta);
            listAsteInversaDTO.add(astaDTO);
        }

        return listAsteInversaDTO;


    }


    @GetMapping("/getAste_inversaApertaByEmail/{indirizzo_email}")
    public List<Asta_inversa_DTO> getAsta_inversaApertaByEmail(@PathVariable String indirizzo_email){
        List<Asta_inversa> list_asta_inversa = i_asta_inversa_service.findAsta_inversaApertaByEmail(indirizzo_email);

        if (!list_asta_inversa.isEmpty()) {
            List<Asta_inversa_DTO> listAsteinversaDTO = new ArrayList<>();
            for (Asta_inversa asta : list_asta_inversa) {
                Asta_inversa_DTO astaDTO = convertiDaModelAaDto(asta);
                listAsteinversaDTO.add(astaDTO);
            }
            return listAsteinversaDTO;
        } else {
            return new ArrayList<>();
        }
    }


    @GetMapping("/getAste_inversaChiusaByEmail/{indirizzo_email}")
    public List<Asta_inversa_DTO> getAsta_inversaChiusaByEmail(@PathVariable String indirizzo_email){
        List<Asta_inversa> list_asta_inversa = i_asta_inversa_service.findAsta_inversaChiusaByEmail(indirizzo_email);

        if (!list_asta_inversa.isEmpty()) {
            List<Asta_inversa_DTO> listAsteinversaDTO = new ArrayList<>();
            for (Asta_inversa asta : list_asta_inversa) {
                Asta_inversa_DTO astaDTO = convertiDaModelAaDto(asta);
                listAsteinversaDTO.add(astaDTO);
            }
            return listAsteinversaDTO;
        } else {
            return new ArrayList<>();
        }
    }


    @PostMapping("/partecipaAstaInversa/{idAstaInversa}/{indirizzo_email}/{offerta}/{tempo_offerta}/{stato}")
    public int partecipaAstaInversa(@PathVariable Long idAstaInversa,@PathVariable String indirizzo_email,@PathVariable String offerta,@PathVariable String tempo_offerta,@PathVariable String stato){
        try {
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
            Asta_inversa_DTO astaRecuperataDTO =convertiDaModelAaDto(astaRecuperata);
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
            return verifica;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    @PostMapping("/inserimentoAstaInPreferiti/{idAstaInversa}/{indirizzo_email}")
    public Integer inserimentoAstaInPreferiti(@PathVariable Long idAstaInversa,@PathVariable String indirizzo_email){
        try{
            Integer inserimento = i_asta_inversa_service.inserimentoAstaInPreferiti(idAstaInversa,indirizzo_email);
            return inserimento;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    @DeleteMapping("/eliminazioneAstaInPreferiti/{idAstaInversa}/{indirizzo_email}")
    public Integer eliminazioneAstaInPreferiti(@PathVariable Long idAstaInversa,@PathVariable String indirizzo_email){
        try{
            Integer rimozione = i_asta_inversa_service.eliminazioneAstaInPreferiti(idAstaInversa,indirizzo_email);
            return rimozione;
        }catch (Exception e){
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
                Asta_inversa_DTO astaDTO = convertiDaModelAaDto(asta);
                listAsteInversaDTO.add(astaDTO);
            }
            return listAsteInversaDTO;
        } else {
            return new ArrayList<>();
        }

        //else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
    }


    @GetMapping("/getAsteInversaPartecipate/{indirizzo_email}")
    public ArrayList<Asta_inversa_DTO> getAste_inversaPartecipate(@PathVariable String indirizzo_email){
        List<Asta_inversa> list_asta_inversa = i_asta_inversa_service.getAsteInversePartecipate(indirizzo_email);

        if (!list_asta_inversa.isEmpty()) {
            ArrayList<Asta_inversa_DTO> listAsteInversaDTO = new ArrayList<>();
            for (Asta_inversa asta : list_asta_inversa) {
                Asta_inversa_DTO astaDTO = convertiDaModelAaDto(asta);
                listAsteInversaDTO.add(astaDTO);
            }
            return listAsteInversaDTO;
        } else {
            return new ArrayList<>();
        }

        //else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
    }

    @PostMapping("/insertAstaInversa/{asta_inversa}/{lista_categorie}")
    public Long insertAstaInversa(@RequestBody Asta_inversa_DTO asta_inversa_dto,@RequestParam(value = "lista_categorie", required = false) ArrayList<String> lista_categorie){
        try{
            //Asta_inversa asta = convertAsta_inversa(asta_inversa_dto);

            Asta_inversa asta = convertiDaDtoAModel(asta_inversa_dto);
            //Long id_asta = i_asta_inversa_service.insertAstaInversa(asta.getNome(),asta.getDescrizione(),asta.getPath_immagine(),asta.getPrezzoMax(),asta.getPrezzoAttuale(),asta.getDataDiScadenza(),asta.getCondizione(),asta.getId_acquirente());
            Asta_inversa astaInserita = i_asta_inversa_service.save(asta);
            Long id_asta = astaInserita.getId();
            if (lista_categorie != null && !lista_categorie.isEmpty()) {
                for(String categoria:lista_categorie) {
                    Integer value = i_asta_inversa_service.insertCategorieAstaInversa(id_asta, categoria);
                }
            }
            return id_asta;
        }catch (Exception e){
            e.printStackTrace();
            return 0L;
        }
    }
    @GetMapping("/getEmailVincente/{indirizzo_email}/{idAstaInversa}")
    public Boolean getEmailVincente(@PathVariable String indirizzo_email,@PathVariable Long idAstaInversa){
        System.out.println("entrato in getemail vincente con id e email: "+ idAstaInversa + ", " + indirizzo_email);
        try{
            String email_vincente = null;
            email_vincente = i_asta_inversa_service.getEmailVincente(idAstaInversa);
            if(email_vincente!=null){
                return email_vincente.equals(indirizzo_email);
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/getAstePerRicerca/{nome}/{ordinamento}/{nomiCategorie}")
    public ArrayList<Asta_inversa_DTO> getAstePerRicerca(@RequestParam(value = "nome", required = false) String nome, @RequestParam(value = "ordinamento") String ordinamento,@RequestParam(value = "nomiCategorie", required = false) ArrayList<String> nomiCategorie){
        ArrayList<Asta_inversa> asteTrovate = new ArrayList<>();
        if(nome!=null && !nome.isEmpty() && nomiCategorie != null && !nomiCategorie.isEmpty() && ordinamento != null && !ordinamento.isEmpty()){
            asteTrovate = i_asta_inversa_service.findByNomeAndCategorieAndCondizioneOrderByPrezzo(nome,nomiCategorie,ordinamento);
        }else if(nome!=null && !nome.isEmpty() && ordinamento != null && !ordinamento.isEmpty()){
            asteTrovate = i_asta_inversa_service.findByNomeAndCondizioneOrderByPrezzo(nome,ordinamento);
        }else if(nomiCategorie != null && !nomiCategorie.isEmpty() && ordinamento != null && !ordinamento.isEmpty()){
            asteTrovate = i_asta_inversa_service.findByCategorieAndCondizioneOrderByPrezzo(nomiCategorie,ordinamento);
        }else if(ordinamento != null && !ordinamento.isEmpty()){
            asteTrovate = i_asta_inversa_service.findByCondizioneOrderByPrezzo(ordinamento);
        }


        if(ordinamento!=null) {
            if (ordinamento.equals("ASC")) {
                Collections.sort(asteTrovate, Comparator.comparing(Asta_inversa::getPrezzoAttuale));
            } else {
                Collections.sort(asteTrovate, Comparator.comparing(Asta_inversa::getPrezzoAttuale).reversed());
            }
        }

        ArrayList<Asta_inversa_DTO> listAsteInversaDTO = new ArrayList<>();
        for (Asta_inversa asta : asteTrovate) {
            Asta_inversa_DTO astaDTO = convertiDaModelAaDto(asta);
            listAsteInversaDTO.add(astaDTO);
        }

        return listAsteInversaDTO;

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

    private Asta_inversa_DTO convertiDaModelAaDto(Asta_inversa asta){
        String img = null;
        if(asta.getPath_immagine()!=null && asta.getPath_immagine().length>0) {
            img = convertByteArrayToBase64(asta.getPath_immagine());
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = dateFormat.format( asta.getDataDiScadenza());

        Asta_inversa_DTO astaDTO = new Asta_inversa_DTO(asta.getId(),asta.getNome(), asta.getDescrizione(),img, asta.getPrezzoMax(),asta.getPrezzoAttuale(),
                formattedDate,asta.getCondizione(),asta.getId_acquirente());
        return astaDTO;
    }
    private Asta_inversa convertiDaDtoAModel(Asta_inversa_DTO astaDTO) throws ParseException {
        byte[] img = null;
        if(astaDTO.getPath_immagine()!=null && !astaDTO.getPath_immagine().isEmpty()) {
            img = convertBase64ToByteArray(astaDTO.getPath_immagine());
        }
        Timestamp timestamp = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date parsedDate = dateFormat.parse(astaDTO.getDataDiScadenza());
            timestamp = new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


        Asta_inversa asta = new Asta_inversa(astaDTO.getNome(), astaDTO.getDescrizione(),img, astaDTO.getPrezzoMax(),astaDTO.getPrezzoAttuale(),
                timestamp,astaDTO.getCondizione(),astaDTO.getId_acquirente());
        return asta;
    }

    public static String convertByteArrayToBase64(byte[] byteArray) {

        return Base64.getEncoder().encodeToString(byteArray);
    }
    public static byte[] convertBase64ToByteArray(String base64String) {
        String base64SenzaSpazi = base64String.replaceAll("\\s+", "");
        // Decodifica la stringa Base64 in un array di byte
        return Base64.getDecoder().decode(base64SenzaSpazi);
    }

}
