package service.interfaces;

import model.Acquirente;

import java.util.Optional;

public interface I_Acquirente_Service {
    public Optional<Acquirente> login(String email, String password);
}
