package it.unicam.cs.ids25.model.Utenti;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.cs.ids25.model.Evento;
import jakarta.persistence.*;

import java.util.List;
//import java.util.Scanner;

@Entity
@DiscriminatorValue("animatore")
public class Animatore {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "animatore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evento> eventicrati;


    public Animatore(String nome) {
        this.nome = nome;

    }
    public Animatore() {}

    public Long getId(){return id;}

    public void setId(Long id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public List<Evento> getEventi() {return eventicrati;}

    public Evento creaEvento(String nome, String descrizione, String luogo, String dataEvento, List<Azienda> allById){

         Evento evento = new Evento(nome, descrizione, luogo, dataEvento, allById, this);
         eventicrati.add(evento);
         return evento;

    }


}
