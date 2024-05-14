package com.example.backendingsw.Controller;

import com.example.backendingsw.DTO.Asta_alribasso_DTO;
import com.example.backendingsw.Model.Asta_alribasso;
import com.example.backendingsw.Service.Interfaces.I_Asta_alribasso_Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
                Asta_alribasso_DTO astaDTO = convertiDaModelAaDto(asta);
                listAsteAlribassoDTO.add(astaDTO);
            }
            return listAsteAlribassoDTO;
        } else {
            return new ArrayList<>();
        }
    }

    @GetMapping("/getAste_alribassoNomeCategoria")
    public List<Asta_alribasso_DTO> getAste_alribassoNomeCategoria(@RequestParam("nomiCategorie") ArrayList<String> nomiCategorie){
        Set<Asta_alribasso> asteUniche = new HashSet<>();

        for (String nomeCategoria : nomiCategorie) {
            List<Asta_alribasso> astePerCategoria = i_asta_alribasso_service.findByCategorieNomeAndCondizioneAperta(nomeCategoria);
            asteUniche.addAll(astePerCategoria);
        }

        List<Asta_alribasso_DTO> listAsteAlribassoDTO = new ArrayList<>();
        for (Asta_alribasso asta : asteUniche) {
            Asta_alribasso_DTO astaDTO = convertiDaModelAaDto(asta);
            listAsteAlribassoDTO.add(astaDTO);
        }

        return listAsteAlribassoDTO;
    }



    @GetMapping("/getAste_alribassoApertaByEmail/{indirizzo_email}")
    public List<Asta_alribasso_DTO> getAsta_alribassoApertaByEmail(@PathVariable String indirizzo_email){
        List<Asta_alribasso> list_asta_alribasso = i_asta_alribasso_service.findAsta_alribassoApertaByEmail(indirizzo_email);

        if (!list_asta_alribasso.isEmpty()) {
            List<Asta_alribasso_DTO> listAsteAlribassoDTO = new ArrayList<>();
            for (Asta_alribasso asta : list_asta_alribasso) {
                Asta_alribasso_DTO astaDTO = convertiDaModelAaDto(asta);
                listAsteAlribassoDTO.add(astaDTO);
            }
            return listAsteAlribassoDTO;
        } else {
            return new ArrayList<>();
        }

    }



    @GetMapping("/getAste_alribassoChiusaByEmail/{indirizzo_email}")
    public List<Asta_alribasso_DTO> getAsta_alribassoChiusaByEmail(@PathVariable String indirizzo_email){
        List<Asta_alribasso> list_asta_alribasso = i_asta_alribasso_service.findAsta_alribassoChiusaByEmail(indirizzo_email);

        if (!list_asta_alribasso.isEmpty()) {
            List<Asta_alribasso_DTO> listAsteAlribassoDTO = new ArrayList<>();
            for (Asta_alribasso asta : list_asta_alribasso) {
                Asta_alribasso_DTO astaDTO = convertiDaModelAaDto(asta);
                listAsteAlribassoDTO.add(astaDTO);
            }
            return listAsteAlribassoDTO;
        } else {
            return new ArrayList<>();
        }

    }

    @PostMapping("/acquistaAstaAlRibasso/{idAstaAlRibasso}/{indirizzo_email}/{prezzoAcquisto}")
    public int acquistaAstaAlRibasso(@PathVariable Long idAstaAlRibasso, @PathVariable String indirizzo_email, @PathVariable String prezzoAcquisto){
        try {
            float prezzo = Float.parseFloat(prezzoAcquisto) ;
            int numeroRitorno = i_asta_alribasso_service.acquistaAstaAlRibasso(idAstaAlRibasso, indirizzo_email, prezzo);
            return numeroRitorno;
        }catch (Exception e){
            return 0;
        }
    }

    @GetMapping("/trovaAstaRibasso/{idAstaRibasso}")
    public Asta_alribasso_DTO findAsta_alribassoById(@PathVariable Long idAstaRibasso){
        try{
            Asta_alribasso astaRecuperata = i_asta_alribasso_service.findAsta_alribassoById(idAstaRibasso);
            Asta_alribasso_DTO astaRecuperataDTO = convertiDaModelAaDto(astaRecuperata);
            return astaRecuperataDTO;
        }catch (Exception e){
            return null;
        }
    }
    @GetMapping("/verificaAstaAlRibassoInPreferiti/{indirizzo_email}/{idAstaRibasso}")
    public Integer verificaAstaAlRibassoInPreferiti(@PathVariable String indirizzo_email,@PathVariable Long idAstaRibasso){
        try{
            Integer verifica = i_asta_alribasso_service.verificaAstaAlRibassoInPreferiti(indirizzo_email, idAstaRibasso);
            return verifica;
        }catch (Exception e){
            return -1;
        }
    }
    @PostMapping("/inserimentoAstaInPreferiti/{idAstaRibasso}/{indirizzo_email}")
    public Integer inserimentoAstaInPreferiti(@PathVariable Long idAstaRibasso,@PathVariable String indirizzo_email){
        try{
            Integer inserimento = i_asta_alribasso_service.inserimentoAstaInPreferiti(idAstaRibasso,indirizzo_email);
            return inserimento;
        }catch (Exception e){
            return -1;
        }
    }
    @DeleteMapping("/eliminazioneAstaInPreferiti/{idAstaRibasso}/{indirizzo_email}")
    public Integer eliminazioneAstaInPreferiti(@PathVariable Long idAstaRibasso,@PathVariable String indirizzo_email){
        try{
            Integer rimozione = i_asta_alribasso_service.eliminazioneAstaInPreferiti(idAstaRibasso,indirizzo_email);
            return rimozione;
        }catch (Exception e){
            return -1;
        }
    }
    @GetMapping("/getAsteRibassoPreferite/{indirizzo_email}")
    public ArrayList<Asta_alribasso_DTO> getAsteRibassoPreferite(@PathVariable String indirizzo_email){

        ArrayList<Asta_alribasso> list_asta_alribasso = i_asta_alribasso_service.getAsteRibassoPreferite(indirizzo_email);

        if (!list_asta_alribasso.isEmpty()) {
            ArrayList<Asta_alribasso_DTO> listAsteAlribassoDTO = new ArrayList<>();
            for (Asta_alribasso asta : list_asta_alribasso) {
                Asta_alribasso_DTO astaDTO = convertiDaModelAaDto(asta);
                listAsteAlribassoDTO.add(astaDTO);
            }
            return listAsteAlribassoDTO;
        } else {
            return new ArrayList<>();
        }

    }
    @PostMapping("/insertAstaRibasso")
    public Long insertAstaRibasso(@RequestBody Asta_alribasso_DTO asta_ribasso_dto, @RequestParam(value = "lista_categorie", required = false) ArrayList<String> lista_categorie){
        try{
            String intervalString = asta_ribasso_dto.getIntervalloDecrementale() + " MINUTES'";
            byte[] img = null;
            if(asta_ribasso_dto.getPath_immagine()!=null) {
                img = convertBase64ToByteArray(asta_ribasso_dto.getPath_immagine());
            }
            i_asta_alribasso_service.insert(asta_ribasso_dto.getNome(), asta_ribasso_dto.getDescrizione(),img,asta_ribasso_dto.getPrezzoBase(),intervalString,
                    asta_ribasso_dto.getDecrementoAutomaticoCifra(),asta_ribasso_dto.getPrezzoMin(),asta_ribasso_dto.getPrezzoAttuale(),asta_ribasso_dto.getCondizione(),asta_ribasso_dto.getId_venditore());

            Long id_asta = i_asta_alribasso_service.getLastInsertedId();

            if (lista_categorie != null && !lista_categorie.isEmpty()) {
                for(String categoria:lista_categorie){
                    Integer value = i_asta_alribasso_service.insertCategorieAstaRibasso(id_asta,categoria);
                }
            }
            return id_asta;
        }catch (Exception e){
            return 0L;
        }
    }

    @GetMapping("/getAstePerRicerca")
    public ArrayList<Asta_alribasso_DTO> getAstePerRicerca(@RequestParam(value = "nome", required = false) String nome, @RequestParam(value = "ordinamento") String ordinamento,@RequestParam(value = "nomiCategorie", required = false) ArrayList<String> nomiCategorie){
        ArrayList<Asta_alribasso> asteTrovate = new ArrayList<>();
        if(nome!=null && !nome.isEmpty() && nomiCategorie != null && !nomiCategorie.isEmpty() && ordinamento != null && !ordinamento.isEmpty()){
            asteTrovate = i_asta_alribasso_service.findByNomeAndCategorieAndCondizioneOrderByPrezzo(nome,nomiCategorie,ordinamento);
        }else if(nome!=null && !nome.isEmpty() && ordinamento != null && !ordinamento.isEmpty()){
            asteTrovate = i_asta_alribasso_service.findByNomeAndCondizioneOrderByPrezzo(nome,ordinamento);
        }else if(nomiCategorie != null && !nomiCategorie.isEmpty() && ordinamento != null && !ordinamento.isEmpty()){
            asteTrovate = i_asta_alribasso_service.findByCategorieAndCondizioneOrderByPrezzo(nomiCategorie,ordinamento);
        }else if(ordinamento != null && !ordinamento.isEmpty()){
            asteTrovate = i_asta_alribasso_service.findByCondizioneOrderByPrezzo(ordinamento);
        }

        if(ordinamento!=null) {
            if (ordinamento.equals("ASC")) {
                Collections.sort(asteTrovate, Comparator.comparing(Asta_alribasso::getPrezzoAttuale));
            } else {
                Collections.sort(asteTrovate, Comparator.comparing(Asta_alribasso::getPrezzoAttuale).reversed());
            }
        }

        ArrayList<Asta_alribasso_DTO> listAsteAlribassoDTO = new ArrayList<>();
        for (Asta_alribasso asta : asteTrovate) {
            Asta_alribasso_DTO astaDTO = convertiDaModelAaDto(asta);
            listAsteAlribassoDTO.add(astaDTO);
        }

        return listAsteAlribassoDTO;

    }

    @Autowired
    private ModelMapper modelMapper;


    private Asta_alribasso_DTO convertiDaModelAaDto(Asta_alribasso asta){
        String img = null;
        if(asta.getPath_immagine()!=null) {
            img = convertByteArrayToBase64(asta.getPath_immagine());
        }
        Asta_alribasso_DTO astaDTO = new Asta_alribasso_DTO(asta.getId(),asta.getNome(), asta.getDescrizione(),img, asta.getPrezzoBase(),asta.getIntervalloDecrementale(),
                asta.getIntervalloDecrementale(),asta.getDecrementoAutomaticoCifra(),asta.getPrezzoMin(),asta.getPrezzoAttuale(),asta.getCondizione(),asta.getId_venditore());
        return astaDTO;
    }


    public static String convertByteArrayToBase64(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }
    public static byte[] convertBase64ToByteArray(String base64String) {
        String base64SenzaSpazi = base64String.replaceAll("\\s+", "");
        return Base64.getDecoder().decode(base64SenzaSpazi);
    }

}
