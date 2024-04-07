package com.example.backendingsw.Repository;

import com.example.backendingsw.Model.Asta_allinglese;
import com.example.backendingsw.Model.Asta_alribasso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Asta_alribassoRepository extends JpaRepository<Asta_alribasso, Long> {

    List<Asta_alribasso> findByCondizioneOrderByIdDesc(String condizione);
}
