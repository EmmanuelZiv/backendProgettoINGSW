package com.example.backendingsw.Repository;

import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.Asta_inversa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public interface Asta_inversaRepository extends JpaRepository<Asta_inversa, Long> {

    List<Asta_inversa> findByCondizioneOrderByDataDiScadenzaAsc(String condizione);

    List<Asta_inversa> findByCondizioneOrderByIdDesc(String condizione);

    @Query("SELECT a FROM Asta_inversa a JOIN AsteCategorieInversa ac ON a.id = ac.asta.id WHERE a.condizione = 'aperta' AND ac.nomeCategoria = :nomeCategoria")
    List<Asta_inversa> findByCategorieNomeAndCondizioneAperta(String nomeCategoria);

    @Query("SELECT a FROM Asta_inversa a WHERE a.condizione='aperta' AND a.id_acquirente = :indirizzo_email")
    public List<Asta_inversa> findAsta_inversaApertaByEmail(@Param("indirizzo_email") String indirizzo_email);

    @Query("SELECT a FROM Asta_inversa a WHERE a.condizione='chiusa' AND a.id_acquirente = :indirizzo_email")
    public List<Asta_inversa> findAsta_inversaChiusaByEmail(@Param("indirizzo_email") String indirizzo_email);

    @Modifying
    @Transactional
    @Query( value = "INSERT INTO partecipazioneAstaInversa (idAstaInversa , indirizzo_email, offerta, tempo_offerta, stato) VALUES (?1, ?2, ?3, ?4, ?5) ", nativeQuery = true)
    int partecipaAstaInversa(Long idAstaInversa, String indirizzo_email, float offerta, Timestamp tempo_offerta, String stato);


    @Query(value = "SELECT a.* FROM partecipazioneAstaInversa p JOIN asta_inversa a ON p.idAstaInversa = a.id WHERE p.indirizzo_email= ?1", nativeQuery = true)
    ArrayList<Asta_inversa> getAsteInversePartecipate(String indirizzo_email);


    Asta_inversa findAsta_inversaById(Long idAstaInversa);

    @Query (value = "SELECT pv.* FROM preferitiVenditore pv  WHERE pv.indirizzo_email= ?1 AND pv.id_asta= ?2 AND pv.tipo_asta= 'inversa'", nativeQuery = true)
    Integer verificaAstaInversaInPreferiti(String indirizzo_email, Long idAstaInversa);

    @Modifying
    @Transactional
    @Query (value = "INSERT INTO preferitiVenditore (id_asta,tipo_asta,indirizzo_email) VALUES (?1,'inversa',?2)", nativeQuery = true)
    Integer inserimentoAstaInPreferiti(Long idAstaInversa, String indirizzo_email);

    @Modifying
    @Transactional
    @Query (value = "DELETE FROM preferitiVenditore WHERE id_asta=?1 AND indirizzo_email= ?2 AND tipo_asta= 'inversa'", nativeQuery = true)
    Integer eliminazioneAstaInPreferiti(Long idAstaInversa, String indirizzo_email);


    @Query(value = "SELECT aa.* FROM preferitiVenditore pv JOIN asta_inversa aa ON pv.id_asta = aa.id WHERE pv.indirizzo_email= ? AND pv.tipo_asta= 'inversa'", nativeQuery = true)
    ArrayList<Asta_inversa> getAsteInversaPreferite(String indirizzo_email);

//    @Modifying
//    @Transactional
//    @Query(value = "INSERT INTO asta_inversa (nome,descizione,path_immagine,prezzoMax,prezzoAttuale,dataDiScadenza,condizione,id_acquirente) VALUES (?1, ?2, ?3, ?4,?5,?6,?7,?8)", nativeQuery = true)
//    Long insertAstaInversa(String nome, String descrizione, byte[] path_immagine, float prezzoMax, float prezzoAttuale, String dataDiScadenza, String condizione, String id_acquirente);
//
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO AsteCategorieInversa (id_asta_inversa, nomeCategoria) VALUES (?1, ?2)",nativeQuery = true)
    Integer insertCategorieAstaInversa(Long id_asta_inversa, String nomeCategoria);

    Asta_inversa save(Asta_inversa asta);

    @Query(value = "SELECT indirizzo_email FROM partecipazioneAstaInversa WHERE idAstaInversa = ?1 ORDER BY offerta ASC LIMIT 1", nativeQuery = true)
    String getEmailVincente(Long idAstaInversa);

    @Query(value = "SELECT DISTINCT a.* FROM asta_inversa  a LEFT JOIN AsteCategorieInversa  c ON a.id = c.id_asta_inversa  WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', ?1, '%')) AND c.nomeCategoria IN ?2 AND a.condizione = 'aperta' ", nativeQuery = true)
    ArrayList<Asta_inversa> findByNomeAndCategorieAndCondizioneOrderByPrezzo(String nome, ArrayList<String> categorie, String ordinamento);
    @Query(value = "SELECT DISTINCT a.* FROM asta_inversa  a LEFT JOIN AsteCategorieInversa  c ON a.id = c.id_asta_inversa  WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', ?1, '%'))  AND a.condizione = 'aperta' ", nativeQuery = true)
    ArrayList<Asta_inversa> findByNomeAndCondizioneOrderByPrezzo(String nome, String ordinamento);
    @Query(value = "SELECT DISTINCT a.* FROM asta_inversa  a LEFT JOIN AsteCategorieInversa  c ON a.id = c.id_asta_inversa  WHERE c.nomeCategoria IN ?1 AND a.condizione = 'aperta' ", nativeQuery = true)
    ArrayList<Asta_inversa> findByCategorieAndCondizioneOrderByPrezzo(ArrayList<String> categorie, String ordinamento);
    @Query(value = "SELECT DISTINCT a.* FROM asta_inversa  a LEFT JOIN AsteCategorieInversa  c ON a.id = c.id_asta_inversa  WHERE a.condizione = 'aperta' ", nativeQuery = true)
    ArrayList<Asta_inversa> findByCondizioneOrderByPrezzo(String ordinamento);
}
