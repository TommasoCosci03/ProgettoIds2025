package it.unicam.cs.ids25.model.Utenti;

import it.unicam.cs.ids25.model.Carrello;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("acquirente")
public class Acquirente {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Transient
    private Carrello carrello;

    public Acquirente(String nome) {
        this.nome = nome;
        this.carrello = new Carrello();
    }

    public Acquirente() {

    }
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Carrello getCarrello() {
        return carrello;
    }

    public void togliDalCarrello(Prodotto prodotto){
        carrello.getProdottiDaAcquistare().remove(prodotto);
    }

    public void cancellaCarrello(){
        carrello.getProdottiDaAcquistare().clear();
    }
}
