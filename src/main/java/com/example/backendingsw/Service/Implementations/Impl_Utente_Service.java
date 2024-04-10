    package com.example.backendingsw.Service.Implementations;

    import com.example.backendingsw.Model.Acquirente;
    import com.example.backendingsw.Model.Venditore;
    import com.example.backendingsw.Repository.VenditoreRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import com.example.backendingsw.Repository.UtenteRepository;
    import com.example.backendingsw.Service.Interfaces.I_Utente_Service;

    import java.util.Optional;

    @Service("Impl_Utente_Service")
    public class Impl_Utente_Service implements I_Utente_Service {

        @Autowired // crea l'oggetto
        private UtenteRepository utente_repository;
        @Autowired
        private VenditoreRepository venditoreRepository;

        @Override
        public Optional<Acquirente> loginAcquirente(String email, String password){
            System.out.println("email e password in impl_acquirente_Service" + email + password);
            return utente_repository.loginAcquirente(email, password);
        }

        @Override
        public Optional<Venditore> loginVenditore(String email, String password){
            System.out.println("email e password in impl_venditore_Service" + email + password);
            Optional<Venditore> venditore = venditoreRepository.loginVenditore(email, password);
            if(venditore.isPresent()){
                System.out.println("venditore trovato in impl utente");
            }else{
                System.out.println("venditore non trovato");
            }
            return venditore;
        }



        public void updateAcquirente(String nome,String cognome,String bio,String link,String areageografica,String email){
            System.out.println("impl_Acquirente_Service ha nome:"+nome + " cognome:"+ cognome + " bio:"+ bio +" link:"+link + " areageografica:"+areageografica + "email:" + email );
            utente_repository.updateAcquirente(nome,cognome,bio,link,areageografica,email);

        }


        public void updatePasswordAcquirente(String password,String email){
            System.out.println("impl_Acquirente_Service ha password:"+ password + "email:" + email );
            utente_repository.updatePasswordAcquirente(password,email);

        }


    }
