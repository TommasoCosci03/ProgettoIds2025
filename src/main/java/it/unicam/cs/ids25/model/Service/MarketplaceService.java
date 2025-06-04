package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Dto.ProdottoDTO;
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

    public List<ProdottoDTO> getProdottiInVendita() {
        List<ProdottoDTO> prodottiDTO = new ArrayList<>();
        ProdottoDTO prodottoDTO = new ProdottoDTO();
        for (Prodotto prodotto : prodottoRepository.findAll()) {
            if (prodotto.isApprovato()) {
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

    public Prodotto getProdottoById(Long id) {
        if(prodottoRepository.findById(id).get().isApprovato())
            return prodottoRepository.findById(id).get();
        else
            return null;
    }

}
