package it.unicam.cs.ids25.model.Controller;


import it.unicam.cs.ids25.model.Carrello;
import it.unicam.cs.ids25.model.Dto.ProdottoOrdineDTO;
import it.unicam.cs.ids25.model.Ordine;
import it.unicam.cs.ids25.model.Service.OrdineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordine")
public class OrdineController {

    private final OrdineService ordineService;


    public OrdineController(OrdineService ordineService) {
        this.ordineService = ordineService;
    }

    @PostMapping("/aggiungiProdottoCarrello")
    public ResponseEntity<String> aggiungiProdottoCarrello(@RequestBody ProdottoOrdineDTO dto) {
        return ordineService.aggiungiAlCarrello(dto);
    }

    @PostMapping("/cancellaCarrello/{id}")
    public ResponseEntity<String> cancellaCarrello(@PathVariable("id") Long id) {
        return ordineService.cancella(id);
    }

    @GetMapping("/getCarrello/{id}")
    public ResponseEntity<String> getCarrello(@PathVariable("id") Long id) {
        return ordineService.carrello(id);
    }

}
