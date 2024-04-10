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
    int partecipaAstaInglese(Long idAstaInglese, String indirizzo_email, float offerta, Timestamp tempo_offerta, String stato);

    //@Query (value = "SELECT * FROM asta_allinglese WHERE id = :idAstaInglese " , nativeQuery = true)
    Asta_allinglese findAsta_allingleseById(Long idAstaInglese);
}
