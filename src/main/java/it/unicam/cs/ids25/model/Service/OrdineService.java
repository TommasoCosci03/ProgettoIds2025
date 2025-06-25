package it.unicam.cs.ids25.model.Service;


import it.unicam.cs.ids25.model.Acquisto.Carrello;
import it.unicam.cs.ids25.model.Autenticazione.SecurityService;
import it.unicam.cs.ids25.model.Dto.OrdineDTO;
import it.unicam.cs.ids25.model.Dto.ProdottoOrdineDTO;
import it.unicam.cs.ids25.model.Acquisto.NotificheObserver;
import it.unicam.cs.ids25.model.Acquisto.CarrelloItem;
import it.unicam.cs.ids25.model.Acquisto.Ordine;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Repository.AcquirenteRepository;
import it.unicam.cs.ids25.model.Repository.NotificheRepository;
import it.unicam.cs.ids25.model.Repository.OrdineRepository;
import it.unicam.cs.ids25.model.Repository.ProdottoRepository;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import  it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Acquisto.Ordine;

/**
 * la classe OrdineService è responsabile della logica per le operazioni relative agli {@link Ordine}
 */
@Service
@Transactional
public class OrdineService {
    //Repository
    private AcquirenteRepository acquirenteRepository;
    private ProdottoRepository prodottoRepository;
    private OrdineRepository ordineRepository;
    private NotificheRepository notificheRepository;
    private final NotificheObserver notificheObserver;
    //Service
    private final SecurityService securityService;


    /**
     * Costruttore del service {@code OrdineService} per la gestione degli ordini.
     * Inietta i repository e i servizi necessari per operazioni su acquirenti, prodotti,
     * ordini, notifiche e sicurezza.
     * @param acquirenteRepository repository per la gestione degli {@link Acquirente}
     * @param prodottoRepository repository per la gestione dei {@link Prodotto}
     * @param ordineRepository repository per la gestione degli {@link Ordine}
     * @param notificheObserver componente per la gestione delle notifiche (observer)
     * @param securityService servizio per la gestione della sicurezza e autenticazione
 */
    public OrdineService(AcquirenteRepository acquirenteRepository, ProdottoRepository prodottoRepository, OrdineRepository ordineRepository, NotificheObserver notificheObserver, SecurityService securityService) {
        this.acquirenteRepository = acquirenteRepository;
        this.prodottoRepository = prodottoRepository;
        this.ordineRepository = ordineRepository;
        this.notificheObserver = notificheObserver;
        this.securityService = securityService;
    }


    /**
     * Aggiunge un prodotto al carrello dell'acquirente che ha effettuato la richiesta,
     * quindi quello che ha effettuato il login.
     * si controlla che il prodotto esista, sia approvato e che la quantità richiesta sia disponibile.
     *
     * @param dto richiesta json contenente l'ID del prodotto e la quantità da aggiungere
     * @return {@link ResponseEntity<String>} che contiene la risposta alla richiesta
     *
     */
    public ResponseEntity<String> aggiungiAlCarrello(ProdottoOrdineDTO dto) {

        if (!prodottoRepository.existsById(dto.getIdProdotto())) {
            return ResponseEntity.status(404).body("Prodotto non esistente");
        }

        if (dto.getQuantita() < 1) {
            return ResponseEntity.status(404).body("la quantità deve essere maggiore di 0");
        }

        Acquirente acquirente = acquirenteRepository.findById(securityService.getAcquirenteCorrente().getId()).get();
        Prodotto prodotto = prodottoRepository.findById(dto.getIdProdotto()).get();

        if (!prodotto.isApprovato()) {
            return ResponseEntity.status(404).body("Prodotto non esistente");
        }

        if (dto.getQuantita() > prodotto.getQuantita()) {
            return ResponseEntity.status(404).body("Quantità non disponibile");
        }

        acquirente.aggiungiAlCarrello(prodotto, dto.getQuantita());
        aggiornaQuantita(acquirente.getCarrello());
        return ResponseEntity.status(200).body(prodotto.getNome() + " Aggiunto al carrello");
    }


    /**
     * Cancella il carrello dell'acquirente attualmente autenticato,
     * ripristinando le quantità dei prodotti presenti nel carrello prima della cancellazione.
     *
     * @return {@link ResponseEntity<String>} con messaggio di conferma dell'avvenuta cancellazione del carrello
     */
    public ResponseEntity<String> cancellaCarrello() {

        Acquirente acquirente = acquirenteRepository.findById(securityService.getAcquirenteCorrente().getId()).get();
        ripristinaQuantita(acquirente.getCarrello());
        acquirente.cancellaCarrello();
        return ResponseEntity.status(200).body(acquirente.getNome() + "carrello cancellato");
    }


    /**
     * ottieni il carrello dell'acquirente attualmente autenticato,
     *
     * @return {@link ResponseEntity<String>} con il carrello
     */
    public ResponseEntity<String> getCarrello() {

        Acquirente acquirente = acquirenteRepository.findById(securityService.getAcquirenteCorrente().getId()).get();
        return ResponseEntity.status(200).body(acquirente.getCarrello().toString());
    }


    /**
     * Effettua l'ordine per l'acquirente attualmente autenticato utilizzando i prodotti presenti nel carrello.
     * Verifica che il carrello e che il saldo dell'utente sia sufficiente
     * Dopo la conferma, crea un nuovo ordine, aggiorna il saldo, notifica le aziende coinvolte e svuota il carrello.
     *
     * @param ordine richiesta json
     * @return {@link ResponseEntity<String>} con: il relativo messaggio
     *
     */
    public ResponseEntity<String> effettuaOrdine(OrdineDTO ordine) {

        Acquirente acquirente = acquirenteRepository.findById(securityService.getAcquirenteCorrente().getId()).get();

        if (acquirente.getCarrello().getProdottiDaAcquistare().isEmpty()) {
            return ResponseEntity.status(404).body("Carrello vuoto");
        }

        Ordine o = new Ordine(acquirente, ordine.getIndirizzo());
        if (acquirente.getSaldo() < o.getPrezzo()) {
            return ResponseEntity.status(404).body("Saldo insufficiente");
        }
        acquirente.setSaldo(acquirente.getSaldo() - o.getPrezzo());
        o.setProdottiList(o.getAcquirente().getCarrello().toString());
        ordineRepository.save(o);
        o.attach(notificheObserver);  // <- ATTACCHI L'OBSERVER
        o.notifyAziende(); // <- LO NOTIFICHI
        o.detach(notificheObserver);
        String prodCarrello = acquirente.getCarrello().toString();
        acquirente.cancellaCarrello();
        return ResponseEntity.status(200).body(prodCarrello + ": ordine effettuato con successo\n" +
                "Saldo rimanente= " + acquirente.getSaldo() + "€");

    }



    /**
     * Aggiorna la quantità dei prodotti nel repository.
     *viene utilizzato quando si aggiunge un prodotto al carrello
     * @param carrello il {@link Carrello} contenente i prodotti e le quantità da sottrarre
     */
    public void aggiornaQuantita(Carrello carrello) {
        for (CarrelloItem o : carrello.getProdottiDaAcquistare()) {
            Prodotto p = o.getProdotto();
            p.setQuantita(p.getQuantita() - o.getQuantita());
            prodottoRepository.save(p);

        }
    }


    /**
     * Ripristina la quantità dei prodotti nel repository.
     * Utilizzato tipicamente quando si cancella un carrello
     * la disponibilità dei prodotti allo stato precedente.
     *
     * @param carrello il {@link Carrello} contenente i prodotti e le quantità da ripristinare
     */
    public void ripristinaQuantita(Carrello carrello) {
        for (CarrelloItem o : carrello.getProdottiDaAcquistare()) {
            Prodotto p = o.getProdotto();
            p.setQuantita(p.getQuantita() + o.getQuantita());
            prodottoRepository.save(p);

        }


    }


}
