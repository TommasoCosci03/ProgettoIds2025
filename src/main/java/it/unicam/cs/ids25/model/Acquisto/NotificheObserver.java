package it.unicam.cs.ids25.model.Acquisto;

import it.unicam.cs.ids25.model.Observer.Observer;
import it.unicam.cs.ids25.model.Repository.NotificheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificheObserver implements Observer {

    @Autowired
    private NotificheRepository notificheRepository;

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