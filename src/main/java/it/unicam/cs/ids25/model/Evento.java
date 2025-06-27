package it.unicam.cs.ids25.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import it.unicam.cs.ids25.model.Utenti.Animatore;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta un evento organizzato da un {@link Animatore}.
 * Un evento è caratterizzato da nome, descrizione, luogo, data, una lista di aziende invitate
 * e una lista di acquirenti partecipanti.
 * Gli eventi sono creati e gestiti da un animatore.
 */
@Entity
@DiscriminatorValue("evento")
@JsonIgnoreProperties("invitati")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descrizione;
    private String luogo;
    private String dataEvento;

    public List<Azienda> getInvitati() {
        return invitati;
    }

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
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


    /**
     * Costruttore per creare un nuovo evento.
     *
     * @param nome        il nome dell'evento
     * @param descrizione la descrizione dell'evento
     * @param luogo       il luogo in cui si svolge l'evento
     * @param dataEvento  la data dell'evento (es. "2025-07-12")
     * @param invitati    la lista di aziende invitate
     * @param animatore   l'animatore che ha creato l'evento
     */
    public Evento(String nome, String descrizione, String luogo, String dataEvento, List<Azienda> invitati, Animatore animatore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.luogo = luogo;
        this.dataEvento = dataEvento;
        this.invitati = invitati;
        this.partecipanti = new ArrayList<>();
        this.animatore = animatore;
    }

    public Evento() {
    }

    //metodi getter

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getLuogo() {
        return luogo;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public List<Long> getInvitatiId() {
        return invitati.stream().map(Azienda::getId).toList();
    }

    public List<Long> getPartecipantiId() {
        return partecipanti.stream().map(Acquirente::getId).toList();
    }

    public Animatore getAnimatore() {
        return animatore;
    }

//metodi setter

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public void setAnimatore(Animatore animatore) {
        this.animatore = animatore;
    }

    public void setInvitati(List<Azienda> invitati) {
        this.invitati = invitati;
    }

    public void setPartecipanti(List<Acquirente> partecipanti) {
        this.partecipanti = partecipanti;
    }

    public List<Acquirente> getPartecipanti() {
        return partecipanti;
    }
}
