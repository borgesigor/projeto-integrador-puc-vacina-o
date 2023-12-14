package model;

import java.sql.Date;

public class Paciente {
    Integer id;
    String nome;
    Date data_nascimento;
    String endereco;
    String telefone;
    String cpf_cnpj;
    Boolean genero;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public Boolean getGenero() {
        return genero;
    }

    public String getGeneroName(){
        if(this.getGenero()){
            return "Masculino";
        }

        return "Feminino";
    }

    public void setGenero(Boolean genero) {
        this.genero = genero;
    }
}
