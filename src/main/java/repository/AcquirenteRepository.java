package repository;

import jakarta.transaction.Transactional;
import model.Acquirente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquirenteRepository extends JpaRepository<Acquirente,String> {
// Seleziona tutti gli utenti
    // Questo metodo è fornito di default da JpaRepository, quindi non è necessario implementarlo manualmente.
    // List<Acquirente> findAll();

    // Seleziona un utente con una certa email
    Acquirente findByEmail(String email);

    // Aggiungi un utente con certi valori
    // Questo metodo è fornito di default da JpaRepository attraverso il metodo save(Acquirente acquirente);
    // Acquirente save(Acquirente acquirente);

    // Elimina un utente con una certa email
    @Transactional
    @Modifying
    @Query("DELETE FROM Acquirente a WHERE a.email = ?1")
    void deleteByEmail(String email);


}
