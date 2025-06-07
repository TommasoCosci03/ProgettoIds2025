package it.unicam.cs.ids25.model.Observer;

import it.unicam.cs.ids25.model.Utenti.Acquirente;
import it.unicam.cs.ids25.model.Utenti.Azienda;

public interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyAziende(Acquirente acquirente);
}
