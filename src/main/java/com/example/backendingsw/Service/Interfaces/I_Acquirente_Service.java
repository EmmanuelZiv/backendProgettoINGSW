    package com.example.backendingsw.Service.Interfaces;

    import com.example.backendingsw.Model.Acquirente;

    import java.util.Optional;

    public interface I_Acquirente_Service {
        public Optional<Acquirente> login(String email, String password);
    }
