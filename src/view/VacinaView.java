package view;

import model.Vacina;

public class VacinaView {
    public VacinaView(Vacina result){
        System.out.println("id: "+result.getId());
        System.out.println("nome: "+result.getNome());
        System.out.println("fabricante: "+result.getFabricante());
        System.out.println("país de origem: "+result.getPais_origem());
    }
}
