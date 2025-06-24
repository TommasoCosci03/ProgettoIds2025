package it.unicam.cs.ids25.model.Observer;
import it.unicam.cs.ids25.model.Acquisto.Ordine;
import it.unicam.cs.ids25.model.Utenti.Azienda;
/**
 * La classe Observer rappresenta un Observer per la notificazione dei
 * prodotti aquistati in un ordine alle {@link Azienda}
 */
public interface Observer {
    /**
     * Vengono notificate tutte le aziende corrispondenti al prodotto acquistato.
     * @param ordine
     */
    void update(Ordine ordine);
}
