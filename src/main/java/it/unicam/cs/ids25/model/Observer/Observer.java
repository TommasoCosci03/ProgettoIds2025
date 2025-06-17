package it.unicam.cs.ids25.model.Observer;
import it.unicam.cs.ids25.model.Acquisto.Ordine;

public interface Observer {
    void update(Ordine ordine);
}
