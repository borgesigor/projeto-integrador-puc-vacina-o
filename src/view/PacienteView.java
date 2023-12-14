package view;

import model.Paciente;

public class PacienteView {
    public PacienteView(Paciente result) {
        System.out.println("id: "+result.getId());
        System.out.println("nome: "+result.getNome());
        System.out.println("telefone: "+result.getTelefone());
        System.out.println("endereço: "+result.getEndereco());
        System.out.println("gênero: "+result.getGeneroName());
    }
}
