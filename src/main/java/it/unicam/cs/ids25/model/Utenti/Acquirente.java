package it.unicam.cs.ids25.model.Utenti;

import it.unicam.cs.ids25.model.Carrello;
import it.unicam.cs.ids25.model.OrderItem;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("acquirente")
public class Acquirente {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToOne(cascade = CascadeType.ALL)
    private Carrello carrello;

    public Acquirente(String nome) {
        this.nome = nome;
        this.carrello = new Carrello();
        this.carrello.setAcquirente(this);
    }

    public Acquirente() {}
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Carrello getCarrello() {
        return carrello;
    }



    public void aggiungiAlCarrello(Prodotto prodotto, int quantita){
        OrderItem order = new OrderItem(prodotto, quantita);
        carrello.setProdottiDaAcquistare(order);
    }

    public void cancellaCarrello(){
        carrello.getProdottiDaAcquistare().clear();
    }
}
