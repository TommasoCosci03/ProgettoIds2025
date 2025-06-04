package it.unicam.cs.ids25.model;

import it.unicam.cs.ids25.model.Observer.Observer;
import it.unicam.cs.ids25.model.Observer.Subject;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.HashMap;

@Entity
public class Ordine implements Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "acquirenteId")
    private Acquirente acquirente;
    @ElementCollection
    @CollectionTable(
            name = "carrello_prodotti",
            joinColumns = @JoinColumn(name = "carrello_id")
    )
    @MapKeyJoinColumn(name = "prodotto_id")
    @Column(name = "quantita")
    private HashMap<Prodotto, Integer> prodottiAcquistati;
    private String indirizzo;
    @Transient
    private ArrayList<Observer> observer = new ArrayList<>();

    public Ordine(Acquirente acquirente, HashMap<Prodotto, Integer> prodottiAcquistati, String indirizzo) {
        this.acquirente = acquirente;
        this.prodottiAcquistati = prodottiAcquistati;
        this.indirizzo = indirizzo;
    }

    public Ordine() {

    }

    public HashMap<Prodotto, Integer> getProdottiAcquistati() {
        return prodottiAcquistati;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public Acquirente getAcquirente() {
        return acquirente;
    }

    public void aziendeOrdine(HashMap<Prodotto, Integer> prodottiAcquistati){
        for(Prodotto p : prodottiAcquistati.keySet()){
            observer.add(p.getAzienda());
        }
    }

    @Override
    public void attach(Observer o) {
        observer.add(o);
    }

    @Override
    public void detach(Observer o) {
        observer.remove(o);
    }

    @Override
    public void notifyAziende() {
        aziendeOrdine(prodottiAcquistati);

        for(Observer o : observer) {
            o.update(this);
        }
    }

}
