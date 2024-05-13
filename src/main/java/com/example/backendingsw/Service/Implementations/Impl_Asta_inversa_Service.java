package com.example.backendingsw.Service.Implementations;

import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.Asta_inversa;
import com.example.backendingsw.Repository.Asta_inversaRepository;
import com.example.backendingsw.Service.Interfaces.I_Asta_inversa_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service("Impl_Asta_inversa_Service")
public class Impl_Asta_inversa_Service implements I_Asta_inversa_Service {

    @Autowired // crea l'oggetto
    private Asta_inversaRepository asta_inversaRepository;

    @Override
    public List<Asta_inversa> findByCondizioneOrderByDataDiScadenzaAsc(String condizione){
        return asta_inversaRepository.findByCondizioneOrderByDataDiScadenzaAsc("aperta");
    }
    @Override
    public List<Asta_inversa> findByCondizioneOrderByIdDesc(String condizione){
        return asta_inversaRepository.findByCondizioneOrderByIdDesc("aperta");
    }
    @Override
    public List<Asta_inversa> findByCategorieNomeAndCondizioneAperta(String nomeCategoria){
        return asta_inversaRepository.findByCategorieNomeAndCondizioneAperta(nomeCategoria);
    }

    @Override
    public List<Asta_inversa> findAsta_inversaApertaByEmail(String indirizzo_email){
        return asta_inversaRepository.findAsta_inversaApertaByEmail(indirizzo_email);
    }

    @Override
    public List<Asta_inversa> findAsta_inversaChiusaByEmail(String indirizzo_email){
        return asta_inversaRepository.findAsta_inversaChiusaByEmail(indirizzo_email);
    }

    @Override
    public int partecipaAstaInversa(Long idAstaInversa, String indirizzo_email, float offerta, Timestamp tempo_offerta, String stato){
        return asta_inversaRepository.partecipaAstaInversa(idAstaInversa,indirizzo_email,offerta,tempo_offerta,stato);
    }
    @Override
    public Asta_inversa findAsta_inversaById(Long idAstaInversa){
        return asta_inversaRepository.findAsta_inversaById(idAstaInversa);
    }
    @Override
    public Integer verificaAstaInversaInPreferiti(String indirizzo_email, Long idAstaInversa){
        return asta_inversaRepository.verificaAstaInversaInPreferiti(indirizzo_email,idAstaInversa);
    }
    @Override
    public Integer inserimentoAstaInPreferiti(Long idAstaInversa, String indirizzo_email){
        return asta_inversaRepository.inserimentoAstaInPreferiti(idAstaInversa,indirizzo_email);
    }
    @Override
    public Integer eliminazioneAstaInPreferiti(Long idAstaInversa, String indirizzo_email){
        return asta_inversaRepository.eliminazioneAstaInPreferiti(idAstaInversa,indirizzo_email);
    }
    @Override
    public ArrayList<Asta_inversa> getAsteInversaPreferite(String indirizzo_email){
        return asta_inversaRepository.getAsteInversaPreferite(indirizzo_email);
    }

    @Override
    public ArrayList<Asta_inversa> getAsteInversePartecipate(String indirizzo_email){
        return asta_inversaRepository.getAsteInversePartecipate(indirizzo_email);
    }

//    @Override
//    public Long insertAstaInversa(String nome, String descrizione, byte[] path_immagine, float prezzoMax, float prezzoAttuale, String dataDiScadenza, String condizione, String id_acquirente){
//        return asta_inversaRepository.insertAstaInversa(nome,descrizione,path_immagine,prezzoMax,prezzoAttuale,dataDiScadenza,condizione,id_acquirente);
//    }
    @Override
    public Integer insertCategorieAstaInversa(Long id_asta_inversa, String nomeCategoria){
        return asta_inversaRepository.insertCategorieAstaInversa(id_asta_inversa,nomeCategoria);
    }
    public Asta_inversa save(Asta_inversa asta){
        return asta_inversaRepository.save(asta);
    }

    @Override
    public String getEmailVincente(Long idAstaInversa){
        return asta_inversaRepository.getEmailVincente(idAstaInversa);
    }
    @Override
    public ArrayList<Asta_inversa> findByNomeAndCategorieAndCondizioneOrderByPrezzo(String nome, ArrayList<String> categorie, String ordinamento){
        return asta_inversaRepository.findByNomeAndCategorieAndCondizioneOrderByPrezzo(nome, categorie, ordinamento);
    }
    @Override
    public ArrayList<Asta_inversa> findByNomeAndCondizioneOrderByPrezzo(String nome, String ordinamento){
        return asta_inversaRepository.findByNomeAndCondizioneOrderByPrezzo(nome, ordinamento);
    }
    @Override
    public ArrayList<Asta_inversa> findByCategorieAndCondizioneOrderByPrezzo(ArrayList<String> categorie, String ordinamento){
        return asta_inversaRepository.findByCategorieAndCondizioneOrderByPrezzo(categorie, ordinamento);
    }
    @Override
    public ArrayList<Asta_inversa> findByCondizioneOrderByPrezzo(String ordinamento){
        return asta_inversaRepository.findByCondizioneOrderByPrezzo(ordinamento);
    }
}
