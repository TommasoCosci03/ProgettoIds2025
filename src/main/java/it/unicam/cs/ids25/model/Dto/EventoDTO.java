package it.unicam.cs.ids25.model.Dto;

import it.unicam.cs.ids25.model.Evento;
import it.unicam.cs.ids25.model.Utenti.Animatore;
import it.unicam.cs.ids25.model.Utenti.Azienda;

import java.util.List;

public class EventoDTO {

    private String nome;
    private String descrizione;
    private String luogo;
    private String dataEvento;
    private Long idAnimatore;
    private List<Long> aziendeInvitateId;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public Long getIdAnimatore() {
        return idAnimatore;
    }

    public void setIdAnimatore(Long idAnimatore) {
        this.idAnimatore = idAnimatore;
    }


    public List<Long> getAziendeInvitateId() {
        return aziendeInvitateId;
    }

    public void setAziendeInvitateId(List<Long> aziendeInvitateId) {
        this.aziendeInvitateId = aziendeInvitateId;
    }
}
