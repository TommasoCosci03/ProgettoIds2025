package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Repository.ProdottoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuratoreService {
    private ProdottoRepository prodottoRepository;

    public CuratoreService(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }

    public void approva(Long idProdotto){
        Prodotto prodotto = prodottoRepository.findById(idProdotto).get();
        prodotto.setApprovato();
        prodottoRepository.save(prodotto);
    }

    public void rifiuta(Long idProdotto){
        prodottoRepository.deleteById(idProdotto);
    }

    public List<Prodotto> getRichieste(){
        return prodottoRepository.findAllByApprovatoIs(false);
    }
}
