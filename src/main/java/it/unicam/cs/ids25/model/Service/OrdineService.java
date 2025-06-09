package it.unicam.cs.ids25.model.Service;


import it.unicam.cs.ids25.model.Carrello;
import it.unicam.cs.ids25.model.Dto.OrdineDTO;
import it.unicam.cs.ids25.model.Dto.ProdottoOrdineDTO;
import it.unicam.cs.ids25.model.NotificheObserver;
import it.unicam.cs.ids25.model.OrderItem;
import it.unicam.cs.ids25.model.Ordine;
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

    public OrdineService(AcquirenteRepository acquirenteRepository, ProdottoRepository prodottoRepository, OrdineRepository ordineRepository, NotificheObserver notificheObserver) {
        this.acquirenteRepository = acquirenteRepository;
        this.prodottoRepository = prodottoRepository;
        this.ordineRepository = ordineRepository;
        this.notificheObserver = notificheObserver;
    }

    private AcquirenteRepository acquirenteRepository;
    private ProdottoRepository prodottoRepository;
    private OrdineRepository ordineRepository;
    private NotificheRepository notificheRepository;
    private final NotificheObserver notificheObserver;

    public ResponseEntity<String> aggiungiAlCarrello(ProdottoOrdineDTO dto) {

        if (!prodottoRepository.existsById(dto.getIdProdotto())){
            return ResponseEntity.status(404).body("Prodotto non esistente");
        }
        if (!acquirenteRepository.existsById(dto.getIdAcquirente())){
            return ResponseEntity.status(404).body("Acquirente non esistente");
        }
        if (dto.getQuantita()<1){ return ResponseEntity.status(404).body("la quantità deve essere maggiore di 0");}
        Acquirente acquirente = acquirenteRepository.findById(dto.getIdAcquirente()).get();
        Prodotto prodotto =prodottoRepository.findById(dto.getIdProdotto()).get();

        if (!prodotto.isApprovato()){ return ResponseEntity.status(404).body("Prodotto non esistente");}
        if (dto.getQuantita() > prodotto.getQuantita()){
            return ResponseEntity.status(404).body("Quantità non disponibile");
        }

        acquirente.aggiungiAlCarrello(prodotto, dto.getQuantita());
        return ResponseEntity.status(200).body(prodotto.getNome() + " Aggiunto al carrello");
    }



    public ResponseEntity<String> cancella(Long id) {

        if(!acquirenteRepository.existsById(id)){
            return ResponseEntity.status(404).body("Aquirente non esistente");
        }
        Acquirente acquirente= acquirenteRepository.findById(id).get();
        acquirente.cancellaCarrello();
        return ResponseEntity.status(200).body(acquirente.getNome()+"carrello cancellato");
    }

    public ResponseEntity<String> carrello(Long id) {
        if(!acquirenteRepository.existsById(id)){
            return ResponseEntity.status(404).body("Aquirente non esistente");
        }
        Acquirente acquirente= acquirenteRepository.findById(id).get();
        return ResponseEntity.status(200).body(acquirente.getCarrello().toString());
    }

    public ResponseEntity<String> effettua(OrdineDTO ordine) {
        if(!acquirenteRepository.existsById(ordine.getAcquirente())){
            return ResponseEntity.status(404).body("Aquirente non esistente");
        }
        Acquirente acquirente= acquirenteRepository.findById(ordine.getAcquirente()).get();
        if (acquirente.getCarrello().getProdottiDaAcquistare().isEmpty()){
            return ResponseEntity.status(404).body("Carrello vuoto");
        }
        Ordine o = new Ordine(acquirente, ordine.getIndirizzo());
        aggiornaQuantita(acquirente.getCarrello());
        ordineRepository.save(o);
        o.attach(notificheObserver);  // <- ATTACCHI L'OBSERVER
        o.notifyAziende(); // <- LO NOTIFICHI
        o.detach(notificheObserver);

        String prodCarrello = acquirente.getCarrello().toString();
        acquirente.cancellaCarrello();
        return ResponseEntity.status(200).body(prodCarrello+ ": ordine effettuato con successo");

    }


    public void aggiornaQuantita(Carrello carrello) {
        for (OrderItem o : carrello.getProdottiDaAcquistare()) {
            Prodotto p= o.getProdotto();
            p.setQuantita(p.getQuantita() - o.getQuantita());
            prodottoRepository.save(p);

        }
    }





}
