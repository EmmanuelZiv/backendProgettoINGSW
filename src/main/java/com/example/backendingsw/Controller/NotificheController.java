package com.example.backendingsw.Controller;

import com.example.backendingsw.DTO.NotificheAcquirente_DTO;
import com.example.backendingsw.DTO.NotificheVenditore_DTO;
import com.example.backendingsw.Model.NotificheAcquirente;
import com.example.backendingsw.Model.NotificheVenditore;
import com.example.backendingsw.Service.Interfaces.I_Notifiche_Service;
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
