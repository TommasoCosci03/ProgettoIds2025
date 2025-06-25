package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Autenticazione.SecurityService;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Repository.CuratoreRepository;
import it.unicam.cs.ids25.model.Repository.ProdottoRepository;
import it.unicam.cs.ids25.model.Repository.UtenteRepository;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import it.unicam.cs.ids25.model.Utenti.Curatore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import it.unicam.cs.ids25.model.Autenticazione.Utente;
import it.unicam.cs.ids25.model.Utenti.Curatore;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;

import java.util.List;
import java.util.Optional;

/**
 * la classe CuratoreService è responsabile della logica per le operazioni di{@link Curatore}
 */
@Service
public class CuratoreService {
    @Autowired
    private final ProdottoRepository prodottoRepository;

    @Autowired
    private final UtenteRepository utenteRepository;

    @Autowired
    private final CuratoreRepository curatoreRepository;

    private final SecurityService securityService;


    /**
     * Costruttore del service {@code CuratoreService} per la gestione delle funzionalità
     * relative ai curatori. Inietta i repository e i servizi necessari per gestire
     * prodotti, utenti e sicurezza.
     *
     * @param prodottoRepository repository per l'accesso ai dati dei {@link Prodotto}
     * @param utenteRepository repository per la gestione degli {@link Utente}
     * @param curatoreRepository repository per la gestione dei {@link Curatore}
     * @param securityService servizio per la gestione della sicurezza e dell'autenticazione
     */
    public CuratoreService(ProdottoRepository prodottoRepository, UtenteRepository utenteRepository, CuratoreRepository curatoreRepository, SecurityService securityService) {
        this.prodottoRepository = prodottoRepository;
        this.utenteRepository = utenteRepository;
        this.curatoreRepository = curatoreRepository;
        this.securityService = securityService;
    }

    /**
     * metodo per approvare una richiesta.
     * il curatore approva la richiesta del prodotto
     * @param idProdotto json passato nella richiesta, id del prodotto da approvare
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato dell'approvazione.'
     */
    public ResponseEntity<String> approva(Long idProdotto) {
        if (idProdotto == null) {
            return ResponseEntity.status(404).body("id prodotto nullo");
        }

        if (!prodottoRepository.existsById(idProdotto)) {
            return ResponseEntity.status(404).body("Prodotto non trovato");
        }

        Prodotto prodotto = prodottoRepository.findById(idProdotto).get();
        prodotto.setApprovato();
        prodottoRepository.save(prodotto);
        return ResponseEntity.ok().body("Prodotto approvato");
    }


    /**
     * metodo per rifiutare una richiesta.
     * il curatore rifiuta la richiesta del prodotto
     * @param idProdotto json passato nella richiesta, id del prodotto da rofoiutare
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato del rifiuto.'
     */
    public ResponseEntity<String> rifiuta(Long idProdotto) {
        if (idProdotto == null) {
            return ResponseEntity.status(404).body("id prodotto nullo");
        }

        if (!prodottoRepository.existsById(idProdotto)) {
            return ResponseEntity.status(404).body("Prodotto non trovato");
        }

        prodottoRepository.deleteById(idProdotto);
        return ResponseEntity.ok().body("Prodotto rifiutato");
    }

    /**
     * metodo per visualizzare tutte le richieste.
     * il curatore visualizza tutti i prodotti le cui richieste sono in sospeso
     * @return ResponseEntity<Prodotto> - Risposta HTTP con la lista dei prodotti non ancora approvati o rifiutati.'
     */
    public List<Prodotto> getRichieste() {
        return prodottoRepository.findAllByApprovatoIs(false);
    }


    /**
     * metodo per approvare tutte le richieste.
     * il curatore approva tutti i prodotti le cui richieste sono in sospeso
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di approvazione'
     */
    public ResponseEntity<String> approvaTutti() {
        for (Prodotto p : prodottoRepository.findAllByApprovatoIs(false)) {
            p.setApprovato();
            prodottoRepository.save(p);
        }

        return ResponseEntity.ok("Tutti i prodotti sono stati approvati");
    }


    /**
     * metodo per ottenere l'istanza del curatore.
     * @return Curatore - Risposta HTTP con il messaggio di approvazione'
     */
    public Curatore getCuratore() {
        return curatoreRepository.findByUsername("curatore");
    }
}
