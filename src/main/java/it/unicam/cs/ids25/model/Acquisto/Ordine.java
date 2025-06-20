package it.unicam.cs.ids25.model.Acquisto;

import it.unicam.cs.ids25.model.Observer.Observer;
import it.unicam.cs.ids25.model.Observer.Subject;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import jakarta.persistence.*;


import java.util.ArrayList;

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
    private boolean spedito = false;

    public Ordine(Acquirente acquirente, String indirizzo) {
        this.acquirente = acquirente;
        this.prodottiAcquistati = acquirente.getCarrello();
        this.indirizzo = indirizzo;
        this.prezzo = acquirente.getCarrello().prezzoTotale();
    }

    public Ordine() {

    }

    public Long getId() {return id;}

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

    public boolean isSpedito() {
        return spedito;
    }

    public void setSpedito() {
        this.spedito = true;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
}
