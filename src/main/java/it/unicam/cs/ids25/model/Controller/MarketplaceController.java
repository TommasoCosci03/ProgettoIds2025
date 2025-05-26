package it.unicam.cs.ids25.model.Controller;

import it.unicam.cs.ids25.model.Repository.ProdottoRepository;
import it.unicam.cs.ids25.model.Service.MarketplaceService;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marketplace")
public class MarketplaceController {
    private final MarketplaceService service;
    private final ProdottoRepository prodottoRepo;

    public MarketplaceController(MarketplaceService service, ProdottoRepository prodottoRepo) {
        this.service = service;
        this.prodottoRepo = prodottoRepo;
    }

    @GetMapping("/prodottiInVendita")
    public List<Prodotto> getProdottiInVendita(){
        return service.getProdottiInVendita().stream().toList();
    }

    @GetMapping("/prodotto/{id}")
    public ResponseEntity<Prodotto> getProdotto(@PathVariable("id") Long id){
        if(prodottoRepo.existsById(id)){
            return ResponseEntity.ok(service.getProdottoById(id));
        }else
            return ResponseEntity.status(404).body(null);
    }
}
