package it.unicam.cs.ids25.model.Utenti;

import it.unicam.cs.ids25.model.Autenticazione.Utente;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("gestore")
public class Gestore extends Utente {

    public Gestore(String username, String password) {
        super(username, password);
    }

    public Gestore() {

    }
}
