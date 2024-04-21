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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/notificheController")
public class NotificheController {

    @Autowired
    @Qualifier("Impl_NotificheAcquirente_Service")
    private I_Notifiche_Service i_Notifiche_Service;
    @Autowired
    private NotificheAcquirenteRepository notificheAcquirenteRepository;

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
            System.out.println("Non sono state notifiche dell'acquirente");
            return new ArrayList<>();
        }

        //else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
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
            System.out.println("Non sono state notifiche dell'acquirente");
            return new ArrayList<>();
        }

        //else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Errore: user name o password errata");
    }

    @DeleteMapping("/deleteNotificheAcquirente/{id}")
    public Integer deleteNotificheAcquirente(@PathVariable Long id){
        try {
            System.out.println("elimino la notifica acquirente con id " + id);
            i_Notifiche_Service.deleteNotificheAcquirente(id);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    @DeleteMapping("/deleteNotificheVenditore/{id}")
    public Integer deleteNotificheVenditore(@PathVariable Long id){
        try {
            System.out.println("elimino la notifica venditore con id " + id);
            i_Notifiche_Service.deleteNotificheVenditore(id);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    @PostMapping("/sendNotification")
    public void sendNotification(@RequestBody NotificationRequest request) {
        // Costruisci il messaggio da inviare a FCM
//        Message message = Message.builder()
//                .putData("titolo", request.getTitolo())
//                .putData("corpo", request.getCorpo())
//                .setToken(request.getTokenDestinatario())
//                .build();

        Message fcmMessage = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(request.getTitolo())
                        .setBody(request.getCorpo())
                        .build())
                .setToken(request.getTokenDestinatario())
                .build();
        // Invia il messaggio a FCM
        try {
            FirebaseMessaging.getInstance().send(fcmMessage);
        } catch (FirebaseMessagingException e) {
            // Gestisci eventuali errori
            e.printStackTrace();
        }
    }

    @GetMapping("/getNumeroNotificheAcquirente/{indirizzo_email}")
    public int getNumeroNotificheAcquirente(@PathVariable String indirizzo_email){
        System.out.println("entrato in getNumeroNotificheAcquirente");
        try{
            int numeroNotifiche = i_Notifiche_Service.getNumeroNotificheAcquirente(indirizzo_email);
            if(numeroNotifiche>0){
                System.out.println("ho recuperato " + numeroNotifiche + " notifiche per acquirente " + indirizzo_email + ".");
            }else{
                System.out.println("nessuna notifica recuperata per acquirente " + indirizzo_email + ".");
            }
            return numeroNotifiche;
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }
    @GetMapping("/getNumeroNotificheVenditore/{indirizzo_email}")
    public int getNumeroNotificheVenditore(@PathVariable String indirizzo_email){
        System.out.println("entrato in getNumeroNotificheVenditore");
        try{
            int numeroNotifiche = i_Notifiche_Service.getNumeroNotificheVenditore(indirizzo_email);
            if(numeroNotifiche>0){
                System.out.println("ho recuperato " + numeroNotifiche + " notifiche per Venditore " + indirizzo_email + ".");
            }else{
                System.out.println("nessuna notifica recuperata per Venditore " + indirizzo_email + ".");
            }
            return numeroNotifiche;
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public int updateMandataAcquirente(Long id){
        try {
            int valore = i_Notifiche_Service.updateMandataAcquirente(id);
            return valore;
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    public int updateMandataVenditore(Long id){
        try {
            int valore = i_Notifiche_Service.updateMandataVenditore(id);
            return valore;
        }catch(Exception e){
            e.printStackTrace();
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
