package it.unicam.cs.ids25.model;

import it.unicam.cs.ids25.model.Repository.UtenteRepository;
import it.unicam.cs.ids25.model.Utenti.Gestore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Filiera {

    public static void main(String[] args) {
        SpringApplication.run(Filiera.class, args);
    }

    @Bean
    public CommandLineRunner initGestore(UtenteRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("gestore") == null) {
                Gestore gestore = new Gestore("gestore", encoder.encode("gestore"));
                repo.save(gestore);
                System.out.println("Gestore inizializzato con username 'gestore'");
            }
        };
    }

}
