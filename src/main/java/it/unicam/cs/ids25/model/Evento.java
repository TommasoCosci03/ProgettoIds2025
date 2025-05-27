package it.unicam.cs.ids25.model;

import it.unicam.cs.ids25.model.Utenti.Acquirente;
import it.unicam.cs.ids25.model.Utenti.Animatore;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import it.unicam.cs.ids25.model.Utenti.Utente;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue( "evento")
public class Evento {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descrizione;
    private String luogo;
    private String dataEvento;

    @ManyToMany
    @JoinTable(
            name = "evento_invitati",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "azienda_id")
    )
    private List<Azienda> invitati;

    @ManyToMany
    @JoinTable(
            name = "evento_partecipanti",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "utente_id")
    )
    private List<Acquirente> partecipanti;

    @ManyToOne
    @JoinColumn(name = "animatore_id", nullable = false)
    private Animatore animatore;

    public Evento(String nome, String descrizione, String luogo, String dataEvento, Animatore animatore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.luogo = luogo;
        this.dataEvento = dataEvento;
        this.invitati = new ArrayList<>();
        this.partecipanti = new ArrayList<>();
        this.animatore = animatore;
    }

    public Evento() {   }

    //metodi getter
    public Long getId() {return id;}

    public String getNome() {return nome;}

    public String getDescrizione() {return descrizione;}

    public String getLuogo() {return luogo;}

    public String getDataEvento() {return dataEvento;}

    public List<Azienda> getInvitati() {return invitati;}

    public List<Acquirente> getPartecipanti() {return partecipanti;}

    public Animatore getAnimatore() {return animatore;}

//metodi setter
    public void setNome(String nome) {this.nome = nome;}

    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}

    public void setLuogo(String luogo) {this.luogo = luogo;}

    public void setDataEvento(String dataEvento) {this.dataEvento = dataEvento;}


    public void setAnimatore(Animatore animatore) {
        this.animatore = animatore;
    }

    public void aggiungiInvitato(Azienda a){invitati.add(a);}
}
