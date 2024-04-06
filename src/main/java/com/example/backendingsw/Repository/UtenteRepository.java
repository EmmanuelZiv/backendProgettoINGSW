package com.example.backendingsw.Repository;

import com.example.backendingsw.Model.Acquirente;
import com.example.backendingsw.Model.Venditore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Acquirente, String> {
    // Seleziona tutti gli utenti
    // Questo metodo è fornito di default da JpaRepository, quindi non è necessario implementarlo manualmente.
    List<Acquirente> findAll();

    Acquirente findByEmail(String email);


    @Query(value = "SELECT * FROM acquirente WHERE indirizzo_email = :indirizzo_email AND password = :password", nativeQuery = true)
    public Optional<Acquirente> loginAcquirente(@Param("indirizzo_email") String indirizzo_email, @Param("password") String password);

    @Query(value = "SELECT * FROM venditore WHERE indirizzo_email = :indirizzo_email AND password = :password", nativeQuery = true)
    public Optional<Venditore> loginVenditore(@Param("indirizzo_email") String indirizzo_email, @Param("password") String password);


}
