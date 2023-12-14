package model;

public class Ampola {
    Integer id;
    Integer quantidade_doses;
    Integer id_vacina;
    Integer id_lote;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setQuantidade_doses(Integer quantidade_doses) {
        this.quantidade_doses = quantidade_doses;
    }

    public Integer getQuantidade_doses() {
        return quantidade_doses;
    }

    public void setId_vacina(Integer id_ampola) {
        this.id_vacina = id_ampola;
    }

    public Integer getId_vacina() {
        return id_vacina;
    }

    public void setId_lote(Integer id_lote) {
        this.id_lote = id_lote;
    }

    public Integer getId_lote() {
        return id_lote;
    }
}
