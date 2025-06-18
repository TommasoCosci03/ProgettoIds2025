package it.unicam.cs.ids25.model.Autenticazione;

import it.unicam.cs.ids25.model.Repository.AziendaRepository;
import it.unicam.cs.ids25.model.Repository.UtenteRepository;
import it.unicam.cs.ids25.model.Utenti.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Admin hardcoded
        if (username.equals("admin")) {
            return User.builder()
                    .username("admin")
                    .password("{bcrypt}...") // password già codificata
                    .roles("ADMIN")
                    .build();
        }

        Utente utente = utenteRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));

        String ruolo = switch (utente) {
            case Produttore p -> "PRODUTTORE";
            case Trasformatore t -> "TRASFORMATORE";
            case Distributore d -> "DISTRIBUTORE";
            case Acquirente a -> "ACQUIRENTE";
            default -> throw new IllegalStateException("Ruolo sconosciuto");
        };

        return User.builder()
                .username(utente.getUsername())
                .password(utente.getPassword())
                .roles(ruolo)
                .build();
    }
}