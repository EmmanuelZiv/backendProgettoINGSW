package com.example.backendingsw.Notifications;

import com.example.backendingsw.Controller.NotificheController;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.ByteArrayInputStream;
import java.io.IOException;
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
                logger.error("Errore nell'avvio del servizio di notifica Firebase", e);
                throw new RuntimeException(e);
            }

            // Avvio del thread per la ricezione delle notifiche
            startNotificationListenerThread();
        } catch (SQLException e) {
            logger.error("Error during init notification listener service", e);
        }
    }

    private void startNotificationListenerThread() {
        Thread notificationListenerThread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    processAcquirenteNotifications();
                    processVenditoreNotifications();
                }
            } catch (SQLException e) {
                logger.error("Error during startNotificationListenerThread", e);
            }
        });
        notificationListenerThread.start();
    }

    private void processAcquirenteNotifications() throws SQLException {
        ResultSet rsAcquirente = statement.executeQuery("SELECT na.id, na.titolo, na.commento, a.token " +
                "FROM notificheAcquirente na " +
                "JOIN acquirente a ON na.id_acquirente = a.indirizzo_email " +
                "WHERE na.mandata = FALSE");
        while (rsAcquirente.next()) {
            processNotification(rsAcquirente, notificheController::updateMandataAcquirente);
        }
    }

    private void processVenditoreNotifications() throws SQLException {
        ResultSet rsVenditore = statement.executeQuery("SELECT nv.id, nv.titolo, nv.commento, v.token " +
                "FROM notificheVenditore nv " +
                "JOIN venditore v ON nv.id_venditore = v.indirizzo_email " +
                "WHERE nv.mandata = FALSE");
        while (rsVenditore.next()) {
            processNotification(rsVenditore, notificheController::updateMandataVenditore);
        }
    }

    private void processNotification(ResultSet rs, NotificationUpdater updater) throws SQLException {
        Long idNotifica = rs.getLong("id");
        String titolo = rs.getString("titolo");
        String commento = rs.getString("commento");
        commento = commento != null ? commento : "";
        String token = rs.getString("token");
        NotificationRequest notificationRequest = new NotificationRequest(titolo, commento, token);
        int valore = updater.updateMandata(idNotifica);
        if (token != null) {
            notificheController.sendNotification(notificationRequest);
        }
    }

    @FunctionalInterface
    private interface NotificationUpdater {
        int updateMandata(Long idNotifica) throws SQLException;
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
            logger.error("Error during cleanUp notification listener service", e);
            // Gestione dell'errore
        }
    }

    @Value("${private_key_firebase}")
    private String firebasePrivateKey;

    public void avviaServizioNotificaFirebase() throws IOException {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(firebasePrivateKey.getBytes())))
                .build();
        FirebaseApp.initializeApp(options);
    }
}
