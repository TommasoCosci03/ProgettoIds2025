package it.unicam.cs.ids25.model.Utenti;

import it.unicam.cs.ids25.model.Autenticazione.Utente;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;

/**
 * Rappresenta un curatore del sistema.
 * Il curatore è un {@link Utente} responsabile della validazione dei prodotti caricati dalle aziende.
 * I curatori possono approvare o rifiutare i prodotti.
 * se vengono accettati potranno essere acquistati, se vengono rifiutati saranno eliminati definitivamente.
 * il curatore viene creato di default dal gestore.
 **/
@Entity
@DiscriminatorValue("curatore")
public class Curatore extends Utente {

    protected Curatore() {
        super();
    }


    /**
     * Costruttore pubblico che inizializza un curatore con le credenziali specificate.
     *
     * @param username lo username del curatore
     * @param password la password del curatore
     */
    public Curatore(String username, String password) {
        super(username, password);
    }

}
