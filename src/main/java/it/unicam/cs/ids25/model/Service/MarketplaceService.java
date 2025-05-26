package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Repository.ProdottoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarketplaceService {

    private final ProdottoRepository prodottoRepository;

    private MarketplaceService(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }

    public List<Prodotto> getProdottiInVendita() {
        return prodottoRepository.findAllByApprovatoIs(true);
    }

    public Prodotto getProdottoById(Long id) {
        if(prodottoRepository.findById(id).get().isApprovato())
            return prodottoRepository.findById(id).get();
        else
            return null;
    }

}
