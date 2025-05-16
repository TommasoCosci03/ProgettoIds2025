package it.unicam.cs.ids25.model.observer;
import it.unicam.cs.ids25.model.Ordine;

public interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyAziende();
}
