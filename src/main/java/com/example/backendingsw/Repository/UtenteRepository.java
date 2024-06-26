package com.example.backendingsw.Repository;

import com.example.backendingsw.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Acquirente, String> {
    List<Acquirente> findAll();


    @Query(value = "SELECT * FROM acquirente WHERE indirizzo_email = :indirizzo_email AND password = :password", nativeQuery = true)
    Optional<Acquirente> loginAcquirente(@Param("indirizzo_email") String indirizzo_email, @Param("password") String password);

    @Query(value = "SELECT * FROM acquirente WHERE token = :token", nativeQuery = true)
    Optional<Acquirente> loginAcquirenteConToken(@Param("token") String token);

    @Query(value = "SELECT ca.nome FROM categorieAcquirente ca WHERE ca.indirizzo_email = :indirizzo_email", nativeQuery = true)
    ArrayList<String> findCategorieByIndirizzoEmailAcquirente(@Param("indirizzo_email") String indirizzo_email);

    @Query(value = "SELECT * FROM venditore WHERE indirizzo_email = :indirizzo_email AND password = :password", nativeQuery = true)
    Optional<Venditore> loginVenditore(@Param("indirizzo_email") String indirizzo_email, @Param("password") String password);


    @Query(value = "SELECT ca.nome FROM categorieVenditore ca WHERE ca.indirizzo_email = :indirizzo_email", nativeQuery = true)
    ArrayList<String> findCategorieByIndirizzoEmailVenditore(@Param("indirizzo_email") String indirizzo_email);

    @Query(value = "UPDATE acquirente SET nome = :nome, cognome = :cognome,bio=:bio, link=:link, areageografica=:areageografica WHERE indirizzo_email = :indirizzo_email", nativeQuery = true)
    void updateAcquirente(@Param("nome") String nome, @Param("cognome") String cognome, @Param("bio") String bio, @Param("link") String link, @Param("areageografica") String areageografica, @Param("indirizzo_email") String indirizzo_email);

    @Query(value = "UPDATE acquirente SET  password=:password WHERE indirizzo_email = :indirizzo_email", nativeQuery = true)
    void updatePasswordAcquirente(@Param("password") String password,@Param("indirizzo_email") String indirizzo_email);


    @Query(value = "UPDATE venditore SET nome = :nome, cognome = :cognome,bio=:bio, link=:link, areageografica=:areageografica WHERE indirizzo_email = :indirizzo_email", nativeQuery = true)
    void updateVenditore(@Param("nome") String nome, @Param("cognome") String cognome, @Param("bio") String bio, @Param("link") String link, @Param("areageografica") String areageografica, @Param("indirizzo_email") String indirizzo_email);

    @Query(value = "UPDATE venditore SET  password=:password WHERE indirizzo_email = :indirizzo_email", nativeQuery = true)
    void updatePasswordVenditore(@Param("password") String password,@Param("indirizzo_email") String indirizzo_email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE acquirente SET token = ?1 WHERE indirizzo_email = ?2", nativeQuery = true)
    int createAndInsertTokenAcquirente(String token , String indirizzo_email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE acquirente SET token = NULL WHERE indirizzo_email = ?1", nativeQuery = true)
    int removeTokenFromAcquirente(String indirizzo_email);

    @Query(value = "SELECT * FROM acquirente WHERE indirizzo_email = :indirizzo_email" , nativeQuery = true)
     Optional<Acquirente> ricercaAcquirente(@Param("indirizzo_email") String indirizzo_email);

    @Modifying
    @Transactional
    @Query(value ="INSERT INTO acquirente (nome,cognome,indirizzo_email,password,bio,link,areageografica) VALUES (?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
    void insertAcquirente(String nome,String cognome,String email,String password,String bio,String link,String areageografica);
    @Modifying
    @Transactional
    @Query(value = "UPDATE venditore SET token = ?1 WHERE indirizzo_email = ?2", nativeQuery = true)
    int createAndInsertTokenVenditore(String token , String indirizzo_email);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO categorieAcquirente (nome , indirizzo_email) VALUES (?1, ?2)",nativeQuery = true)
    Integer insertCategorieAcquirente(String nome, String indirizzo_email);
    @Modifying
    @Transactional
    @Query(value = "UPDATE venditore SET token = NULL WHERE indirizzo_email = ?1", nativeQuery = true)
    int removeTokenFromVenditore(String indirizzo_email);

    @Query(value = "SELECT * FROM acquirente WHERE indirizzo_email = ?1", nativeQuery = true)
    Acquirente getAcquirenteByIndirizzo_email(String indirizzo_email);
}
