package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Repository.ProdottoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuratoreService {
    private ProdottoRepository prodottoRepository;

    public CuratoreService(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }

    public ResponseEntity<String> approva(Long idProdotto){
        if(idProdotto == null){
            return ResponseEntity.status(404).body("id prodotto nullo");
        }

        if(!prodottoRepository.existsById(idProdotto)){
            return ResponseEntity.status(404).body("Prodotto non trovato");
        }

        Prodotto prodotto = prodottoRepository.findById(idProdotto).get();
        prodotto.setApprovato();
        prodottoRepository.save(prodotto);
        return ResponseEntity.ok().body("Prodotto approvato");
    }

    public ResponseEntity<String> rifiuta(Long idProdotto){
        if(idProdotto == null){
            return ResponseEntity.status(404).body("id prodotto nullo");
        }

        if(!prodottoRepository.existsById(idProdotto)){
            return ResponseEntity.status(404).body("Prodotto non trovato");
        }

        prodottoRepository.deleteById(idProdotto);
        return ResponseEntity.ok().body("Prodotto rifiutato");
    }

    public List<Prodotto> getRichieste(){
        return prodottoRepository.findAllByApprovatoIs(false);
    }

    public ResponseEntity<String> approvaTutti() {
        for(Prodotto p : prodottoRepository.findAllByApprovatoIs(false)) {
            p.setApprovato();
            prodottoRepository.save(p);
        }

    return ResponseEntity.ok("Tutti i prodotti sono stati approvati");
    }
}
