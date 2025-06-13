package it.unicam.cs.ids25.model;

import it.unicam.cs.ids25.model.Observer.Observer;
import it.unicam.cs.ids25.model.Observer.Subject;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Repository.ProdottoRepository;
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
    @Transient
    private Carrello prodottiAcquistati;
    private double prezzo;
    private String indirizzo;
    @Transient
    private ArrayList<Observer> observer = new ArrayList<>();
    private String prodottiList;

    public Ordine(Acquirente acquirente, String indirizzo) {
        this.acquirente = acquirente;
        this.prodottiAcquistati = acquirente.getCarrello();
        this.indirizzo = indirizzo;
        this.prezzo = acquirente.getCarrello().prezzoTotale();
    }

    public Ordine() {

    }



    public String getIndirizzo() {
        return indirizzo;
    }

    public Acquirente getAcquirente() {
        return acquirente;
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
        for(Observer o : observer) {
            o.update(this);
        }
    }

    public ArrayList<Observer> getObserver() {
        return observer;
    }

    public String getProdottiList() {
        return prodottiList;
    }

    public void setProdottiList(String prodottiList) {
        this.prodottiList = prodottiList;
    }
}
