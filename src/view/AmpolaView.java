package view;

import model.Ampola;

public class AmpolaView {
    public AmpolaView(Ampola result){
        System.out.println("id: "+result.getId());
        System.out.println("quantidade de doses: "+result.getQuantidade_doses());
    }
}
