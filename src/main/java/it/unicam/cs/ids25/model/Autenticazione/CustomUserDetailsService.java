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

/**
 * La classe CustomUserDetailsService implementa l'interfaccia UserDetailsService per l'autenticazione.
 * Specifica di quale classe di tipo Utente si riferisce il nome utente.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtenteRepository utenteRepository;

    /**
     * Il metodo loadUserByUsername ritorna un {@link UserDetails} con i dati dell'utente.
     * @param username
     * @return l'utente con tutti i dettagli e il rispettivo ruolo
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Utente utente = utenteRepository.findByUsername(username);

        String ruolo = switch (utente) {
            case Produttore p -> "PRODUTTORE";
            case Trasformatore t -> "TRASFORMATORE";
            case Distributore d -> "DISTRIBUTORE";
            case Acquirente a -> "ACQUIRENTE";
            case Curatore c -> "CURATORE";
            case Gestore g -> "GESTORE";
            case Animatore an -> "ANIMATORE";
            default -> throw new IllegalStateException("Ruolo sconosciuto");
        };

        return User.builder()
                .username(utente.getUsername())
                .password(utente.getPassword())
                .roles(ruolo)
                .build();
    }
}