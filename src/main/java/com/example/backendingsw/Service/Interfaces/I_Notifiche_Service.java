package com.example.backendingsw.Service.Interfaces;

import com.example.backendingsw.Model.NotificheAcquirente;
import com.example.backendingsw.Model.NotificheVenditore;

import java.util.ArrayList;

public interface I_Notifiche_Service {
    public ArrayList<NotificheAcquirente> getNotificheAcquirente(String id_acquirente);
    public ArrayList<NotificheVenditore> getNotificheVenditore(String id_venditore);

    public void deleteNotificheAcquirente(Long id);
    public void deleteNotificheVenditore(Long id);
    public int updateMandataAcquirente(Long id);
    public int updateMandataVenditore(Long id);
    public int getNumeroNotificheAcquirente(String id_acquirente);
    public int getNumeroNotificheVenditore(String id_venditore);
}
