package com.example.backendingsw.Repository;

import com.example.backendingsw.Model.Venditore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface VenditoreRepository extends JpaRepository<Venditore, String> {
    @Query(value = "SELECT * FROM venditore WHERE indirizzo_email = :indirizzo_email AND password = :password", nativeQuery = true)
     Optional<Venditore> loginVenditore(@Param("indirizzo_email") String indirizzo_email, @Param("password") String password);

    @Query(value = "SELECT * FROM venditore WHERE indirizzo_email = :indirizzo_email ", nativeQuery = true)
     Optional<Venditore> ricercaVenditore(@Param("indirizzo_email") String indirizzo_email);

    @Modifying
    @Transactional
    @Query(value ="INSERT INTO venditore (nome,cognome,indirizzo_email,password,bio,link,areageografica) VALUES (?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
    void insertVenditore(String nome,String cognome,String email,String password,String bio,String link,String areageografica);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO categorieVenditore (nome , indirizzo_email) VALUES (?1, ?2)",nativeQuery = true)
    Integer insertCategorieVenditore(String nome, String indirizzo_email);
    @Query(value = "SELECT * FROM venditore WHERE token = :token", nativeQuery = true)
    Optional<Venditore> loginVenditoreConToken(@Param("token") String token);

    @Query(value = "SELECT * FROM venditore WHERE indirizzo_email = ?1", nativeQuery = true)
    Venditore getVenditoreByIndirizzo_email(String indirizzo_email);
}
