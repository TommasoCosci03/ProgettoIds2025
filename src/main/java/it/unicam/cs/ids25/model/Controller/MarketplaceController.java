package it.unicam.cs.ids25.model.Controller;

import it.unicam.cs.ids25.model.Dto.ProdottoDTO;
import it.unicam.cs.ids25.model.Repository.ProdottoRepository;
import it.unicam.cs.ids25.model.Service.MarketplaceService;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * La classe MarketplaceController implementa tutti gli endpoint per l'interazione con il marketplace.
 */
@RestController
@RequestMapping("/api/marketplace")
public class MarketplaceController {

    private final MarketplaceService service;
    @Autowired
    private final ProdottoRepository prodottoRepo;

    /**
     * Costruttore della classe con il service e repository necessari per l'interazione con il marketplace.
     * @param service
     * @param prodottoRepo
     */
    public MarketplaceController(MarketplaceService service, ProdottoRepository prodottoRepo) {
        this.service = service;
        this.prodottoRepo = prodottoRepo;
    }

    /**
     * Metodo per la visualizzazione dei prodotti disponibili nel marketplace.
     * @return  List<ProdottoDTO> lista dei prodotti in vendita
     */
    @GetMapping("/prodottiInVendita")
    public List<ProdottoDTO> getProdottiInVendita(){
        return service.getProdottiInVendita();
    }

    /**
     * Metodo per la visualizzazione di un prodotto nel marketplace.
     * @param id del prodotto da cercare
     * @return Prodotto prodotto trovato
     */
    @GetMapping("/prodotto/{id}")
    public ResponseEntity<Prodotto> getProdotto(@PathVariable("id") Long id){
        if(prodottoRepo.existsById(id)){
            return ResponseEntity.ok(service.getProdottoById(id));
        }else
            return ResponseEntity.status(404).body(null);
    }
}
