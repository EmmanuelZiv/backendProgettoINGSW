package com.example.backendingsw.Controller;


import com.example.backendingsw.DTO.NotificheAcquirente_DTO;
import com.example.backendingsw.DTO.NotificheVenditore_DTO;
import com.example.backendingsw.Model.NotificheAcquirente;
import com.example.backendingsw.Model.NotificheVenditore;
import com.example.backendingsw.Notifications.NotificationRequest;
import com.example.backendingsw.Repository.NotificheAcquirenteRepository;
import com.example.backendingsw.Service.Interfaces.I_Notifiche_Service;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/notificheController")
public class NotificheController {
    private static final Logger logger = LoggerFactory.getLogger(Asta_allingleseController.class);

    @Autowired
    @Qualifier("Impl_NotificheAcquirente_Service")
    private I_Notifiche_Service i_Notifiche_Service;

    @GetMapping("/getNotificheAcquirente/{id_acquirente}")
    public ArrayList<NotificheAcquirente_DTO> getNotificheAcquirente(@PathVariable String id_acquirente){
        ArrayList<NotificheAcquirente> listaNotifiche = i_Notifiche_Service.getNotificheAcquirente(id_acquirente);

        if (!listaNotifiche.isEmpty()) {
            ArrayList<NotificheAcquirente_DTO> listaNotificheDTO = new ArrayList<>();
            for (NotificheAcquirente notificheAcquirente : listaNotifiche) {
                NotificheAcquirente_DTO notificheAcquirente_DTO = convertNotificheAcquirenteDTO(notificheAcquirente);
                listaNotificheDTO.add(notificheAcquirente_DTO);
            }
            return listaNotificheDTO;
        } else {
            return new ArrayList<>();
        }

    }

    @GetMapping("/getNotificheVenditore/{id_venditore}")
    public ArrayList<NotificheVenditore_DTO> getNotificheVenditore(@PathVariable String id_venditore){
        ArrayList<NotificheVenditore> listaNotifiche = i_Notifiche_Service.getNotificheVenditore(id_venditore);

        if (!listaNotifiche.isEmpty()) {
            ArrayList<NotificheVenditore_DTO> listaNotificheDTO = new ArrayList<>();
            for (NotificheVenditore notificheVenditore : listaNotifiche) {
                NotificheVenditore_DTO notificheVenditore_DTO = convertNotificheVenditoreDTO(notificheVenditore);
                listaNotificheDTO.add(notificheVenditore_DTO);
            }
            return listaNotificheDTO;
        } else {
            return new ArrayList<>();
        }

    }

    @DeleteMapping("/deleteNotificheAcquirente/{id}")
    public Integer deleteNotificheAcquirente(@PathVariable Long id){
        try {
            i_Notifiche_Service.deleteNotificheAcquirente(id);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    @DeleteMapping("/deleteNotificheVenditore/{id}")
    public Integer deleteNotificheVenditore(@PathVariable Long id){
        try {
            i_Notifiche_Service.deleteNotificheVenditore(id);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }


    @PostMapping("/sendNotification")
    public void sendNotification(@RequestBody NotificationRequest request) {
        Message fcmMessage = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(request.getTitolo())
                        .setBody(request.getCorpo())
                        .build())
                .setToken(request.getTokenDestinatario())
                .build();

        try {
            FirebaseMessaging.getInstance().send(fcmMessage);
        } catch (FirebaseMessagingException e) {

            logger.error("Error during sendNotification", e);
        }
    }

    @GetMapping("/getNumeroNotificheAcquirente/{indirizzo_email}")
    public int getNumeroNotificheAcquirente(@PathVariable String indirizzo_email){
        try{
            int numeroNotifiche = i_Notifiche_Service.getNumeroNotificheAcquirente(indirizzo_email);
            return numeroNotifiche;
        }catch(Exception e){

            logger.error("Error during getNumeroNotificheAcquirente", e);
        }
        return -1;
    }
    @GetMapping("/getNumeroNotificheVenditore/{indirizzo_email}")
    public int getNumeroNotificheVenditore(@PathVariable String indirizzo_email){
        try{
            int numeroNotifiche = i_Notifiche_Service.getNumeroNotificheVenditore(indirizzo_email);
            return numeroNotifiche;
        }catch(Exception e){

            logger.error("Error during getNumeroNotificheVenditore", e);
        }
        return -1;
    }

    public int updateMandataAcquirente(Long id){
        try {
            int valore = i_Notifiche_Service.updateMandataAcquirente(id);
            return valore;
        }catch(Exception e){
            logger.error("Error during updateMandataAcquirente", e);
        }
        return 0;
    }
    public int updateMandataVenditore(Long id){
        try {
            int valore = i_Notifiche_Service.updateMandataVenditore(id);
            return valore;
        }catch(Exception e){
            logger.error("Error during updateMandataVenditore", e);
        }
        return 0;
    }

    @Autowired
    private ModelMapper modelMapper;

    private NotificheAcquirente_DTO convertNotificheAcquirenteDTO(NotificheAcquirente notificheAcquirente){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        NotificheAcquirente_DTO notificheAcquirente_DTO = new NotificheAcquirente_DTO();
        notificheAcquirente_DTO = modelMapper.map(notificheAcquirente, NotificheAcquirente_DTO.class);
        return notificheAcquirente_DTO;
    }

    private NotificheVenditore_DTO convertNotificheVenditoreDTO(NotificheVenditore notificheVenditore){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        NotificheVenditore_DTO notificheVenditore_DTO = new NotificheVenditore_DTO();
        notificheVenditore_DTO = modelMapper.map(notificheVenditore, NotificheVenditore_DTO.class);
        return notificheVenditore_DTO;
    }

}
