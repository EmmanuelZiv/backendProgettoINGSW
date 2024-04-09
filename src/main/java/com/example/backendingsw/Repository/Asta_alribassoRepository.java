package com.example.backendingsw.Repository;

import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.Asta_alribasso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Asta_alribassoRepository extends JpaRepository<Asta_alribasso, Long> {

    List<Asta_alribasso> findByCondizioneOrderByIdDesc(String condizione);

    @Query("SELECT a FROM Asta_alribasso  a JOIN AsteCategorieAlRibasso ac ON a.id = ac.asta.id WHERE a.condizione = 'aperta' AND ac.nomeCategoria = :nomeCategoria")
    List<Asta_alribasso> findByCategorieNomeAndCondizioneAperta(String nomeCategoria);
}
