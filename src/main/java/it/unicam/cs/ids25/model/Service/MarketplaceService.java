package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Dto.ProdottoDTO;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Repository.ProdottoRepository;
import org.springframework.stereotype.Service;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;

import java.util.ArrayList;
import java.util.List;

/**
 * la classe MarketplaceService è responsabile della logica per le operazioni relative ai prodotti in vendita
 */
@Service
public class MarketplaceService {

    private final ProdottoRepository prodottoRepository;

    /**
     * Costruttore del service {@code MarketplaceService} per la gestione del marketplace dei prodotti.
     * Inietta il repository necessario per accedere ai dati dei prodotti.
     *
     * @param prodottoRepository repository per la gestione dei {@link Prodotto}
     */
    private MarketplaceService(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }


    /**
     * Recupera la lista di prodotti attualmente in vendita, cioè tutti i prodotti
     * che risultano approvati. Per ogni prodotto approvato viene creato un oggetto
     * {@link ProdottoDTO} contenente i dati rilevanti da esporre.
     *
     * @return una {@link List} di {@link ProdottoDTO} che rappresentano i prodotti
     *         approvati e disponibili per la vendita
     */
    public List<ProdottoDTO> getProdottiInVendita() {
        List<ProdottoDTO> prodottiDTO = new ArrayList<>();

        for (Prodotto prodotto : prodottoRepository.findAll()) {
            if (prodotto.isApprovato()) {
                ProdottoDTO prodottoDTO = new ProdottoDTO();
                prodottoDTO.setNome(prodotto.getNome());
                prodottoDTO.setAziendaId(prodotto.getAzienda().getId());
                prodottoDTO.setId(prodotto.getId());
                prodottoDTO.setDescrizione(prodotto.getDescrizione());
                prodottoDTO.setPrezzo(prodotto.getPrezzo());
                prodottoDTO.setQuantita(prodotto.getQuantita());
                prodottoDTO.setAziendaNome(prodotto.getAzienda().getNome());
                prodottoDTO.setCategoria(prodotto.getCategoria().toString());
                prodottiDTO.add(prodottoDTO);
            }
        }
        return prodottiDTO;
    }

    /**
     * Recupera un prodotto dato il suo ID solo se è approvato.
     * @param id l'ID del prodotto da cercare
     * @return l'istanza di {@link Prodotto}
     *
     */
    public Prodotto getProdottoById(Long id) {
        if (prodottoRepository.findById(id).get().isApprovato())
            return prodottoRepository.findById(id).get();
        else
            return null;
    }

}
