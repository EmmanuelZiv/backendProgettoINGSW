    package com.example.backendingsw.Service.Interfaces;

    import com.example.backendingsw.Model.Acquirente;
    import com.example.backendingsw.Model.Venditore;

    import java.util.ArrayList;
    import java.util.Optional;

    public interface I_Utente_Service {
        public Optional<Acquirente> loginAcquirente(String email, String password);
        public ArrayList<String> findCategorieByIndirizzoEmailAcquirente(String email);
        public Optional<Venditore> loginVenditore(String email, String password);
        public ArrayList<String> findCategorieByIndirizzoEmailVenditore(String email);
    }
