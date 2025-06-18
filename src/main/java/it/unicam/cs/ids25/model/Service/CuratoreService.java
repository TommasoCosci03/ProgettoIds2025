package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Autenticazione.SecurityService;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Repository.CuratoreRepository;
import it.unicam.cs.ids25.model.Repository.ProdottoRepository;
import it.unicam.cs.ids25.model.Repository.UtenteRepository;
import it.unicam.cs.ids25.model.Utenti.Curatore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuratoreService {
    @Autowired
    private final ProdottoRepository prodottoRepository;

    @Autowired
    private final UtenteRepository utenteRepository;

    @Autowired
    private final CuratoreRepository curatoreRepository;

    private final SecurityService securityService;

    public CuratoreService(ProdottoRepository prodottoRepository, UtenteRepository utenteRepository, CuratoreRepository curatoreRepository, SecurityService securityService) {
        this.prodottoRepository = prodottoRepository;
        this.utenteRepository = utenteRepository;
        this.curatoreRepository = curatoreRepository;
        this.securityService = securityService;
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

    public Curatore getCuratore() {
        return curatoreRepository.findByUsername("curatore");
    }

    /*public ResponseEntity<String> creaCuratore() {
        if(curatoreRepository.isEmpty()>0){
            curatoreRepository.save(new Curatore("curatore", "password"));
            return ResponseEntity.ok("Curatore creato con successo");
        }
        else{
            return ResponseEntity.ok("Curatore " + securityService.getCuratoreCorrente().getUsername());
        }
    }*/
}
