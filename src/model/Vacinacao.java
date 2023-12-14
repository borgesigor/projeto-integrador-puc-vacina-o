package model;

import java.sql.Date;

public class Vacinacao {
    Integer id;
    Date date;
    Integer id_ampola;
    Integer id_profissional;
    Integer id_paciente;
    String observacoes;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setId_ampola(Integer id_ampola) {
        this.id_ampola = id_ampola;
    }

    public Integer getId_ampola() {
        return id_ampola;
    }

    public void setId_profissional(Integer id_profissional) {
        this.id_profissional = id_profissional;
    }

    public Integer getId_profissional() {
        return id_profissional;
    }

    public void setId_paciente(Integer id_paciente) {
        this.id_paciente = id_paciente;
    }

    public Integer getId_paciente() {
        return id_paciente;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getObservacoes() {
        return observacoes;
    }
}
