package com.example.backendingsw.Repository;

import com.example.backendingsw.Model.NotificheAcquirente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface NotificheAcquirenteRepository extends JpaRepository<NotificheAcquirente, Long> {

    @Query (value ="SELECT * FROM notificheAcquirente WHERE id_acquirente = ?1 ", nativeQuery = true )
    ArrayList<NotificheAcquirente> getNotificheAcquirente(String id_acquirente);

    @Query (value = "DELETE FROM  notificheAcquirente WHERE id = ?1 ",nativeQuery = true)
    void deleteNotificheAcquirente(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE notificheAcquirente SET mandata = 'true' WHERE id = ?1", nativeQuery = true)
    int updateMandataAcquirente(Long id);

    @Query (value = "SELECT COUNT(*) AS numero_notifiche FROM notificheAcquirente WHERE id_acquirente = ?1", nativeQuery = true)
    int getNumeroNotificheAcquirente(String id_acquirente);

}
