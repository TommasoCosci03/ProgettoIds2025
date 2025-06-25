package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Acquisto.Ordine;
import it.unicam.cs.ids25.model.Autenticazione.SecurityService;
import it.unicam.cs.ids25.model.Dto.PacchettoProdottiDTO;
import it.unicam.cs.ids25.model.Dto.ProdottoSingoloDTO;
import it.unicam.cs.ids25.model.Dto.ProdottoTrasformatoDTO;
//import it.unicam.cs.ids25.model.Eccezioni.CreazioneProdottoException;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Repository.AziendaRepository;
import it.unicam.cs.ids25.model.Repository.CuratoreRepository;
import it.unicam.cs.ids25.model.Repository.NotificheRepository;
import it.unicam.cs.ids25.model.Repository.ProdottoRepository;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import it.unicam.cs.ids25.model.Utenti.Distributore;
import it.unicam.cs.ids25.model.Utenti.Produttore;
import it.unicam.cs.ids25.model.Utenti.Trasformatore;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import it.unicam.cs.ids25.model.Acquisto.Notifica;

import java.util.List;

import static it.unicam.cs.ids25.model.Prodotti.Enum.Categoria.Pacchetto;

/**
 * la classe ProdottoService è responsabile della logica per le operazioni relative ai {@link Prodotto}
 */
@Service
@Transactional
public class ProdottoService {

    private final ProdottoRepository repoProdotto;
    @Autowired
    private AziendaRepository repoAzienda;
    private final NotificheRepository repoNotifiche;
    private final ProdottoRepository pacchettoRepository;

    private final SecurityService securityService;
    private final CuratoreService curatoreService;

    @Autowired
    private CuratoreRepository curatoreRepository;


    /**
     * Costruttore per l'inizializzazione del service {@link ProdottoService}.
     * Inietta i repository necessari per la gestione dei prodotti, delle aziende,
     * delle notifiche e i servizi di sicurezza e curatore.
     *
     * @param repo repository generico di prodotti (non usato nel corpo ma iniettato)
     * @param repoProdotto repository specifico per la gestione dei {@link Prodotto}
     * @param repoAzienda repository per la gestione delle {@link Azienda}
     * @param repoNotifiche repository per la gestione delle {@link Notifica}
     * @param pacchettoRepository repository per la gestione di eventuali pacchetti di prodotti
     * @param securityService servizio per la gestione della sicurezza e autenticazione
     * @param curatoreService servizio per la gestione dei curatori
     */
    @Autowired
    public ProdottoService(ProdottoRepository repo, ProdottoRepository repoProdotto, AziendaRepository repoAzienda, NotificheRepository repoNotifiche, ProdottoRepository pacchettoRepository, SecurityService securityService, CuratoreService curatoreService) {
        this.repoProdotto = repoProdotto;
        this.repoAzienda = repoAzienda;
        this.repoNotifiche = repoNotifiche;
        this.pacchettoRepository = pacchettoRepository;
        this.securityService = securityService;
        this.curatoreService = curatoreService;
    }



    /**
     * Crea un prodotto singolo associato all'azienda corrente autenticata,
     * che deve essere un {@link Produttore}.
     * Se tutte le condizioni sono rispettate, viene creato e salvato il prodotto associato all'azienda.
     *
     * @param dto json che contiene tutti i campi relativi al prodotto da creare
     * @return ResponseEntity<String> con relativo messaggio di successo o errore
     */
    public ResponseEntity<String> creaProdottoSingolo(ProdottoSingoloDTO dto) {
        if (!(securityService.getAziendaCorrente() instanceof Produttore)) {
            return ResponseEntity.status(500).body(securityService.getAziendaCorrente().getNome() +
                    " Non puo' creare prodotti singoli perche' non e' un produttore");
        }
        if (dto.getCategoria().equals(Pacchetto)) {
            return ResponseEntity.badRequest().body("CURATORE MESSAGE:\n Solo il distributore puo creare dei pacchetti");
        }
        if (dto.getQuantita() < 1) {
            return ResponseEntity.badRequest().body("Quantità inserita non valida");
        }
        if (dto.getPrezzo() < 0.1) {
            return ResponseEntity.badRequest().body("Il prezzo deve essere consono al mercato");
        }

        Azienda azienda = repoAzienda.findById(securityService.getAziendaCorrente().getId()).get();
        Prodotto prodotto = azienda.creaProdottoAzienda(dto.getNome(), dto.getDescrizione(), dto.getPrezzo(),
                dto.getQuantita(), dto.getCategoria(), dto.getCertificazioni());
        repoProdotto.save(prodotto);

        return ResponseEntity.ok("Prodotto singolo " + prodotto.getNome() + " creato con successo");
    }


    /**
     * Crea un prodotto Trasformato associato all'azienda corrente autenticata,
     * che deve essere un {@link Trasformatore}.
     * Se tutte le condizioni sono rispettate, viene creato e salvato il prodotto associato all'azienda.
     *
     * @param dto json che contiene tutti i campi relativi al prodotto da creare
     * @return ResponseEntity<String> con relativo messaggio di successo o errore
     */
    public ResponseEntity<String> creaProdottoTrasformato(ProdottoTrasformatoDTO dto) {

        if (!(securityService.getAziendaCorrente() instanceof Trasformatore)) {
            return ResponseEntity.status(500).body(repoAzienda.findById(securityService.getAziendaCorrente().getId()).get().getNome() +
                    " Non puo' creare prodotti singoli perche' non e' un produttore");
        }
        if (dto.getCategoria().equals(Pacchetto)) {
            return ResponseEntity.badRequest().body("CURATORE MESSAGE:\n Solo il distributore puo creare dei pacchetti");
        }
        if (dto.getQuantita() <= 1) {
            return ResponseEntity.badRequest().body("Quantità inserita non valida");
        }
        if (dto.getPrezzo() < 0.1) {
            return ResponseEntity.badRequest().body("Il prezzo deve essere consono al mercato");
        }

        Trasformatore t = (Trasformatore) repoAzienda.findById(securityService.getAziendaCorrente().getId()).get();
        t.setMateriePrime(dto.getMateriePrime());
        Prodotto prodotto = t.creaProdottoAzienda(dto.getNome(), dto.getDescrizione(), dto.getPrezzo(),
                dto.getQuantita(), dto.getCategoria(), dto.getCertificazioni());
        repoProdotto.save(prodotto);

        return ResponseEntity.ok(prodotto.getNome() + " creato con successo");
    }



    /**
     * Crea un pacchetto di prodotti associato all'azienda corrente autenticata,
     * che deve essere un {@link Distributore}.

     * Se tutte le condizioni sono rispettate, viene creato e salvato il pacchetto di prodotti associato all'azienda.
     *
     * @param dto json che contiene tutti i campi relativi al prodotto da creare
     *contiene informazioni relative ai prodotti singoli(un pacchetto è realizzato con prodotti singoli)
     * @return ResponseEntity<String> con relativo messaggio di successo o errore
     */
    public ResponseEntity<String> creaPacchetto(PacchettoProdottiDTO dto) {

        if (!(securityService.getAziendaCorrente() instanceof Distributore)) {
            return ResponseEntity.status(500).body(repoAzienda.findById(securityService.getAziendaCorrente().getId()).get().getNome() +
                    " Non puo' creare prodotti singoli perche' non e' un produttore");
        }
        if (!dto.getCategoria().equals(Pacchetto)) {
            return ResponseEntity.badRequest().body("CURATORE MESSAGE:\n Il distributore puo solo creare dei pacchetti");
        }
        if (dto.getQuantita() <= 1) {
            return ResponseEntity.badRequest().body("Quantità inserita non valida");
        }
        if (dto.getPrezzo() < 0.1) {
            return ResponseEntity.badRequest().body("Il prezzo deve essere consono al mercato");
        }

        Distributore d = (Distributore) repoAzienda.findById(securityService.getAziendaCorrente().getId()).get();

        for (Prodotto p : repoProdotto.findAllById(dto.getPacchetto())) {
            if (!p.isApprovato()) {
                return ResponseEntity.badRequest().body("I prodotti che si inseriscono all'interno dei pacchetti devono essere approvati");
            }
        }

        d.setProdotti(repoProdotto.findAllById(dto.getPacchetto()));
        Prodotto prodotto = d.creaProdottoAzienda(dto.getNome(), dto.getDescrizione(), dto.getPrezzo(),
                dto.getQuantita(), dto.getCategoria(), dto.getCertificazioni());
        repoProdotto.save(prodotto);

        return ResponseEntity.ok(prodotto.getNome() + " creato con successo");
    }

    /**
     * Recupera la lista di tutti i prodotti presenti nel repository.
     *
     * @return una lista di oggetti {@link Prodotto} contenente tutti i prodotti salvati
     */
    public List<Prodotto> trovaTutti() {
        return repoProdotto.findAll();
    }

    public Prodotto trova(Long id) {
        return repoProdotto.findById(id).orElse(null);
    }


    /**
     * Elimina un prodotto dall'azienda corrente.
     *
     * Controlla che il prodotto e l'azienda esistano, che il prodotto appartenga all'azienda,
     * che il prodotto non sia presente in pacchetti, carrelli o ordini pendenti prima di eliminarlo.
     *
     * @param idProdotto l'id del prodotto da eliminare
     * @return una {@link ResponseEntity} contenente il relativo messaggio di successo o di errore
     */
    public ResponseEntity<String> eliminaProdotto(Long idProdotto) {
        if (!(repoProdotto.existsById(idProdotto) && repoAzienda.existsById(securityService.getAziendaCorrente().getId()))) {
            return ResponseEntity.status(404).body("Prodotto azienda non esistente");
        }

        Azienda azienda = repoAzienda.findById(securityService.getAziendaCorrente().getId()).get();
        Prodotto prodotto = repoProdotto.findById(idProdotto).get();

        if (!azienda.getListaProdotti().contains(prodotto)) {
            return ResponseEntity.status(500).body("Non puoi eliminare un prodotto che non hai caricato tu");
        }

        if (pacchettoRepository.countPacchettiConProdotto(idProdotto) > 0) {
            return ResponseEntity.status(500).body("Impossibile eliminare il prodotto: è presente in uno o più pacchetti.");
        }


        if (repoAzienda.countProdottiNelCarrello(idProdotto) > 0) {
            return ResponseEntity.status(500).body(
                    "Prodotto presente in un carrello, aspetta che l'acquirente effettui l'ordine");
        }


        if (!repoNotifiche.findAllByProdotto_Id(idProdotto).isEmpty()) {
            return ResponseEntity.status(500).body("Il prodotto e' presente in un ordine, spediscilo prima di eliminarlo");
        }

        azienda.getListaProdotti().remove(prodotto);
        repoAzienda.save(azienda); // aggiorna lo stato
        repoProdotto.delete(prodotto);
        return ResponseEntity.status(200).body("Prodotto eliminato con successo");

    }


}
