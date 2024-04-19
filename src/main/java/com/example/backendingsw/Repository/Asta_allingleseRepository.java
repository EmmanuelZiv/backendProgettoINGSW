package com.example.backendingsw.Repository;

import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.SocialVenditore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface Asta_allingleseRepository extends JpaRepository<Asta_allinglese, Long> {

//    @Query(value = "SELECT a FROM asta_allinglese a WHERE a.condizione = 'aperta' ORDER BY a.intervalloTempoOfferte ASC LIMIT 5")
//    public List<Asta_allinglese> getAste_allingleseScadenzaRecente();

    List<Asta_allinglese> findFirst5ByCondizioneOrderByIntervalloTempoOfferteAsc(String condizione);

    List<Asta_allinglese> findFirst5ByCondizioneOrderByIdDesc(String condizione);

    @Query("SELECT a FROM Asta_allinglese a JOIN AsteCategorieAllInglese ac ON a.id = ac.asta.id WHERE a.condizione = 'aperta' AND ac.nomeCategoria = :nomeCategoria ORDER BY a.intervalloTempoOfferte ASC ")
    List<Asta_allinglese> findFirst5ByCategorieNomeAndCondizioneAperta(String nomeCategoria);


    @Query("SELECT a FROM Asta_allinglese a WHERE a.condizione='aperta' AND a.idVenditore = :indirizzo_email")
    public List<Asta_allinglese> findAsta_allingleseApertaByEmail(@Param("indirizzo_email") String indirizzo_email);

    @Query("SELECT a FROM Asta_allinglese a WHERE a.condizione='chiusa' AND a.idVenditore = :indirizzo_email")
    public List<Asta_allinglese> findAsta_allingleseChiusaByEmail(@Param("indirizzo_email") String indirizzo_email);


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

    @Query(value = "SELECT aa.* FROM preferitiAcquirente pv JOIN asta_allinglese aa ON pv.id_asta = aa.id WHERE pv.indirizzo_email= ?1 AND pv.tipo_asta= 'inglese'", nativeQuery = true)
    ArrayList<Asta_allinglese> getAsteInglesePreferite(String indirizzo_email);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO AsteCategorieAllInglese (id_asta_allinglese , nomeCategoria) VALUES (?1, ?2)",nativeQuery = true)
    Integer insertCategorieAstaInglese(Long id_asta_allinglese, String nomeCategoria);

    Asta_allinglese save(Asta_allinglese astaAllinglese);

    @Modifying
    @Transactional
    @Query(value ="INSERT INTO asta_allinglese (nome, descrizione, path_immagine, baseAsta, intervalloTempoOfferte, rialzoMin, prezzoAttuale, condizione, id_venditore) VALUES (?1,?2,?3,?4,CAST(?5 AS interval),?6,?7,?8,?9)", nativeQuery = true)
    void insert(String nome, String descrizione, byte[] path_immagine, float baseAsta, String intervalloTempoOfferte, float rialzoMin ,float prezzoAttuale, String condizione, String id_venditore);

    @Query("SELECT MAX(id) FROM Asta_allinglese") // Se stai usando un ID auto-generato, altrimenti adatta questa query
    Long getLastInsertedId();

    @Query(value = "SELECT indirizzo_email FROM partecipazioneAstaAllInglese WHERE idAstaInglese = ?1 ORDER BY offerta DESC LIMIT 1",nativeQuery = true)
    String getEmailVincente(Long idAstaInglese);

    @Query(value = "SELECT DISTINCT a.* FROM asta_allinglese a LEFT JOIN AsteCategorieAllInglese c ON a.id = c.id_asta_allinglese WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', ?1, '%')) AND c.nomeCategoria IN ?2 AND a.condizione = 'aperta'", nativeQuery = true)
    ArrayList<Asta_allinglese> findByNomeAndCategorieAndCondizioneOrderByPrezzo(String nome, ArrayList<String> categorie, String ordinamento);
    @Query(value = "SELECT DISTINCT a.* FROM asta_allinglese a LEFT JOIN AsteCategorieAllInglese c ON a.id = c.id_asta_allinglese WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', ?1, '%'))  AND a.condizione = 'aperta'", nativeQuery = true)
    ArrayList<Asta_allinglese> findByNomeAndCondizioneOrderByPrezzo(String nome,  String ordinamento);
    @Query(value = "SELECT DISTINCT a.* FROM asta_allinglese a LEFT JOIN AsteCategorieAllInglese c ON a.id = c.id_asta_allinglese WHERE c.nomeCategoria IN ?1 AND a.condizione = 'aperta'", nativeQuery = true)
    ArrayList<Asta_allinglese> findByCategorieAndCondizioneOrderByPrezzo(ArrayList<String> categorie,  String ordinamento);
    @Query(value = "SELECT DISTINCT a.* FROM asta_allinglese a LEFT JOIN AsteCategorieAllInglese c ON a.id = c.id_asta_allinglese WHERE a.condizione = 'aperta'", nativeQuery = true)
    ArrayList<Asta_allinglese> findByCondizioneOrderByPrezzo(String ordinamento);
}
