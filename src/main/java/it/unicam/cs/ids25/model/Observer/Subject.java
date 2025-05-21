package it.unicam.cs.ids25.model.Observer;

public interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyAziende();
}
