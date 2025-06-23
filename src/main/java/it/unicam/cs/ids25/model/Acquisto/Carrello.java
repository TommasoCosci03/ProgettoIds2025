package it.unicam.cs.ids25.model.Acquisto;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * La classe Carrello rappresenta il carrello degli acquisti di un {@link Acquirente}.
 */
@Entity
public class Carrello {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "carrello_id")
    private List<CarrelloItem> prodottiDaAcquistare = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name= "utende_id")
    private Acquirente acquirente;

    /**
     * Costruttore vuoto per Springboot
     */
    public Carrello() {}

    public List<CarrelloItem> getProdottiDaAcquistare() {
        return prodottiDaAcquistare;
    }

    public void setProdottiDaAcquistare(CarrelloItem prodotto) {
        this.prodottiDaAcquistare.add(prodotto);
    }

    public void setAcquirente(Acquirente acquirente) {
        this.acquirente = acquirente;
    }

    /**
     * Calcola il prezzo totale dei prodotti presenti nel carrello.
     * @return totale del carrello.
     */
    public double prezzoTotale(){
        double somma = 0;
        for(CarrelloItem p : prodottiDaAcquistare){
            somma += p.getProdotto().getPrezzo() * p.getQuantita();
        }
        return somma;
    }

    /**
     * Override del metodo toString per la visualizzazione dei prodotti presenti nel carrello.
     * @return
     */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
       for (CarrelloItem p : prodottiDaAcquistare) {
           ret.append(p.getProdotto().getNome() + " " + p.getProdotto().getCategoria());
           ret.append(": quantità = ").append(p.getQuantita());
           ret.append("\n");
       }
       ret.append("prezzo totale = ").append(prezzoTotale()).append("€");
       return ret != null ? ret.toString() : "null";
    }

}
