package it.unicam.cs.ids25.model.Utenti;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.cs.ids25.model.Evento;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

@Entity
@DiscriminatorValue("animatore")
public class Animatore {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @OneToMany(mappedBy = "animatore", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Evento> eventiCreati;

    public Animatore(String nome) {
        this.nome = nome;
        //this.eventiCreati = new ArrayList<>();
    }
    public Animatore() {}

    public long getId(){return id;}

    public void setId(long id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public List<Evento> getEventiCreati() {return eventiCreati;}

    public Evento creaEvento(String nome, String descrizione, String luogo, String dataEvento){
        /*Scanner scanner = new Scanner(System.in);

        System.out.println("Creazione di un nuovo evento:");
        System.out.print("Nome evento: ");
        String nome = scanner.nextLine();

        System.out.print("Descrizione: ");
        String descrizione = scanner.nextLine();

        System.out.print("luogo: ");
        String luogo = scanner.nextLine();

        System.out.print("Data: ");
        String dataEvento = scanner.nextLine();*/

         Evento evento = new Evento(nome, descrizione, luogo, dataEvento, this);
         eventiCreati.add(evento);
         return evento;

    }

    public void eliminaEvento(Evento evento){
        eventiCreati.remove(evento);
    }

    public void invitaAdEvento(Azienda a, Evento e){
        e.aggiungiInvitato(a);
    }
}
