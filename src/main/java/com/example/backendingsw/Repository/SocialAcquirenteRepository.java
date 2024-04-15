package com.example.backendingsw.Repository;

import com.example.backendingsw.Model.Acquirente;
import com.example.backendingsw.Model.SocialAcquirente;
import com.example.backendingsw.Model.SocialAcquirenteID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SocialAcquirenteRepository extends JpaRepository<SocialAcquirente, SocialAcquirenteID> {

    @Query(value = "SELECT * FROM socialAcquirente WHERE indirizzo_email = :indirizzo_email", nativeQuery = true)
    public List<SocialAcquirente> findSocialAcquirente(@Param("indirizzo_email") String indirizzo_email);

    //da fare

    @Query(value = "INSERT INTO socialAcquirente (nome, link, indirizzo_email) VALUES (:nome, :link, :indirizzo_email) RETURNING *", nativeQuery = true)
    Optional<SocialAcquirente> insertSocialAcquirente(@Param("nome") String nome, @Param("link") String link, @Param("indirizzo_email") String indirizzo_email);

    @Query(value = "DELETE FROM socialAcquirente WHERE nome = :nome AND link = :link AND indirizzo_email = :indirizzo_email", nativeQuery = true)
    void deleteSocialAcquirente(@Param("nome") String nome, @Param("link") String link, @Param("indirizzo_email") String indirizzo_email);


    @Query(value = "UPDATE socialAcquirente SET nome = :newNome, link = :newLink WHERE nome = :oldNome AND link = :oldLink", nativeQuery = true)
    void updateSocialAcquirente(@Param("oldNome") String oldNome, @Param("oldLink") String oldLink, @Param("newNome") String newNome, @Param("newLink") String newLink);






}
