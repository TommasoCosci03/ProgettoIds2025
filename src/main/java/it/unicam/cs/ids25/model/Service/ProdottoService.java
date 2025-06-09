package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Dto.PacchettoProdottiDTO;
import it.unicam.cs.ids25.model.Dto.ProdottoSingoloDTO;
import it.unicam.cs.ids25.model.Dto.ProdottoTrasformatoDTO;
//import it.unicam.cs.ids25.model.Eccezioni.CreazioneProdottoException;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Prodotti.ProdottoSingolo;
import it.unicam.cs.ids25.model.Repository.AziendaRepository;
import it.unicam.cs.ids25.model.Repository.NotificheRepository;
import it.unicam.cs.ids25.model.Repository.ProdottoRepository;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import it.unicam.cs.ids25.model.Utenti.Distributore;
import it.unicam.cs.ids25.model.Utenti.Trasformatore;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProdottoService {

    private final ProdottoRepository repoProdotto;
    private final AziendaRepository repoAzienda;
    private final NotificheRepository repoNotifiche;

    @Autowired
    public ProdottoService(ProdottoRepository repo, ProdottoRepository repoProdotto, AziendaRepository repoAzienda, NotificheRepository repoNotifiche) {
        this.repoProdotto = repoProdotto;
        this.repoAzienda = repoAzienda;
        this.repoNotifiche = repoNotifiche;
    }

    public Prodotto creaProdottoSingolo(ProdottoSingoloDTO dto) {
        Azienda azienda = repoAzienda.findById(dto.getIdAzienda()).get();
        Prodotto prodotto = azienda.creaProdottoAzienda(dto.getNome(), dto.getDescrizione(), dto.getPrezzo(),
                dto.getQuantita(), dto.getCategoria(), dto.getCertificazioni());
        return repoProdotto.save(prodotto);
    }

    public Prodotto creaProdottoTrasformato(ProdottoTrasformatoDTO dto) {

        Trasformatore t = (Trasformatore) repoAzienda.findById(dto.getIdAzienda()).get();
        t.setMateriePrime(dto.getMateriePrime());
        Prodotto prodotto = t.creaProdottoAzienda(dto.getNome(), dto.getDescrizione(), dto.getPrezzo(),
                dto.getQuantita(), dto.getCategoria(), dto.getCertificazioni());
        return repoProdotto.save(prodotto);
    }

    public Prodotto creaPacchetto(PacchettoProdottiDTO dto) {
        Distributore d = (Distributore) repoAzienda.findById(dto.getIdAzienda()).get();
        d.setProdotti(repoProdotto.findAllById(dto.getPacchetto()));
        Prodotto prodotto = d.creaProdottoAzienda(dto.getNome(), dto.getDescrizione(), dto.getPrezzo(),
                dto.getQuantita(), dto.getCategoria(), dto.getCertificazioni());
        return repoProdotto.save(prodotto);
    }

    public List<Prodotto> trovaTutti() {
        return repoProdotto.findAll();
    }

    public Prodotto trova(Long id) {
        return repoProdotto.findById(id).orElse(null);
    }

    public ResponseEntity<String> eliminaProdotto(Long idProdotto, Long idAzienda) {
            if (!(repoProdotto.existsById(idProdotto) && repoAzienda.existsById(idAzienda))) {
                return ResponseEntity.status(404).body("Prodotto azienda non esistente");
            }

        Azienda azienda = repoAzienda.findById(idAzienda).get();
        Prodotto prodotto = repoProdotto.findById(idProdotto).get();

            if (!azienda.getProdottiCaricati().contains(prodotto)) {
                return ResponseEntity.status(404).body("Non puoi eliminare un prodotto che non hai caricato tu");
            }

            if (!repoNotifiche.findAllByProdotto_Id(idProdotto)) {
                return ResponseEntity.status(404).body("Il prodotto e' presente in un ordine, spediscilo prima di eliminarlo");
            }

        repoProdotto.deleteById(idProdotto);
        return ResponseEntity.status(200).body("Prodotto eliminato");

    }

}
