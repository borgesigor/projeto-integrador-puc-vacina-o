package service;

import model.Ampola;
import helpers.Input.*;
import controller.AmpolaController;
import controller.LoteController;
import controller.VacinaController;
import model.Lote;
import model.Vacina;
import view.AmpolaView;
import view.LoteView;
import view.VacinaView;

import java.sql.Connection;
import java.util.ArrayList;

public class AmpolaService extends AmpolaController {
    Connection connection;
    LoteController loteController;
    VacinaController vacinaController;

    public AmpolaService(Connection connection){
        super(connection);
        this.connection = connection;

        this.loteController = new LoteController(connection);
        this.vacinaController = new VacinaController(connection);
    }

    public void start(){
        Painel painel = new Painel();

        painel.setTitle("Painel Ampola");
        painel.setItems("1 - \uD83D\uDD22 Listar pelo id");
        painel.setItems("2 - \uD83D\uDC64 Listar todas");
        painel.setItems("3 - \uD83D\uDC64 Listar pelo id da vacina");
        painel.setItems("4 - \uD83D\uDC64 Listar pelo id do lote");
        painel.setItems("5 - ➕ Cadastrar nova ampola");
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
                findAllByVacinaId();
                break;
            case (4):
                findAllByLoteId();
                break;
            case (5):
                create();
                break;
            case (0):
                System.exit(0);
        }
    }

    public ArrayList<Ampola> listAll() {

        ArrayList<Ampola> resultM = super.listAll();

        new Title("Resultado:");

        for (Ampola result : resultM){
            Vacina vacinaResult = vacinaController.findById(result.getId_vacina());
            Lote loteResult = loteController.findById(result.getId_lote());

            new Separator();
            new List("Informações da Ampola");
            new AmpolaView(result);
            new List("Informações da Vacina");
            new VacinaView(vacinaResult);
            new List("Informações do Lote");
            new LoteView(loteResult);
            new Separator();
        }

        return resultM;
    }

    public Ampola findById() {
        int id = new InputInt("Digite o id da ampola.").getInput();

        Ampola result = super.findById(id);

        Vacina vacinaResult = vacinaController.findById(result.getId_vacina());
        Lote loteResult = loteController.findById(result.getId_lote());

        new Title("Resultado:");

        new Separator();
        new List("Informações da Ampola");
        new AmpolaView(result);
        new List("Informações da Vacina");
        new VacinaView(vacinaResult);
        new List("Informações do Lote");
        new LoteView(loteResult);
        new Separator();

        return result;
    }

    public ArrayList<Ampola> findAllByLoteId() {
        int id = new InputInt("Digite o identificador do lote:").getInput();

        ArrayList<Ampola> resultM = super.findAllByLoteId(id);

        Lote loteResult = loteController.findById(id);

        new List("Informações do Lote");
        new LoteView(loteResult);

        new Title("Resultado:");

        for (Ampola result : resultM){
            Vacina vacinaResult = vacinaController.findById(result.getId_vacina());

            new Separator();
            new List("Informações da Ampola");
            new AmpolaView(result);
            new List("Informações da Vacina");
            new VacinaView(vacinaResult);
            new Separator();
        }

        return resultM;
    }

    public ArrayList<Ampola> findAllByVacinaId() {
        int id = new InputInt("Digite o identificador da vacina:").getInput();

        ArrayList<Ampola> resultM = super.findAllByVacinaId(id);

        Vacina vacinaResult = vacinaController.findById(id);

        new List("Informações da Vacina");
        new VacinaView(vacinaResult);

        new Title("Resultado:");

        for (Ampola result : resultM){
            Lote loteResult = loteController.findById(result.getId_lote());

            new Separator();
            new List("Informações da Ampola");
            new AmpolaView(result);
            new List("Informações do Lote");
            new LoteView(loteResult);
            new Separator();
        }

        return resultM;
    }

    public Ampola create() {
        Ampola ampolaVirtual = new Ampola();

        int idLote = new InputInt("Digite o identificador do lote:").getInput();

        try {
            loteController.findById(idLote);
        } catch (Error e) {
            new Warning("Cadastre um novo lote primeiramente");
            idLote = new LoteService(connection).create().getId();
        }

        int idVacina = new InputInt("Digite o identificador da vacina:").getInput();

        try {
            vacinaController.findById(idVacina);
        } catch (Error ignore) {
            new Warning("Cadastre uma nova vacina primeiramente");
            idVacina = new VacinaService(connection).create().getId();
        }

        int qtdDoses = new InputInt("Digite a quantidade de doses que possui a ampola:").getInput();

        ampolaVirtual.setQuantidade_doses(qtdDoses);
        ampolaVirtual.setId_vacina(idVacina);
        ampolaVirtual.setId_lote(idLote);

        new Title("Cadastrado com sucesso!");

        return super.create(ampolaVirtual);
    }
}
