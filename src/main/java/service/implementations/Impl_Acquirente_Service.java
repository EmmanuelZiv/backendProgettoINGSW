package service.implementations;

import model.Acquirente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AcquirenteRepository;
import service.interfaces.I_Acquirente_Service;

import java.util.Optional;

@Service("Impl_Acquirente_Service")
public class Impl_Acquirente_Service implements I_Acquirente_Service {

    @Autowired // crea l'oggetto
    private AcquirenteRepository acquirente_repository;

    @Override
    public Optional<Acquirente> login(String email, String password){
        return acquirente_repository.login(email, password);
    }
}
