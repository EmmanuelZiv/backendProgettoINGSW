package com.example.backendingsw.Repository;

import jakarta.transaction.Transactional;
import com.example.backendingsw.Model.Acquirente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcquirenteRepository extends JpaRepository<Acquirente,String> {
// Seleziona tutti gli utenti
    // Questo metodo è fornito di default da JpaRepository, quindi non è necessario implementarlo manualmente.
    // List<Acquirente> findAll();

    Acquirente findByEmail(String email);

    @Query(value = "SELECT * FROM acquirente WHERE indirizzo_email = :email AND password = :password", nativeQuery = true)
    public Optional<Acquirente> login(@Param("email") String indirizzo_email, @Param("password") String password);

    // Elimina un utente con una certa email
    @Transactional
    @Modifying
    @Query("DELETE FROM Acquirente a WHERE a.email = ?1")
    void deleteByEmail(String email);


}
