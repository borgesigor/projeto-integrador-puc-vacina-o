package model;

import java.sql.Date;

public class Lote {
    Integer id;
    Date data_fabricacao;
    Date data_validade;
    String remetente;
    String armazenamento_recomendado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData_fabricacao() {
        return data_fabricacao;
    }

    public void setData_fabricacao(Date data_fabricacao) {
        this.data_fabricacao = data_fabricacao;
    }

    public Date getData_validade() {
        return data_validade;
    }

    public void setData_validade(Date data_validade) {
        this.data_validade = data_validade;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getArmazenamento_recomendado() {
        return armazenamento_recomendado;
    }

    public void setArmazenamento_recomendado(String armazenamento_recomendado) {
        this.armazenamento_recomendado = armazenamento_recomendado;
    }
}
