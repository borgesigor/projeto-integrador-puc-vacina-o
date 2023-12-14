package view;

import model.Profissional;

public class ProfissionalView {
    public ProfissionalView(Profissional result) {
        System.out.println("id: " + result.getId());
        System.out.println("nome: " + result.getNome());
        System.out.println("cpf ou cnpj: " + result.getCpf_cnpj());
    }
}
