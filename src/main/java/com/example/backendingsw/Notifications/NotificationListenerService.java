package com.example.backendingsw.Notifications;

import com.example.backendingsw.Controller.NotificheController;
import com.example.backendingsw.Service.Interfaces.I_Notifiche_Service;
import com.example.backendingsw.Service.Interfaces.I_Utente_Service;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

@Service
public class NotificationListenerService {
    @Autowired
    private NotificheController notificheController;

    @Value("${spring.datasource.url}")
    private String postgresUrl;

    @Value("${spring.datasource.username}")
    private String postgresUsername;

    @Value("${spring.datasource.password}")
    private String postgresPassword;
    private static final String CHANNEL_NAME_ACQUIRENTE = "nuova_notifica_acquirente";
    private static final String CHANNEL_NAME_VENDITORE = "nuova_notifica_venditore";

    private static final Logger logger = LoggerFactory.getLogger(NotificationListenerService.class);

    private Connection connection;
    private Statement statement;


    @PostConstruct
    public void init() {
        try {
            // Connessione al database PostgreSQL
            connection = DriverManager.getConnection(postgresUrl, postgresUsername, postgresPassword);
            statement = connection.createStatement();

            // Registrazione per la ricezione di notifiche per gli acquirenti
            statement.execute("LISTEN " + CHANNEL_NAME_ACQUIRENTE);
            logger.info("Registrazione per la ricezione di notifiche per gli acquirenti effettuata.");
            // Registrazione per la ricezione di notifiche per i venditori
            statement.execute("LISTEN " + CHANNEL_NAME_VENDITORE);
            logger.info("Registrazione per la ricezione di notifiche per i venditori effettuata.");
            try {
                avviaServizioNotificaFirebase();
                logger.info("Servizio di notifica Firebase avviato con successo.");
            } catch (IOException e) {
                System.out.println("Errore nell'avvio del servizio di notifica Firebase");
                e.printStackTrace();
                throw new RuntimeException(e);
            }

            // Avvio del thread per la ricezione delle notifiche
            startNotificationListenerThread();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestione dell'errore
        }
    }

    private void startNotificationListenerThread() {
        Thread notificationListenerThread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    // Attende una notifica dal database per gli acquirenti
                    ResultSet rsAcquirente = statement.executeQuery("SELECT na.id, na.titolo, na.commento, a.token " +
                            "FROM notificheAcquirente na " +
                            "JOIN acquirente a ON na.id_acquirente = a.indirizzo_email " +
                            "WHERE na.mandata = FALSE");
                    while (rsAcquirente.next()) {
                        // Recupera i dettagli della notifica per gli acquirenti
                        Long idNotificaAcquirente = rsAcquirente.getLong("id");
                        String titoloAcquirente = rsAcquirente.getString("titolo");
                        String corpoAcquirente = rsAcquirente.getString("commento");
                        corpoAcquirente = corpoAcquirente != null ? corpoAcquirente : "";
                        String tokenAcquirente = rsAcquirente.getString("token");
                        System.out.println("notifica acquirente con id,titolo,token: " + idNotificaAcquirente + titoloAcquirente + tokenAcquirente);

                        // Costruisci un oggetto NotificationRequest per gli acquirenti
                        NotificationRequest notificationRequestAcquirente = new NotificationRequest(titoloAcquirente, corpoAcquirente, tokenAcquirente);
                        int valore = notificheController.updateMandataAcquirente(idNotificaAcquirente);
                        if(valore==1){
                            System.out.println("notifica acquirente settata a mandata");
                        }else{
                            System.out.println("Errore nel settare la notifica acquirente a mandata");
                        }
                        // Chiamata al metodo sendNotification di NotificheController con l'oggetto NotificationRequest per gli acquirentii 
                        if(tokenAcquirente!=null) {
                            notificheController.sendNotification(notificationRequestAcquirente);
                        }
                    }

                    // Attende una notifica dal database per i venditori
                    ResultSet rsVenditore = statement.executeQuery("SELECT nv.id, nv.titolo, nv.commento, v.token " +
                            "FROM notificheVenditore nv " +
                            "JOIN venditore v ON nv.id_venditore = v.indirizzo_email " +
                            "WHERE nv.mandata = FALSE");
                    while (rsVenditore.next()) {
                        // Recupera i dettagli della notifica per i venditori
                        Long idNotificaVenditore = rsVenditore.getLong("id");
                        String titoloVenditore = rsVenditore.getString("titolo");
                        System.out.println("notifica venditore con titolo: " + titoloVenditore);
                        String corpoVenditore = rsVenditore.getString("commento");
                        corpoVenditore = corpoVenditore != null ? corpoVenditore : "";
                        String tokenVenditore = rsVenditore.getString("token");
                        System.out.println("notifica venditore con id,titolo,token: " + idNotificaVenditore + titoloVenditore + tokenVenditore);

                        // Costruisci un oggetto NotificationRequest per i venditori
                        NotificationRequest notificationRequestVenditore = new NotificationRequest(titoloVenditore, corpoVenditore, tokenVenditore);
                        int valore = notificheController.updateMandataVenditore(idNotificaVenditore);
                        if(valore==1){
                            System.out.println("notifica venditore settata a mandata");
                        }else{
                            System.out.println("Errore nel settare la notifica venditore a mandata");
                        }

                        // Chiamata al metodo sendNotification di NotificheController con l'oggetto NotificationRequest per i venditori
                        if(tokenVenditore!=null) {
                            notificheController.sendNotification(notificationRequestVenditore);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Gestione dell'errore
            }
        });
        notificationListenerThread.start();
    }



    @PreDestroy
    public void cleanup() {
        try {
            // Chiusura della connessione e dello statement
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestione dell'errore
        }
    }
    public static void avviaServizioNotificaFirebase() throws IOException {
        Resource resource = new ClassPathResource("progettoingsw2324-firebase-adminsdk-j6oq4-e1fc5c6973.json");
        InputStream inputStream = resource.getInputStream();
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .build();
        FirebaseApp.initializeApp(options);
    }
}

