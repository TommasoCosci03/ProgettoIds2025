package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Autenticazione.SecurityService;
import it.unicam.cs.ids25.model.Dto.PacchettoProdottiDTO;
import it.unicam.cs.ids25.model.Dto.ProdottoSingoloDTO;
import it.unicam.cs.ids25.model.Dto.ProdottoTrasformatoDTO;
//import it.unicam.cs.ids25.model.Eccezioni.CreazioneProdottoException;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Repository.AziendaRepository;
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

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@Transactional
public class ProdottoService {

    private final ProdottoRepository repoProdotto;
    @Autowired
    private AziendaRepository repoAzienda;
    private final NotificheRepository repoNotifiche;
    private final ProdottoRepository pacchettoRepository;

    private final SecurityService securityService;

    @Autowired
    public ProdottoService(ProdottoRepository repo, ProdottoRepository repoProdotto, AziendaRepository repoAzienda, NotificheRepository repoNotifiche, ProdottoRepository pacchettoRepository, SecurityService securityService) {
        this.repoProdotto = repoProdotto;
        this.repoAzienda = repoAzienda;
        this.repoNotifiche = repoNotifiche;
        this.pacchettoRepository = pacchettoRepository;
        this.securityService = securityService;
    }


    public ResponseEntity<String> creaProdottoSingolo(ProdottoSingoloDTO dto) {
        if(!( securityService.getAziendaCorrente() instanceof Produttore)) {
            return ResponseEntity.status(500).body(securityService.getAziendaCorrente().getNome() +
                    " Non puo' creare prodotti singoli perche' non e' un produttore");
        }

        Azienda azienda = repoAzienda.findById(securityService.getAziendaCorrente().getId()).get();
        Prodotto prodotto = azienda.creaProdottoAzienda(dto.getNome(), dto.getDescrizione(), dto.getPrezzo(),
                dto.getQuantita(), dto.getCategoria(), dto.getCertificazioni());
        repoProdotto.save(prodotto);

        return ResponseEntity.ok( prodotto.getNome() + " creato con successo");
    }

    public ResponseEntity<String> creaProdottoTrasformato(ProdottoTrasformatoDTO dto)  {

        if(!(securityService.getAziendaCorrente() instanceof Trasformatore)) {
            return ResponseEntity.status(500).body(repoAzienda.findById(securityService.getAziendaCorrente().getId()).get().getNome() +
                    " Non puo' creare prodotti singoli perche' non e' un produttore");
        }

        Trasformatore t = (Trasformatore) repoAzienda.findById(securityService.getAziendaCorrente().getId()).get();
        t.setMateriePrime(dto.getMateriePrime());
        Prodotto prodotto = t.creaProdottoAzienda(dto.getNome(), dto.getDescrizione(), dto.getPrezzo(),
                dto.getQuantita(), dto.getCategoria(), dto.getCertificazioni());
         repoProdotto.save(prodotto);

         return ResponseEntity.ok( prodotto.getNome() + " creato con successo");
    }

    public ResponseEntity<String> creaPacchetto(PacchettoProdottiDTO dto)  {

        if(!(securityService.getAziendaCorrente() instanceof Distributore)) {
            return ResponseEntity.status(500).body(repoAzienda.findById(securityService.getAziendaCorrente().getId()).get().getNome() +
                    " Non puo' creare prodotti singoli perche' non e' un produttore");
        }

        Distributore d = (Distributore) repoAzienda.findById(securityService.getAziendaCorrente().getId()).get();
        d.setProdotti(repoProdotto.findAllById(dto.getPacchetto()));
        Prodotto prodotto = d.creaProdottoAzienda(dto.getNome(), dto.getDescrizione(), dto.getPrezzo(),
                dto.getQuantita(), dto.getCategoria(), dto.getCertificazioni());
        repoProdotto.save(prodotto);

        return ResponseEntity.ok( prodotto.getNome() + " creato con successo");
    }

    public List<Prodotto> trovaTutti() {
        return repoProdotto.findAll();
    }

    public Prodotto trova(Long id) {
        return repoProdotto.findById(id).orElse(null);
    }


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


            if(repoAzienda.countProdottiNelCarrello(idProdotto) > 0){
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
