    package com.example.backendingsw.Service.Implementations;

    import com.example.backendingsw.Model.*;
    import com.example.backendingsw.Repository.VenditoreRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Service;
    import com.example.backendingsw.Repository.UtenteRepository;
    import com.example.backendingsw.Service.Interfaces.I_Utente_Service;

    import java.util.ArrayList;
    import java.util.Optional;

    @Service("Impl_Utente_Service")
    public class Impl_Utente_Service implements I_Utente_Service {

        @Autowired // crea l'oggetto
        private UtenteRepository utente_repository;
        @Autowired
        private VenditoreRepository venditoreRepository;

        @Override
        public Optional<Acquirente> loginAcquirente(String email, String password){
            return utente_repository.loginAcquirente(email, password);
        }
        @Override
        public ArrayList<String> findCategorieByIndirizzoEmailAcquirente(String email){
            return utente_repository.findCategorieByIndirizzoEmailAcquirente(email);
        }
        @Override
        public Optional<Venditore> loginVenditore(String email, String password){
            Optional<Venditore> venditore = venditoreRepository.loginVenditore(email, password);
            if(venditore.isPresent()){
            }else{
            }
            return venditore;
        }
        @Override
        public ArrayList<String> findCategorieByIndirizzoEmailVenditore(String email){
            return utente_repository.findCategorieByIndirizzoEmailVenditore(email);
        }
        @Override
        public void updateAcquirente(String nome,String cognome,String bio,String link,String areageografica,String email){
            utente_repository.updateAcquirente(nome,cognome,bio,link,areageografica,email);

        }
        @Override
        public void updatePasswordAcquirente(String password,String email){
            utente_repository.updatePasswordAcquirente(password,email);

        }

        @Override
        public void updateVenditore(String nome,String cognome,String bio,String link,String areageografica,String email) {
            utente_repository.updateVenditore(nome, cognome, bio, link, areageografica, email);
        }
        @Override
        public Optional<Acquirente> ricercaAcquirente(String email){
            return utente_repository.ricercaAcquirente(email);
        }


        @Override
        public void updatePasswordVenditore(String password,String email){
            utente_repository.updatePasswordVenditore(password,email);
        }
        @Override
        public Optional<Venditore> ricercaVenditore(String email){
            Optional<Venditore> venditore = venditoreRepository.ricercaVenditore(email);
            if(venditore.isPresent()){
            }else{
            }
            return venditore;
        }
        @Override
        public void insertAcquirente(String nome,String cognome,String email,String password,String bio,String link,String areageografica){
            utente_repository.insertAcquirente(nome,cognome,email,password,bio,link,areageografica);
        }
        @Override
        public void insertVenditore(String nome,String cognome,String email,String password,String bio,String link,String areageografica){
            venditoreRepository.insertVenditore(nome,cognome,email,password,bio,link,areageografica);
        }
        @Override
        public int createAndInsertToken(String token ,String indirizzo_email){
            return utente_repository.createAndInsertTokenAcquirente(token, indirizzo_email);
        }
        @Override
        public int removeTokenFromAcquirente(String indirizzo_email){
            return utente_repository.removeTokenFromAcquirente(indirizzo_email);
        }
        @Override
        public int createAndInsertTokenVenditore(String token , String indirizzo_email){
            return utente_repository.createAndInsertTokenVenditore(token, indirizzo_email);
        }
        @Override
        public int removeTokenFromVenditore(String indirizzo_email){
            return utente_repository.removeTokenFromVenditore(indirizzo_email);
        }
        @Override
        public Optional<Acquirente> loginAcquirenteConToken(@Param("token") String token){
            return utente_repository.loginAcquirenteConToken(token);
        }
        @Override
        public Optional<Venditore> loginVenditoreConToken(@Param("token") String token){
            return venditoreRepository.loginVenditoreConToken(token);
        }
        @Override
        public void insertCategorieAcquirente(String indirizzo_email, String nome){
            utente_repository.insertCategorieAcquirente(nome,indirizzo_email);
        }
        @Override
        public void insertCategorieVenditore(String indirizzo_email, String nome){
             venditoreRepository.insertCategorieVenditore(nome,indirizzo_email);
        }

        @Override
        public Acquirente getAcquirenteByIndirizzo_email(String indirizzo_email){
            return utente_repository.getAcquirenteByIndirizzo_email(indirizzo_email);
        }
        @Override
        public Venditore getVenditoreByIndirizzo_email(String indirizzo_email){
            return venditoreRepository.getVenditoreByIndirizzo_email(indirizzo_email);
        }
    }
