    package com.example.backendingsw.Service.Interfaces;

    import com.example.backendingsw.Model.Acquirente;
    import com.example.backendingsw.Model.Venditore;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    import java.util.Optional;

    public interface I_Utente_Service {
        public Optional<Acquirente> loginAcquirente(String email, String password);

        public Optional<Venditore> loginVenditore(String email, String password);

        public void updateAcquirente(String nome,String cognome,String bio,String link,String areageografica,String email);

        public void updatePasswordAcquirente(String password,String email);


    }
