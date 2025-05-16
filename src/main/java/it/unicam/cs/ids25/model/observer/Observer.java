package it.unicam.cs.ids25.model.observer;
import it.unicam.cs.ids25.model.Ordine;

public interface Observer {
    void update(Ordine ordine);
}
