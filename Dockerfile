# Usa un'immagine di base di OpenJDK
FROM openjdk:17-jdk-alpine

# Copia il file JAR del tuo backend nella directory di lavoro del container
COPY build/libs/backendINGSW-0.0.1-SNAPSHOT.jar /app/


# Comando di avvio dell'applicazione Spring Boot
CMD ["java", "-jar", "/app/backendINGSW-0.0.1-SNAPSHOT.jar"]

