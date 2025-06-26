package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Autenticazione.SecurityService;
import it.unicam.cs.ids25.model.Dto.AziendaDTO;
import it.unicam.cs.ids25.model.Acquisto.Notifica;
import it.unicam.cs.ids25.model.Acquisto.Ordine;
import it.unicam.cs.ids25.model.Dto.SedeDTO;
import it.unicam.cs.ids25.model.GeocodingService;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Repository.*;
import it.unicam.cs.ids25.model.Utenti.*;
import it.unicam.cs.ids25.model.Autenticazione.Utente;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import it.unicam.cs.ids25.model.Evento;
import  it.unicam.cs.ids25.model.Prodotti.Prodotto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * la classe AziendaService è responsabile della logica per le operazioni di{@link Azienda}
 */
@Service
@Transactional
public class AziendaService {

    private final AziendaRepository aziendaRepository;

    private final NotificheRepository notificheRepo;

    private final UtenteRepository utenteRepo;

    private final OrdineRepository ordineRepo;

    private final PasswordEncoder passwordEncoder;

    private final SecurityService serviceUtente;

    private final EventoRepository eventoRepository;

    private final SecurityService securityService;

    private final ProdottoRepository prodottoRepository;

    @Autowired
    private GeocodingService geocodingService;



    /**
     * Costruttore del service per la gestione dell'entità {@link Azienda}.
     * Inietta tutti i repository e i servizi necessari per gestire aziende, utenti, prodotti, notifiche e sicurezza.
     *
     * @param aziendaRepository repository dell'entità {@link Azienda}
     * @param notificheRepo repository per la gestione delle {@link Notifica}
     * @param utenteRepo repository per l'accesso ai dati degli {@link Utente}
     * @param ordineRepo repository per la gestione degli {@link Ordine}
     * @param passwordEncoder componente per la codifica sicura delle password
     * @param serviceUtente servizio per la gestione della sicurezza lato utente
     * @param eventoRepository repository degli {@link Evento}
     * @param securityService servizio per la gestione generale della sicurezza e autenticazione
     * @param prodottoRepository repository per la gestione dei {@link Prodotto}
     */
    public AziendaService(AziendaRepository aziendaRepository, NotificheRepository notificheRepo, UtenteRepository utenteRepo, OrdineRepository ordineRepo, PasswordEncoder passwordEncoder, SecurityService serviceUtente, EventoRepository eventoRepository, SecurityService securityService, ProdottoRepository prodottoRepository) {
        this.aziendaRepository = aziendaRepository;
        this.notificheRepo = notificheRepo;
        this.utenteRepo = utenteRepo;
        this.ordineRepo = ordineRepo;
        this.passwordEncoder = passwordEncoder;
        this.serviceUtente = serviceUtente;
        this.eventoRepository = eventoRepository;
        this.securityService = securityService;
        this.prodottoRepository = prodottoRepository;
    }



    /**
     * metodo per la creazione di un'azienda.
     * l'azienda viene creata in base al tipo specificato nel DTO
     * @param dto json passato nella richiesta
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato della creazione dell'azienda.'
     */
    public ResponseEntity<String> crea(AziendaDTO dto) {
        final Azienda azienda;

        if (dto.getUsername().isEmpty() || dto.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Email e Password non valido");
        }

        if (dto.getNome().isEmpty() || dto.getTipoAzienda().isEmpty() || dto.getSede().isEmpty()) {
            return ResponseEntity.badRequest().body("nome, tipologia azienda o sede non valide");
        }

        if (dto.getTipoAzienda().equals("produttore")) {
            azienda = new Produttore(dto.getNome(), dto.getSede(), dto.getUsername(), dto.getPassword());
        } else if (dto.getTipoAzienda().equals("trasformatore")) {
            azienda = new Trasformatore(dto.getNome(), dto.getSede(), dto.getUsername(), dto.getPassword());
        } else if (dto.getTipoAzienda().equals("distributore")) {
            azienda = new Distributore(dto.getNome(), dto.getSede(), dto.getUsername(), dto.getPassword());
        }else
            return ResponseEntity.badRequest().body("tipologia azienda non valida");

        if (aziendaRepository.existsByUsername(dto.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username: " + dto.getUsername() + " già in uso");
        }

        azienda.setUsername(dto.getUsername());
        azienda.setPassword(passwordEncoder.encode(dto.getPassword()));

        String indirizzoCompleto = azienda.getSede();
        geocodingService.geocode(indirizzoCompleto).ifPresent(coords -> {
            azienda.setLatitudine(coords[0]);
            azienda.setLongitudine(coords[1]);
        });

        aziendaRepository.save(azienda);
        return ResponseEntity.status(200).body(dto.getNome() + " creato con successo");
    }



    /**
     * metodo per trovare tutte le aziende
     * @return List<Aziende> - Risposta HTTP con il messaggio di risultato della lista delle aziende.'
     */
    public List<Azienda> trovaTutte() {
        return aziendaRepository.findAll();
    }

    public Azienda trova(Long id) {
        return aziendaRepository.findById(id).orElse(null);
    }


    /**
     * metodo per l'eliminazione di una azienda.
     * l'azienda che ha effettuato il login verrà eliminata
     * @return ResponseEntity<List<AziendaDTO>> - Risposta HTTP con il messaggio di risultato della visualizzazione di tutte le aziende disponibili.'
     */
    @Transactional
    public ResponseEntity<String> eliminaAzienda() {
        if (serviceUtente.getAziendaCorrente() == null) {
            return ResponseEntity.badRequest().body("l'utente autenticato non è un azienda");
        }

        Azienda azienda = aziendaRepository.findById(serviceUtente.getAziendaCorrente().getId()).orElse(null);
        List<Long> listaIdProdotti = azienda.getIdProdottiCaricati();

        for (Long idProdotto : listaIdProdotti) {
            if (aziendaRepository.countProdottiNelCarrello(idProdotto) > 0) {
                return ResponseEntity.status(500).body(
                        "Prodotto/i dell'azienda presenti in un carrello, aspetta che l'acquirente effettui l'ordine");
            }

        }

        for (Long idProdotto : listaIdProdotti) {
            if (!notificheRepo.findAllByProdotto_Id(idProdotto).isEmpty()) {
                return ResponseEntity.status(500).body("L'azienda deve effettuare tutti gli ordini prima di essere eliminata");
            }
        }

        eventoRepository.eliminaAziendaInvitata(azienda.getId());

        aziendaRepository.deleteById(serviceUtente.getAziendaCorrente().getId());
        return ResponseEntity.status(200).body("Azienda eliminata con successo");
    }



    /**
     * metodo per controllare le notifiche dell'azienda
     * l'azienda che ha effettuato il login vedrà tutte le notifiche relative ai suoi prodotti venduti
     * @return ResponseEntity<StringBuilder> - Risposta HTTP con il messaggio di risultato delle notifiche dell'azienda.
     */
    public ResponseEntity<StringBuilder> notificheById() {
        List<Notifica> notifiche = notificheRepo.findByAzienda_Id(serviceUtente.getAziendaCorrente().getId());
        Azienda azienda = aziendaRepository.findById(serviceUtente.getAziendaCorrente().getId()).orElse(null);
        if (azienda != null) {
            StringBuilder msg = new StringBuilder();
            for (Notifica notifica : notifiche) {
                msg.append(notifica.toString()).append("\n");
            }
            return ResponseEntity.ok().body(msg);
        }
        return ResponseEntity.notFound().build();
    }


    /**
     * metodo per la spedizione di un ordine
     * l'azienda effettua la spedizione
     * @param idNotifica della notifica dell'ordine da spedire
     * una volta effettuata la spedizione verrà cancellata la notifica
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato della spedizione dell'ordine.
     */
    public ResponseEntity<String> effettuaSpedizione(Long idNotifica) {
        List<Notifica> notifica = notificheRepo.findByAzienda_Id(serviceUtente.getAziendaCorrente().getId());

        if (notifica.contains(notificheRepo.findById(idNotifica).get())) {
            ordineRepo.findById(notificheRepo.findById(idNotifica).get().getIdOrdine()).get().setSpedito();
            notificheRepo.deleteById(idNotifica);
            return ResponseEntity.ok().body("Spedizione effettuata");
        }

        return ResponseEntity.status(404).body("Ordine non trovato");
    }


    /**
     * metodo per l'aggiunta di una quantita' ad un prodotto
     * @param idProdotto del prodotto a cui si vuole aggiungere una certa quantita'
     * @param quantita da aggiungere al rpodotto
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato dell'aggiunta della quantita' al prodotto passato.
     */
    public ResponseEntity<String> aggiungiQuantità(Long idProdotto,int quantita){
        if(quantita < 1 ){
            return ResponseEntity.badRequest().body("Quantita inserita non valida");
        }

        if(!prodottoRepository.existsById(idProdotto)){
            return ResponseEntity.status(404).body("Prodotto non esistente");
        }

        Azienda azienda = aziendaRepository.findById(serviceUtente.getAziendaCorrente().getId()).orElse(null);
        Prodotto prodotto = prodottoRepository.findById(idProdotto).get();

        if (azienda.getListaProdotti().contains(prodotto)){
            prodotto.setQuantita(prodotto.getQuantita()+quantita);
            prodottoRepository.save(prodotto);
            return ResponseEntity.ok().body("Quantita' aggiunta con successo\n Prodotto: " + prodotto.getNome() + " quantita: " + prodotto.getQuantita());
        }
        return ResponseEntity.status(500).body("Il prodotto selezionato non è stato caricato dall'azienda: " + azienda.getNome() +
                " non puoi modificarne la quantita'");
    }

    public List<SedeDTO> getSedi() {
        List<Azienda> aziende = aziendaRepository.findAll();
        return aziende.stream()
                .filter(a -> a.getLatitudine() != null && a.getLongitudine() != null)
                .map(a -> new SedeDTO(
                        a.getNome(),
                        a.getSede(),
                        a.getLatitudine(),
                        a.getLongitudine()
                ))
                .collect(Collectors.toList());
    }
}