package it.unicam.cs.ids25.model.Utenti;

import it.unicam.cs.ids25.model.Acquisto.Carrello;
import it.unicam.cs.ids25.model.Acquisto.CarrelloItem;
import it.unicam.cs.ids25.model.Autenticazione.Utente;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("acquirente")
public class Acquirente extends Utente {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToOne(cascade = CascadeType.ALL)
    private Carrello carrello;
    private double saldo;

    public Acquirente(String nome) {
        this.nome = nome;
        this.carrello = new Carrello();
        this.carrello.setAcquirente(this);
        this.saldo = 0.0;
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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    public void aggiungiAlCarrello(Prodotto prodotto, int quantita){
        CarrelloItem order = new CarrelloItem(prodotto, quantita);
        carrello.setProdottiDaAcquistare(order);
    }

    public void cancellaCarrello(){
        carrello.getProdottiDaAcquistare().clear();

    }
}
