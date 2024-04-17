package com.example.backendingsw.Repository;

import com.example.backendingsw.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SocialVenditoreRepository extends JpaRepository<SocialVenditore, SocialVenditoreID> {

    @Query(value = "SELECT * FROM socialVenditore WHERE indirizzo_email = :indirizzo_email", nativeQuery = true)
    public List<SocialVenditore> findSocialVenditore(@Param("indirizzo_email") String indirizzo_email);

    //da fare

    @Query(value = "INSERT INTO socialVenditore (nome, link, indirizzo_email) VALUES (:nome, :link, :indirizzo_email) RETURNING *", nativeQuery = true)
    Optional<SocialVenditore> insertSocialVenditore(@Param("nome") String nome, @Param("link") String link, @Param("indirizzo_email") String indirizzo_email);

    @Query(value = "DELETE FROM socialVenditore WHERE nome = :nome AND link = :link AND indirizzo_email = :indirizzo_email", nativeQuery = true)
    public void deleteSocialVenditore(@Param("nome") String nome, @Param("link") String link, @Param("indirizzo_email") String indirizzo_email);


    @Query(value = "UPDATE socialVenditore SET nome = :newNome, link = :newLink WHERE nome = :oldNome AND link = :oldLink", nativeQuery = true)
    void updateSocialVenditore(@Param("oldNome") String oldNome, @Param("oldLink") String oldLink, @Param("newNome") String newNome, @Param("newLink") String newLink);






}