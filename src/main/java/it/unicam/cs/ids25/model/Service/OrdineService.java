package it.unicam.cs.ids25.model.Service;


import it.unicam.cs.ids25.model.Carrello;
import it.unicam.cs.ids25.model.Dto.AcquirenteDTO;
import it.unicam.cs.ids25.model.Dto.ProdottoDTO;
import it.unicam.cs.ids25.model.Dto.ProdottoOrdineDTO;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Repository.AcquirenteRepository;
import it.unicam.cs.ids25.model.Repository.OrdineRepository;
import it.unicam.cs.ids25.model.Repository.ProdottoRepository;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Transactional
public class OrdineService {

    public OrdineService(AcquirenteRepository acquirenteRepository, ProdottoRepository prodottoRepository, OrdineRepository ordineRepository) {
        this.acquirenteRepository = acquirenteRepository;
        this.prodottoRepository = prodottoRepository;
        this.ordineRepository = ordineRepository;
    }

    private AcquirenteRepository acquirenteRepository;
    private ProdottoRepository prodottoRepository;
    private OrdineRepository ordineRepository;

    public ResponseEntity<String> aggiungiAlCarrello(ProdottoOrdineDTO dto) {
        Acquirente acquirente = acquirenteRepository.findById(dto.getIdAcquirente()).get();
        Prodotto prodotto =prodottoRepository.findById(dto.getIdProdotto()).get();

        if (dto.getQuantita() > prodotto.getQuantita()){
            return ResponseEntity.status(404).body("Quantità non disponibile");
        }
        else if (!prodottoRepository.existsById(dto.getIdProdotto())){
            return ResponseEntity.status(404).body("Prodotto non esistente");
        }
        acquirente.aggiungiAlCarrello(prodotto, dto.getQuantita());
        return ResponseEntity.status(200).body(prodotto.getNome() + " Aggiunto al carrello");
    }

    public ResponseEntity<String> cancella(Long id) {
        if(!acquirenteRepository.existsById(id)){
            return ResponseEntity.status(404).body("Aquirente non esistente");
        }
        Acquirente acquirente= acquirenteRepository.findById(id).get();
        acquirente.cancellaCarrello();
        return ResponseEntity.status(200).body(acquirente.getNome()+"carrello cancellato");
    }

    public ResponseEntity<String> carrello(Long id) {
        if(!acquirenteRepository.existsById(id)){
            return ResponseEntity.status(404).body("Aquirente non esistente");
        }
        Acquirente acquirente= acquirenteRepository.findById(id).get();
        return ResponseEntity.status(200).body(acquirente.getCarrello().toString());
    }

}
