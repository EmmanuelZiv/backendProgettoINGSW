package com.example.backendingsw.Repository;

import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.Asta_inversa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

public interface Asta_inversaRepository extends JpaRepository<Asta_inversa, Long> {

    List<Asta_inversa> findByCondizioneOrderByDataDiScadenzaAsc(String condizione);

    List<Asta_inversa> findByCondizioneOrderByIdDesc(String condizione);

    @Query("SELECT a FROM Asta_inversa a JOIN AsteCategorieInversa ac ON a.id = ac.asta.id WHERE a.condizione = 'aperta' AND ac.nomeCategoria = :nomeCategoria")
    List<Asta_inversa> findByCategorieNomeAndCondizioneAperta(String nomeCategoria);

    @Modifying
    @Transactional
    @Query( value = "INSERT INTO partecipazioneAstaInversa (idAstaInversa , indirizzo_email, offerta, tempo_offerta, stato) VALUES (?1, ?2, ?3, ?4, ?5) ", nativeQuery = true)
    int partecipaAstaInversa(Long idAstaInversa, String indirizzo_email, float offerta, Timestamp tempo_offerta, String stato);


    Asta_inversa findAsta_inversaById(Long idAstaInversa);

}
