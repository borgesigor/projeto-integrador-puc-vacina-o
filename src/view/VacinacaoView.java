package view;

import model.Vacinacao;

public class VacinacaoView {
    public VacinacaoView(Vacinacao result){
        System.out.println("id: "+ result.getId());
        System.out.println("data da vacinação: "+ result.getDate());
        System.out.println("observações: "+ result.getObservacoes());
    }
}
