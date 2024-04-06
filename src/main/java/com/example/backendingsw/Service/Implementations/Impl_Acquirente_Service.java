    package com.example.backendingsw.Service.Implementations;

    import com.example.backendingsw.Model.Acquirente;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import com.example.backendingsw.Repository.AcquirenteRepository;
    import com.example.backendingsw.Service.Interfaces.I_Acquirente_Service;

    import java.util.Optional;

    @Service("Impl_Acquirente_Service")
    public class Impl_Acquirente_Service implements I_Acquirente_Service {

        @Autowired // crea l'oggetto
        private AcquirenteRepository acquirente_repository;

        @Override
        public Optional<Acquirente> login(String email, String password){
            System.out.println("email e password in impl_acquirente_Service" + email + password);
            return acquirente_repository.login(email, password);
        }
    }
