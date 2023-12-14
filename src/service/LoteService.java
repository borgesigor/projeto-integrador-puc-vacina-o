package service;

import model.Lote;
import helpers.ConvertDate;
import helpers.Input.*;
import controller.LoteController;
import view.LoteView;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

public class LoteService extends LoteController {
    Connection connection;

    public LoteService(Connection connection){
        super(connection);
        this.connection = connection;
    }

    public void start(){
        Painel painel = new Painel();

        painel.setTitle("Painel Lote");
        painel.setItems("1 - \uD83D\uDD22 Listar pelo id");
        painel.setItems("2 - \uD83D\uDC64 Listar todos");
        painel.setItems("3 - ➕ Cadastrar novo lote");
        painel.setItems("0 - \uD83D\uDEAA Sair do Programa");

        painel.show();

        int key = new InputInt("Selecione uma opção:").getInput();

        switch (key){
            case (1):
                findById();
                break;
            case (2):
                listAll();
                break;
            case (3):
                create();
                break;
            case (0):
                System.exit(0);
        }
    }

    public Lote findById() {
        int id = new InputInt("Digite o id do lote.").getInput();

        Lote result = super.findById(id);

        new Title("Resultado:");

        new Separator();
        new LoteView(result);
        new Separator();

        return result;
    }

    public ArrayList<Lote> listAll() {
        ArrayList<Lote> resultM = super.listAll();

        new Title("Resultado:");

        for (Lote result : resultM){
            new Separator();
            new LoteView(result);
            new Separator();
        }

        return resultM;
    }

    public Lote create() {
        Lote loteVirtual = new Lote();

        String dataFabricao = new InputStr("Data de fabricacao do lote:").getInput();
        String dataValidade = new InputStr("Data de validade do lote:").getInput();

        Date dataConvertida;

        String remetente = new InputStr("Remetente do lote:").getInput();
        String armazenamentoRecomendado = new InputStr("Armazenamento recomendado:").getInput();

        loteVirtual.setData_fabricacao(ConvertDate.ConvertDate(dataFabricao));
        loteVirtual.setData_validade(ConvertDate.ConvertDate(dataValidade));

        loteVirtual.setRemetente(remetente);
        loteVirtual.setArmazenamento_recomendado(armazenamentoRecomendado);

        new Title("Cadastrado com sucesso!");

        return super.create(loteVirtual);
    }
}
