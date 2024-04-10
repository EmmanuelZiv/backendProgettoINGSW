package com.example.backendingsw.Repository;

import com.example.backendingsw.Model.Asta_allinglese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Asta_allingleseRepository extends JpaRepository<Asta_allinglese, Long> {

//    @Query(value = "SELECT a FROM asta_allinglese a WHERE a.condizione = 'aperta' ORDER BY a.intervalloTempoOfferte ASC LIMIT 5")
//    public List<Asta_allinglese> getAste_allingleseScadenzaRecente();

    List<Asta_allinglese> findFirst5ByCondizioneOrderByIntervalloTempoOfferteAsc(String condizione);

    List<Asta_allinglese> findFirst5ByCondizioneOrderByIdDesc(String condizione);

    @Query("SELECT a FROM Asta_allinglese a JOIN AsteCategorieAllInglese ac ON a.id = ac.asta.id WHERE a.condizione = 'aperta' AND ac.nomeCategoria = :nomeCategoria ORDER BY a.intervalloTempoOfferte ASC LIMIT 5")
    List<Asta_allinglese> findFirst5ByCategorieNomeAndCondizioneAperta(String nomeCategoria);

}
