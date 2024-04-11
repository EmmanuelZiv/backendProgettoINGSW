package com.example.backendingsw.Repository;

import com.example.backendingsw.Model.Asta_allinglese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface Asta_allingleseRepository extends JpaRepository<Asta_allinglese, Long> {

//    @Query(value = "SELECT a FROM asta_allinglese a WHERE a.condizione = 'aperta' ORDER BY a.intervalloTempoOfferte ASC LIMIT 5")
//    public List<Asta_allinglese> getAste_allingleseScadenzaRecente();

    List<Asta_allinglese> findFirst5ByCondizioneOrderByIntervalloTempoOfferteAsc(String condizione);

    List<Asta_allinglese> findFirst5ByCondizioneOrderByIdDesc(String condizione);

    @Query("SELECT a FROM Asta_allinglese a JOIN AsteCategorieAllInglese ac ON a.id = ac.asta.id WHERE a.condizione = 'aperta' AND ac.nomeCategoria = :nomeCategoria ORDER BY a.intervalloTempoOfferte ASC LIMIT 5")
    List<Asta_allinglese> findFirst5ByCategorieNomeAndCondizioneAperta(String nomeCategoria);

    @Modifying
    @Transactional
    @Query( value = "INSERT INTO partecipazioneAstaAllInglese (idAstaInglese, indirizzo_email, offerta, tempo_offerta, stato) VALUES (?1, ?2, ?3, ?4, ?5) ", nativeQuery = true)
    Integer partecipaAstaInglese(Long idAstaInglese, String indirizzo_email, float offerta, Timestamp tempo_offerta, String stato);

    Asta_allinglese findAsta_allingleseById(Long idAstaInglese);

    @Query (value = "SELECT pv.* FROM preferitiAcquirente pv  WHERE pv.indirizzo_email= ?1 AND pv.id_asta= ?2 AND pv.tipo_asta= 'inglese'", nativeQuery = true)
    Integer verificaAstaIngleseInPreferiti(String indirizzo_email, Long idAstaInglese);

    @Modifying
    @Transactional
    @Query (value = "INSERT INTO preferitiAcquirente (id_asta,tipo_asta,indirizzo_email) VALUES (?1,'inglese',?2)", nativeQuery = true)
    Integer inserimentoAstaInPreferiti(Long idAstaInglese, String indirizzo_email);

    @Modifying
    @Transactional
    @Query (value = "DELETE FROM preferitiAcquirente WHERE id_asta=?1 AND indirizzo_email= ?2 AND tipo_asta= 'inglese'", nativeQuery = true)
    Integer eliminazioneAstaInPreferiti(Long idAstaInglese, String indirizzo_email);
}
