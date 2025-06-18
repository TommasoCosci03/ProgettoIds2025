package it.unicam.cs.ids25.model.Autenticazione;

import it.unicam.cs.ids25.model.Repository.UtenteRepository;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import it.unicam.cs.ids25.model.Utenti.Curatore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
public class SecurityService {

    @Autowired
    private UtenteRepository utenteRepository;

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


}
