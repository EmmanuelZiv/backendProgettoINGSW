package com.example.backendingsw.Repository;

import com.example.backendingsw.Model.NotificheAcquirente;
import com.example.backendingsw.Model.NotificheVenditore;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface NotificheVenditoreRepository extends JpaRepository<NotificheVenditore, Long> {

    @Query (value ="SELECT * FROM notificheVenditore WHERE id_venditore = ?1 ", nativeQuery = true )
    ArrayList<NotificheVenditore> getNotificheVenditore(String id_venditore);

    @Query (value = "DELETE FROM  notificheVenditore WHERE id = ?1 ",nativeQuery = true)
    void deleteNotificheVenditore(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE notificheVenditore SET mandata = 'true' WHERE id = ?1", nativeQuery = true)
    int updateMandataVenditore(Long id);

    @Query (value = "SELECT COUNT(*) AS numero_notifiche FROM notificheVenditore WHERE id_venditore = ?1", nativeQuery = true)
    int getNumeroNotificheVenditore(String id_venditore);
}