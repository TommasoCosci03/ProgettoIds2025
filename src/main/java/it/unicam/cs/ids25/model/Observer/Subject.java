package it.unicam.cs.ids25.model.Observer;

import it.unicam.cs.ids25.model.Utenti.Acquirente;
import it.unicam.cs.ids25.model.Utenti.Azienda;

/**
 *  L' interfaccia Subject viene implementata dall'oggetto il cuo
 *  cambiamento di stato implica l'update degli {@link Observer}
 */
public interface Subject {
    /**
     * Il metodo attach aggiunge un {@link Observer} alla lista di Observer
     * @param o
     */
    void attach(Observer o);

    /**
     * Il metodo detach rimuove un {@link Observer} alla lista di Observer
     * @param o
     */
    void detach(Observer o);

    /**
     * Il metodo notifyAziende chiama l'update su tutti gli
     * {@link Observer} aggiunti alla lista Observer delle aziende
     */
    void notifyAziende();
}
