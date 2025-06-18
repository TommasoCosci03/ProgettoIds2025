package it.unicam.cs.ids25.model.Utenti;

import it.unicam.cs.ids25.model.Autenticazione.Utente;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;

@Entity
@DiscriminatorValue("curatore")
public class Curatore extends Utente {

    protected Curatore(){
        super();
    }

    public Curatore(String username, String password) {
        super(username, password);
    }

}
