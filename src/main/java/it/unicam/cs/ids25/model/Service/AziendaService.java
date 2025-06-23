package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Autenticazione.SecurityService;
import it.unicam.cs.ids25.model.Dto.AziendaDTO;
import it.unicam.cs.ids25.model.Acquisto.Notifica;
import it.unicam.cs.ids25.model.Repository.*;
import it.unicam.cs.ids25.model.Utenti.*;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public AziendaService(AziendaRepository aziendaRepository, NotificheRepository notificheRepo, UtenteRepository utenteRepo, OrdineRepository ordineRepo, PasswordEncoder passwordEncoder, SecurityService serviceUtente, EventoRepository eventoRepository) {
        this.aziendaRepository = aziendaRepository;
        this.notificheRepo = notificheRepo;
        this.utenteRepo = utenteRepo;
        this.ordineRepo = ordineRepo;
        this.passwordEncoder = passwordEncoder;
        this.serviceUtente = serviceUtente;
        this.eventoRepository = eventoRepository;
    }

    public ResponseEntity<String> crea(AziendaDTO dto) {
        Azienda azienda = null;

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
        }

        if (aziendaRepository.existsByUsername(dto.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username: " + dto.getUsername() + " già in uso");
        }

        azienda.setUsername(dto.getUsername());
        azienda.setPassword(passwordEncoder.encode(dto.getPassword()));
        aziendaRepository.save(azienda);
        return ResponseEntity.status(200).body(dto.getNome() + " creato con successo");
    }

    public List<Azienda> trovaTutte() {
        return aziendaRepository.findAll();
    }

    public Azienda trova(Long id) {
        return aziendaRepository.findById(id).orElse(null);
    }

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

    public ResponseEntity<String> effettuaSpedizione(Long idNotifica) {
        List<Notifica> notifica = notificheRepo.findByAzienda_Id(serviceUtente.getAziendaCorrente().getId());

        if (notifica.contains(notificheRepo.findById(idNotifica).get())) {
            ordineRepo.findById(notificheRepo.findById(idNotifica).get().getIdOrdine()).get().setSpedito();
            notificheRepo.deleteById(idNotifica);
            return ResponseEntity.ok().body("Spedizione effettuata");
        }

        return ResponseEntity.status(404).body("Ordine non trovato");
    }
}