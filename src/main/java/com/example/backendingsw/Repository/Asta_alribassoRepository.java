package com.example.backendingsw.Repository;

import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.Asta_alribasso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface Asta_alribassoRepository extends JpaRepository<Asta_alribasso, Long> {

    List<Asta_alribasso> findByCondizioneOrderByIdDesc(String condizione);

    @Query("SELECT a FROM Asta_alribasso  a JOIN AsteCategorieAlRibasso ac ON a.id = ac.asta.id WHERE a.condizione = 'aperta' AND ac.nomeCategoria = :nomeCategoria")
    List<Asta_alribasso> findByCategorieNomeAndCondizioneAperta(String nomeCategoria);

    @Modifying
    @Transactional
    @Query( value = "INSERT INTO vincitoriAstaAlRibasso (idAstaRibasso, indirizzo_email, prezzoAcquisto) VALUES (?1, ?2, ?3)", nativeQuery = true)
    int acquistaAstaAlRibasso(Long idAstaAlRibasso, String indirizzo_email, float prezzoAcquisto);

    Asta_alribasso findAsta_alribassoById(Long idAstaRibasso);

    @Query (value = "SELECT pv.* FROM preferitiAcquirente pv  WHERE pv.indirizzo_email= ?1 AND pv.id_asta= ?2 AND pv.tipo_asta= 'ribasso'", nativeQuery = true)
    Integer verificaAstaAlRibassoInPreferiti(String indirizzo_email, Long idAstaRibasso);

    @Modifying
    @Transactional
    @Query (value = "INSERT INTO preferitiAcquirente (id_asta,tipo_asta,indirizzo_email) VALUES (?1,'ribasso',?2)", nativeQuery = true)
    Integer inserimentoAstaInPreferiti(Long idAstaRibasso, String indirizzo_email);

    @Modifying
    @Transactional
    @Query (value = "DELETE FROM preferitiAcquirente WHERE id_asta=?1 AND indirizzo_email= ?2 AND tipo_asta= 'ribasso'", nativeQuery = true)
    Integer eliminazioneAstaInPreferiti(Long idAstaRibasso, String indirizzo_email);

    @Query(value = "SELECT aa.* FROM preferitiAcquirente pv JOIN asta_alribasso aa ON pv.id_asta = aa.id WHERE pv.indirizzo_email= ? AND pv.tipo_asta= 'ribasso'", nativeQuery = true)
    ArrayList<Asta_alribasso> getAsteRibassoPreferite(String indirizzo_email);

}
