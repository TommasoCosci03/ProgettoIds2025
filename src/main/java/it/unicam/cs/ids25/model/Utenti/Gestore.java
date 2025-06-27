package it.unicam.cs.ids25.model.Utenti;

import it.unicam.cs.ids25.model.Autenticazione.Utente;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


/**
 * Rappresenta un gestore del sistema.
 * Il {@code Gestore} è un {@link Utente} con privilegi elevati, responsabile
 * della creazione di un distributore.
 */
@Entity
@DiscriminatorValue("gestore")
public class Gestore extends Utente {

    /**
     * Crea un nuovo gestore con credenziali specificate.
     *
     * @param username lo username del gestore
     * @param password la password del gestore
     */
    public Gestore(String username, String password) {
        super(username, password);
    }

    public Gestore() {

    }
}
