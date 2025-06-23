package it.unicam.cs.ids25.model.Acquisto;

import it.unicam.cs.ids25.model.Observer.Observer;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Repository.NotificheRepository;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * La classe NotificheObserver rappresenta un Observer per l'aggiornamento delle notifiche ai
 * {@link Azienda} nel momento di un acquisto da parte di un {@link Acquirente}.
 */
@Component
public class NotificheObserver implements Observer {

    @Autowired
    private NotificheRepository notificheRepository;

    /**
     * Vengono notificate tutte le aziende corrispondenti al prodotto acquistato.
     * @param ordine
     */
    @Override
    public void update(Ordine ordine) {
        for (CarrelloItem item : ordine.getAcquirente().getCarrello().getProdottiDaAcquistare()) {
            Notifica notifica = new Notifica();
            notifica.setAcquirente(ordine.getAcquirente());
            notifica.setIndirizzo(ordine.getIndirizzo());
            notifica.setProdotto(item.getProdotto());
            notifica.setAzienda(item.getProdotto().getAzienda());
            notifica.setQuantita(item.getQuantita());
            notifica.setIdOrdine(ordine.getId());
            notificheRepository.save(notifica);
        }
    }
}