package service;

import model.Profissional;
import controller.ProfissionalController;
import helpers.Input;
import helpers.Input.*;
import view.ProfissionalView;

import java.sql.Connection;
import java.util.ArrayList;

public class ProfissionalService extends ProfissionalController {
    Connection connection;

    public ProfissionalService(Connection connection){
        super(connection);
        this.connection = connection;
    }

    public void start(){
        Painel painel = new Input.Painel();

        painel.setTitle("Painel Profissionais");
        painel.setItems("1 - \uD83D\uDD22 Listar pelo id");
        painel.setItems("2 - \uD83E\uDEAA Listar pelo Cpf ou Cnpj");
        painel.setItems("3 - \uD83D\uDC64 Listar todos");
        painel.setItems("4 - ➕ Cadastrar novo profissional");
        painel.setItems("0 - \uD83D\uDEAA Sair do Programa");

        painel.show();

        int key = new InputInt("Selecione uma opção:").getInput();

        switch (key){
            case (1):
                findById();
                break;
            case (2):
                findByCpfCnpj();
                break;
            case (3):
                listAll();
                break;
            case (4):
                create();
                break;
            case (0):
                System.exit(0);
        }
    }

    public Profissional findById() {
        int id = new InputInt("Digite um id.").getInput();

        Profissional result = super.findById(id);

        new Title("Resultado:");

        new Separator();
        new ProfissionalView(result);
        new Separator();

        return result;
    }

    public Profissional findByCpfCnpj() {
        String cpf = new InputStr("Digite um cpf ou cnpj.").getInput();

        Profissional result = super.findByCpfCnpj(cpf);

        new Title("Resultado:");

        new Separator();
        new ProfissionalView(result);
        new Separator();

        return result;
    }

    public ArrayList<Profissional> listAll() {
        ArrayList<Profissional> resultM = super.listAll();

        new Title("Resultado:");

        for (Profissional result : resultM){
            new Separator();
            new ProfissionalView(result);
            new Separator();
        }

        return resultM;
    }

    public Profissional create() {
        String nome = new InputStr("Digite um nome.").getInput();
        String cpfCnpj = new InputStr("Digite um cpf ou cnpj válido.").getInput();

        Profissional virtual = new Profissional();

        virtual.setNome(nome);
        virtual.setCpf_cnpj(cpfCnpj);

        new Title("Cadastrado com sucesso!");

        return super.create(virtual);
    }
}
