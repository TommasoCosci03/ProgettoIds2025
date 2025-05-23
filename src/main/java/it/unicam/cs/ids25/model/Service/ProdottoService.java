package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Dto.ProdottoSingoloDTO;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Prodotti.ProdottoSingolo;
import it.unicam.cs.ids25.model.Repository.AziendaRepository;
import it.unicam.cs.ids25.model.Repository.ProdottoRepository;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProdottoService {

    private final ProdottoRepository repoProdotto;
    private final AziendaRepository repoAzienda;

    @Autowired
    public ProdottoService(ProdottoRepository repo, ProdottoRepository repoProdotto, AziendaRepository repoAzienda) {
        this.repoProdotto = repoProdotto;
        this.repoAzienda = repoAzienda;
    }

    public Prodotto crea(ProdottoSingoloDTO dto) {
        Azienda azienda = repoAzienda.findById(dto.getIdAzienda()).get();
        Prodotto prodotto = azienda.creaProdottoAzienda(dto.getNome(), dto.getDescrizione(), dto.getPrezzo(),
                dto.getQuantita(), dto.getCategoria(), dto.getCertificazioni());
        return repoProdotto.save(prodotto);
    }

    public List<Prodotto> trovaTutti() {
        return repoProdotto.findAll();
    }

    public Prodotto trova(Long id) {
        return repoProdotto.findById(id).orElse(null);
    }

    public void elimina(Long id) {
        repoProdotto.deleteById(id);
    }
}
