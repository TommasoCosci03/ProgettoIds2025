package it.unicam.cs.ids25.model;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Carrello {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "carrello_id")
    private List<OrderItem> prodottiDaAcquistare = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name= "utende_id")
    private Acquirente acquirente;

    public Carrello() {}

    public List<OrderItem> getProdottiDaAcquistare() {
        return prodottiDaAcquistare;
    }

    public void setProdottiDaAcquistare(OrderItem prodotto) {
        this.prodottiDaAcquistare.add(prodotto);
    }

    public void setAcquirente(Acquirente acquirente) {
        this.acquirente = acquirente;
    }

    public double prezzoTotale(){
        double somma = 0;
        for(OrderItem p : prodottiDaAcquistare){
            somma += p.getProdotto().getPrezzo() * p.getQuantita();
        }
        return somma;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
       for (OrderItem p : prodottiDaAcquistare) {
           ret.append(p.getProdotto().getNome() + " " + p.getProdotto().getCategoria());
           ret.append(": quantità= ").append(p.getQuantita());
           ret.append("\n");
       }
       ret.append("prezzo totale= ").append(prezzoTotale());
       return ret != null ? ret.toString() : "null";
    }

}
