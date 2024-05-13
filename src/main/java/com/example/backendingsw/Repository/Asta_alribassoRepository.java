package com.example.backendingsw.Repository;

import com.example.backendingsw.Model.Asta_alribasso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface Asta_alribassoRepository extends JpaRepository<Asta_alribasso, Long> {

    List<Asta_alribasso> findFirst15ByCondizioneOrderByIdDesc(String condizione);

    @Query("SELECT a FROM Asta_alribasso a JOIN AsteCategorieAlRibasso ac ON a.id = ac.asta.id WHERE a.condizione = 'aperta' AND ac.nomeCategoria = :nomeCategoria ORDER BY a.id LIMIT 15")
    List<Asta_alribasso> findByCategorieNomeAndCondizioneAperta(String nomeCategoria);

    @Query("SELECT a FROM Asta_alribasso a WHERE a.condizione='aperta' AND a.id_venditore = :indirizzo_email")
    List<Asta_alribasso> findAsta_alribassoApertaByEmail(@Param("indirizzo_email") String indirizzo_email);

    @Query("SELECT a FROM Asta_alribasso a WHERE a.condizione='chiusa' AND a.id_venditore = :indirizzo_email")
    List<Asta_alribasso> findAsta_alribassoChiusaByEmail(@Param("indirizzo_email") String indirizzo_email);

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


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO AsteCategorieAlRibasso  (id_asta_alribasso  , nomeCategoria) VALUES (?1, ?2)",nativeQuery = true)
    Integer insertCategorieAstaRibasso(Long id_asta_alribasso, String nomeCategoria);
    @Modifying
    @Transactional
    @Query(value ="INSERT INTO asta_alribasso (nome, descrizione, path_immagine, prezzoBase, intervalloDecrementale, decrementoAutomaticoCifra, prezzoMin, prezzoAttuale,condizione, id_venditore) VALUES (?1,?2,?3,?4,CAST(?5 AS interval),?6,?7,?8,?9,?10)", nativeQuery = true)
    void insert(String nome, String descrizione, byte[] path_immagine, float prezzoBase, String intervalloDecrementale, float decrementoAutomaticoCifra ,float prezzoMin, float prezzoAttuale, String condizione, String id_venditore);
    @Query("SELECT MAX(id) FROM Asta_alribasso") // Se stai usando un ID auto-generato, altrimenti adatta questa query
    Long getLastInsertedId();

    @Query(value = "SELECT DISTINCT a.* FROM asta_alribasso  a LEFT JOIN AsteCategorieAlRibasso  c ON a.id = c.id_asta_alribasso  WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', ?1, '%')) AND c.nomeCategoria IN ?2 AND a.condizione = 'aperta' ", nativeQuery = true)
    ArrayList<Asta_alribasso> findByNomeAndCategorieAndCondizioneOrderByPrezzo(String nome, ArrayList<String> categorie, String ordinamento);
    @Query(value = "SELECT DISTINCT a.* FROM asta_alribasso  a LEFT JOIN AsteCategorieAlRibasso  c ON a.id = c.id_asta_alribasso  WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', ?1, '%'))  AND a.condizione = 'aperta' ", nativeQuery = true)
    ArrayList<Asta_alribasso> findByNomeAndCondizioneOrderByPrezzo(String nome, String ordinamento);
    @Query(value = "SELECT DISTINCT a.* FROM asta_alribasso  a LEFT JOIN AsteCategorieAlRibasso  c ON a.id = c.id_asta_alribasso  WHERE c.nomeCategoria IN ?1 AND a.condizione = 'aperta' ", nativeQuery = true)
    ArrayList<Asta_alribasso> findByCategorieAndCondizioneOrderByPrezzo(ArrayList<String> categorie, String ordinamento);
    @Query(value = "SELECT DISTINCT a.* FROM asta_alribasso  a LEFT JOIN AsteCategorieAlRibasso  c ON a.id = c.id_asta_alribasso  WHERE a.condizione = 'aperta' ", nativeQuery = true)
    ArrayList<Asta_alribasso> findByCondizioneOrderByPrezzo(String ordinamento);
}
