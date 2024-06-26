package com.example.backendingsw;

import com.example.backendingsw.Notifications.NotificationListenerService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.backendingsw.Model", "com.example.backendingsw.Repository"})
@EnableJpaRepositories("com.example.backendingsw.Repository")
public class BackendIngswApplication {

	private static final Logger logger = LoggerFactory.getLogger(BackendIngswApplication.class);

	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		try {
			SpringApplication.run(BackendIngswApplication.class, args);
			logger.info("Applicazione backend avviata con successo.");

		} catch (Exception e) {
			logger.error("Errore durante l'avvio dell'applicazione: {}", e.getMessage());
		}
	}

	@Bean
	public ModelMapper modelMapper() {
		String serverPort = environment.getProperty("server.port");
		String serverAddress = getServerAddress();
		String backendUrl = "http://" + serverAddress + ":" + serverPort;
		logger.info("Backend URL: {}", backendUrl);
		return new ModelMapper();
	}

	private String getServerAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			logger.error("Errore durante la risoluzione dell'indirizzo IP del server.", e);
			return "localhost";
		}
	}

	@Bean
	public NotificationListenerService notificationListenerService() {
		return new NotificationListenerService(); // Se necessario, inizializza il servizio con le dipendenze necessarie
	}

}
