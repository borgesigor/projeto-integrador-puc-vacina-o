package service;

import model.*;
import helpers.Input;
import helpers.Input.*;
import controller.*;
import view.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

public class VacinacaoService extends VacinacaoController {
    Connection connection;
    PacienteController pacienteController;
    ProfissionalController profissionalController;
    AmpolaController ampolaController;
    VacinaController vacinaController;
    LoteController loteController;

    public VacinacaoService(Connection connection){
        super(connection);
        this.connection = connection;

        this.pacienteController = new PacienteController(connection);
        this.profissionalController = new ProfissionalController(connection);
        this.ampolaController = new AmpolaController(connection);
        this.vacinaController = new VacinaController(connection);
        this.loteController = new LoteController(connection);
    }

    public void start(){
        Input.Painel painel = new Input.Painel();

        painel.setTitle("Painel Vacinações");
        painel.setItems("1 - \uD83D\uDD22 Listar pelo id");
        painel.setItems("2 - \uD83E\uDEAA Listar pelo id do profissional");
        painel.setItems("3 - \uD83E\uDEAA Listar pelo id do paciente");
        painel.setItems("4 - \uD83E\uDEAA Listar pelo id da ampola");
        painel.setItems("5 - \uD83D\uDC64 Listar todas");
        painel.setItems("6 - ➕ Cadastrar nova vacinação");
        painel.setItems("0 - \uD83D\uDEAA Sair do Programa");

        painel.show();

        int key = new Input.InputInt("Selecione uma opção:").getInput();

        switch (key){
            case (1):
                findById();
                break;
            case (2):
                findByProfissionalId();
                break;
            case (3):
                findByPacienteId();
                break;
            case (4):
                findByAmpolaId();
                break;
            case (5):
                listAll();
                break;
            case (6):
                create();
                break;
            case(0):
                System.exit(0);
        }
    }

    public void defaultList(Vacinacao result){
        Paciente paciente = pacienteController.findById(result.getId_paciente());
        Profissional profissional = profissionalController.findById(result.getId_profissional());
        Ampola ampola = ampolaController.findById(result.getId_ampola());
        Vacina vacina = vacinaController.findById(ampola.getId_vacina());
        Lote lote = loteController.findById(ampola.getId_lote());

        new Separator();
        new List("Informações da Vacinação");
        new VacinacaoView(result);
        new List("Informações do Paciente");
        new PacienteView(paciente);
        new List("Informações do Profissional");
        new ProfissionalView(profissional);
        new List("Informações da Ampola");
        new AmpolaView(ampola);
        new List("Informações do Lote da Ampola");
        new LoteView(lote);
        new List("Informações da Vacina");
        new VacinaView(vacina);
        new Separator();
    }

    public Vacinacao findById() {
        int id = new InputInt("Digite um id.").getInput();

        Vacinacao result = super.findById(id);

        new Title("Resultado:");

        defaultList(result);

        return result;
    }

    public ArrayList<Vacinacao> findByProfissionalId() {
        int id = new InputInt("Digite o id do profissional.").getInput();

        ArrayList<Vacinacao> resultM = super.findByProfissionalId(id);

        for (Vacinacao result : resultM){
            defaultList(result);
        }

        return resultM;
    }

    public ArrayList<Vacinacao> findByPacienteId() {
        int id = new InputInt("Digite o id do paciente.").getInput();

        ArrayList<Vacinacao> resultM = super.findByPacienteId(id);

        for (Vacinacao result : resultM){
            defaultList(result);
        }

        return resultM;
    }

    public ArrayList<Vacinacao> findByAmpolaId() {
        int id = new InputInt("Digite o id da ampola.").getInput();

        ArrayList<Vacinacao> resultM = super.findByAmpolaId(id);

        for (Vacinacao result : resultM){
            defaultList(result);
        }

        return resultM;
    }

    public ArrayList<Vacinacao> listAll() {
        ArrayList<Vacinacao> resultM = super.listAll();

        for (Vacinacao result : resultM){
            defaultList(result);
        }

        return resultM;
    }

    public Vacinacao create() {
        int idAmpola = new InputInt("Digite o indentificador da ampola:").getInput();

        try {
            ampolaController.findById(idAmpola);
        } catch (Error e) {
            new Warning("Cadastre um nova ampola primeiramente");
            idAmpola = new AmpolaService(connection).create().getId();
        }

        String paciente = new InputStr("Digite o cpf ou cnpj do paciente:").getInput();

        try {
            pacienteController.findByCpfCnpj(paciente);
        } catch (Error e) {
            new Warning("Cadastre um novo paciente primeiramente");
            paciente = new PacienteService(connection).create().getCpf_cnpj();
        }

        int idPaciente = pacienteController.findByCpfCnpj(paciente).getId();

        String profissional = new InputStr("Digite o cpf ou cnpj do profissional em operação:").getInput();

        try {
            profissionalController.findByCpfCnpj(profissional);
        } catch (Error e) {
            new Warning("Cadastre um novo profissional primeiramente");
            profissional = new ProfissionalService(connection).create().getCpf_cnpj();
        }

        int idProfissional = profissionalController.findByCpfCnpj(profissional).getId();

        String observacao = new InputStr("Adicionar observações sobre a vacinação:").getInput();

        java.util.Date utilDate = new java.util.Date();
        Date dataVacinacao = new Date(utilDate.getTime());

        Vacinacao vacinaVirtual = new Vacinacao();

        vacinaVirtual.setDate(dataVacinacao);
        vacinaVirtual.setObservacoes(observacao);
        vacinaVirtual.setId_ampola(idAmpola);
        vacinaVirtual.setId_paciente(idPaciente);
        vacinaVirtual.setId_profissional(idProfissional);

        new Title("Cadastrado com sucesso!");

        return super.create(vacinaVirtual);
    }
}
