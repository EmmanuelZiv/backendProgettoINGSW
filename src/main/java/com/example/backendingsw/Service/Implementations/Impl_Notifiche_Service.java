package com.example.backendingsw.Service.Implementations;

import com.example.backendingsw.Model.NotificheAcquirente;
import com.example.backendingsw.Model.NotificheVenditore;
import com.example.backendingsw.Repository.NotificheAcquirenteRepository;
import com.example.backendingsw.Repository.NotificheVenditoreRepository;
import com.example.backendingsw.Service.Interfaces.I_Notifiche_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service("Impl_NotificheAcquirente_Service")
public class Impl_Notifiche_Service implements I_Notifiche_Service {
    @Autowired // crea l'oggetto
    private NotificheAcquirenteRepository notificheAcquirenteRepository;
    @Autowired
    NotificheVenditoreRepository notificheVenditoreRepository;
    @Override
    public ArrayList<NotificheAcquirente> getNotificheAcquirente(String id_acquirente){
        return notificheAcquirenteRepository.getNotificheAcquirente(id_acquirente);
    }
    @Override
    public ArrayList<NotificheVenditore> getNotificheVenditore(String id_venditore){

        return notificheVenditoreRepository.getNotificheVenditore(id_venditore);
    }

    @Override
    public void deleteNotificheAcquirente(Long id){
        notificheAcquirenteRepository.deleteNotificheAcquirente(id);
    }
    @Override
    public void deleteNotificheVenditore(Long id){
        notificheVenditoreRepository.deleteNotificheVenditore(id);
    }
}
