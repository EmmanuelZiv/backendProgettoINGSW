package com.example.backendingsw.Service.Interfaces;

import com.example.backendingsw.Model.NotificheAcquirente;
import com.example.backendingsw.Model.NotificheVenditore;

import java.util.ArrayList;

public interface I_Notifiche_Service {
    public ArrayList<NotificheAcquirente> getNotificheAcquirente(String id_acquirente);
    public ArrayList<NotificheVenditore> getNotificheVenditore(String id_venditore);

    public void deleteNotificheAcquirente(Long id);
    public void deleteNotificheVenditore(Long id);

}
