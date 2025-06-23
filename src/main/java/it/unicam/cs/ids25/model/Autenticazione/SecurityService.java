package it.unicam.cs.ids25.model.Autenticazione;

import it.unicam.cs.ids25.model.Repository.UtenteRepository;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import it.unicam.cs.ids25.model.Utenti.Animatore;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import it.unicam.cs.ids25.model.Utenti.Curatore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

/**
 * La classe SecurityService implementa l'interfaccia SecurityService per l'autenticazione.
 * Responsabile per la ricerca dell'utente corrente e del ruolo.
 */
@Service
public class SecurityService {

    @Autowired
    private UtenteRepository utenteRepository;

    /**
     * Il metodo getAziendaCorrente ritorna l'azienda corrente.
     * @return l'oggetto Azienda corrente.
     */
    public Azienda getAziendaCorrente()  {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Utente utente = utenteRepository.findByUsername(username);

        if (utente instanceof Azienda azienda) {
            return azienda;
        } else {
          return null;
        }
    }

    /**
     * Il metodo getCuratoreCorrente ritorna il curatore corrente.
     * @return l'oggetto Curatore corrente.
     */
    public Acquirente getAcquirenteCorrente()  {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    Utente utente = utenteRepository.findByUsername(username);

        if (utente instanceof Acquirente acquirente) {
            return acquirente;
        } else {
          return null;
        }
    }

    /**
     * Il metodo getCuratoreCorrente ritorna il curatore corrente.
     * @return l'oggetto Curatore corrente.
     */
    public Animatore getAnimatoreCorrente() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Utente utente = utenteRepository.findByUsername(username);

        if (utente instanceof Animatore animatore) {
            return animatore;
        } else {
            return null;
        }
    }

}
