package view;

import model.Lote;

public class LoteView {

    public LoteView(Lote result){
        System.out.println("id: "+result.getId());
        System.out.println("fabricacao: "+result.getData_fabricacao());
        System.out.println("validade: "+result.getData_validade());
        System.out.println("remetente: "+result.getRemetente());
        System.out.println("armazenamento recomendado: "+result.getArmazenamento_recomendado());
    }

}
