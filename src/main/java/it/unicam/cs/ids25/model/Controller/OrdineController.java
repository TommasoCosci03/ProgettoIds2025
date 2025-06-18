package it.unicam.cs.ids25.model.Controller;


import it.unicam.cs.ids25.model.Dto.OrdineDTO;
import it.unicam.cs.ids25.model.Dto.ProdottoOrdineDTO;
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

    @PostMapping("/cancellaCarrello")
    public ResponseEntity<String> cancellaCarrello() {
        return ordineService.cancellaCarrello();
    }

    @GetMapping("/getCarrello")
    public ResponseEntity<String> getCarrello() {
        return ordineService.getCarrello();
    }

    @PostMapping("/effettuaOrdine")
    public ResponseEntity<String> effettuaOrdine(@RequestBody OrdineDTO dto) {
        return ordineService.effettuaOrdine(dto);

    }

}
