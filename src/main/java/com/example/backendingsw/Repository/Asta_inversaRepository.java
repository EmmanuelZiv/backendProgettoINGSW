package com.example.backendingsw.Repository;

import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.Asta_inversa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Asta_inversaRepository extends JpaRepository<Asta_inversa, Long> {

    List<Asta_inversa> findByCondizioneOrderByDataDiScadenzaAsc(String condizione);

    List<Asta_inversa> findByCondizioneOrderByIdDesc(String condizione);

    @Query("SELECT a FROM Asta_inversa a JOIN AsteCategorieInversa ac ON a.id = ac.asta.id WHERE a.condizione = 'aperta' AND ac.nomeCategoria = :nomeCategoria")
    List<Asta_inversa> findByCategorieNomeAndCondizioneAperta(String nomeCategoria);

}
