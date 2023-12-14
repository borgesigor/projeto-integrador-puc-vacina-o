package service;

import model.Vacina;
import helpers.Input.*;
import controller.VacinaController;
import view.VacinaView;

import java.sql.Connection;
import java.util.ArrayList;

public class VacinaService extends VacinaController {
    Connection connection;

    public VacinaService(Connection connection){
        super(connection);
        this.connection = connection;
    }

    public void start(){
        Painel painel = new Painel();

        painel.setTitle("Painel Vacinas");
        painel.setItems("1 - \uD83D\uDD22 Listar pelo id");
        painel.setItems("2 - \uD83D\uDC64 Listar todos");
        painel.setItems("3 - ➕ Cadastrar nova vacina");
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

    public Vacina findById() {
        int id = new InputInt("Digite o id da vacina.").getInput();

        Vacina result = super.findById(id);

        new Title("Resultado:");

        new Separator();
        new VacinaView(result);
        new Separator();

        return result;
    }

    public ArrayList<Vacina> listAll() {
        ArrayList<Vacina> resultM = super.listAll();

        new Title("Resultado:");

        for (Vacina result : resultM){
            new Separator();
            new VacinaView(result);
            new Separator();
        }

        return resultM;
    }

    public Vacina create() {
        String nome = new InputStr("Nome da vacina:").getInput();
        String fabricante = new InputStr("Fabricante:").getInput();
        String paisOrigem = new InputStr("Pais de origem:").getInput();

        Vacina vacinaVirtual = new Vacina();

        vacinaVirtual.setNome(nome);
        vacinaVirtual.setPais_origem(paisOrigem);
        vacinaVirtual.setFabricante(fabricante);

        new Title("Cadastrado com sucesso!");

        return super.create(vacinaVirtual);
    }
}
