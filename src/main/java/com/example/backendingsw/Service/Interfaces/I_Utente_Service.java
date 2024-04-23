    package com.example.backendingsw.Service.Interfaces;

    import com.example.backendingsw.Model.*;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    import java.util.ArrayList;
    import java.util.Optional;

    public interface I_Utente_Service {
        public Optional<Acquirente> loginAcquirente(String email, String password);
        public ArrayList<String> findCategorieByIndirizzoEmailAcquirente(String email);
        public Optional<Venditore> loginVenditore(String email, String password);
        public void updateAcquirente(String nome,String cognome,String bio,String link,String areageografica,String email);
        public void updatePasswordAcquirente(String password,String email);
        public void updateVenditore(String nome,String cognome,String bio,String link,String areageografica,String email);
        public void updatePasswordVenditore(String password,String email);
        public ArrayList<String> findCategorieByIndirizzoEmailVenditore(String email);
        public Optional<Acquirente> ricercaAcquirente(String email);
        public Optional<Venditore> ricercaVenditore(String email);
        public void insertAcquirente(String nome,String cognome,String email,String password,String bio,String link,String areageografica);
        public void insertVenditore(String nome,String cognome,String email,String password,String bio,String link,String areageografica);
        public void insertCategorieAcquirente(String indirizzo_email,String nome) ;
        public void insertCategorieVenditore(String indirizzo_email,String nome) ;

    }
