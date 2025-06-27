package it.unicam.cs.ids25.model.Utenti;

import it.unicam.cs.ids25.model.Autenticazione.Utente;
import it.unicam.cs.ids25.model.Evento;
import jakarta.persistence.*;
import java.util.List;

/**
 * Rappresenta un animatore nel sistema.
 * Un animatore è un {@link Utente} che può organizzare eventi ed invitare aziende a tale evento.
 */
@Entity
@DiscriminatorValue("animatore")
public class Animatore extends Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "animatore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evento> eventicrati;


    public Animatore(String nome) {
        this.nome = nome;
    }

    public Animatore() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Evento> getEventi() {
        return eventicrati;
    }


    /**
     * Crea un nuovo evento associato a questo animatore e lo aggiunge alla lista degli eventi creati.
     *
     * @param nome        il nome dell'evento
     * @param descrizione la descrizione dell'evento
     * @param luogo       il luogo in cui si svolgerà l'evento
     * @param dataEvento  la data in cui si terrà l'evento
     * @param invitati    la lista delle aziende invitate all'evento
     * @return l'evento creato
     */
    public Evento creaEvento(String nome, String descrizione, String luogo, String dataEvento, List<Azienda> invitati) {

        Evento evento = new Evento(nome, descrizione, luogo, dataEvento, invitati, this);
        eventicrati.add(evento);
        return evento;

    }


}
