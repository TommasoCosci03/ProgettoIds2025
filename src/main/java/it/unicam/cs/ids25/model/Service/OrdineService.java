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

    public OrdineService(AcquirenteRepository acquirenteRepository, ProdottoRepository prodottoRepository, OrdineRepository ordineRepository, NotificheObserver notificheObserver, SecurityService securityService) {
        this.acquirenteRepository = acquirenteRepository;
        this.prodottoRepository = prodottoRepository;
        this.ordineRepository = ordineRepository;
        this.notificheObserver = notificheObserver;
        this.securityService = securityService;
    }

    public ResponseEntity<String> aggiungiAlCarrello(ProdottoOrdineDTO dto) {

        if (!prodottoRepository.existsById(dto.getIdProdotto())){
            return ResponseEntity.status(404).body("Prodotto non esistente");
        }

        if (dto.getQuantita()<1){
            return ResponseEntity.status(404).body("la quantità deve essere maggiore di 0");
        }

        Acquirente acquirente = acquirenteRepository.findById(securityService.getAcquirenteCorrente().getId()).get();
        Prodotto prodotto =prodottoRepository.findById(dto.getIdProdotto()).get();

        if (!prodotto.isApprovato()){
            return ResponseEntity.status(404).body("Prodotto non esistente");
        }

        if (dto.getQuantita() > prodotto.getQuantita()){
            return ResponseEntity.status(404).body("Quantità non disponibile");
        }

        acquirente.aggiungiAlCarrello(prodotto, dto.getQuantita());
        aggiornaQuantita(acquirente.getCarrello());
        return ResponseEntity.status(200).body(prodotto.getNome() + " Aggiunto al carrello");
    }


    public ResponseEntity<String> cancellaCarrello() {

        Acquirente acquirente= acquirenteRepository.findById(securityService.getAcquirenteCorrente().getId()).get();
        ripristinaQuantita(acquirente.getCarrello());
        acquirente.cancellaCarrello();
        return ResponseEntity.status(200).body(acquirente.getNome()+"carrello cancellato");
    }


    public ResponseEntity<String> getCarrello() {

        Acquirente acquirente= acquirenteRepository.findById(securityService.getAcquirenteCorrente().getId()).get();
        return ResponseEntity.status(200).body(acquirente.getCarrello().toString());
    }

    public ResponseEntity<String> effettuaOrdine(OrdineDTO ordine) {

        Acquirente acquirente= acquirenteRepository.findById(securityService.getAcquirenteCorrente().getId()).get();

        if (acquirente.getCarrello().getProdottiDaAcquistare().isEmpty()){
            return ResponseEntity.status(404).body("Carrello vuoto");
        }

        Ordine o = new Ordine(acquirente, ordine.getIndirizzo());
        if(acquirente.getSaldo()< o.getPrezzo()){
            return ResponseEntity.status(404).body("Saldo insufficiente");
        }
        acquirente.setSaldo(acquirente.getSaldo()-o.getPrezzo());
        o.setProdottiList(o.getAcquirente().getCarrello().toString());
        ordineRepository.save(o);
        o.attach(notificheObserver);  // <- ATTACCHI L'OBSERVER
        o.notifyAziende(); // <- LO NOTIFICHI
        o.detach(notificheObserver);
        String prodCarrello = acquirente.getCarrello().toString();
        acquirente.cancellaCarrello();
        return ResponseEntity.status(200).body(prodCarrello + ": ordine effettuato con successo\n" +
                "Saldo rimanente= " + acquirente.getSaldo()+"€");

    }


    public void aggiornaQuantita(Carrello carrello) {
        for (CarrelloItem o : carrello.getProdottiDaAcquistare()) {
            Prodotto p= o.getProdotto();
            p.setQuantita(p.getQuantita() - o.getQuantita());
            prodottoRepository.save(p);

        }
    }

    public void ripristinaQuantita(Carrello carrello) {
        for (CarrelloItem o : carrello.getProdottiDaAcquistare()) {
            Prodotto p= o.getProdotto();
            p.setQuantita(p.getQuantita() + o.getQuantita());
            prodottoRepository.save(p);

        }


    }


}
