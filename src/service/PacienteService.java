package service;

import model.Paciente;
import helpers.ConvertDate;
import helpers.Input.*;
import controller.PacienteController;
import view.PacienteView;

import java.sql.Connection;
import java.util.ArrayList;

public class PacienteService extends PacienteController {
    Connection connection;

    public PacienteService(Connection connection){
        super(connection);
        this.connection = connection;
    }

    public void start(){
        Painel painel = new Painel();

        painel.setTitle("Painel Pacientes");
        painel.setItems("1 - \uD83D\uDD22 Listar pelo id");
        painel.setItems("2 - \uD83E\uDEAA Listar pelo Cpf ou Cnpj");
        painel.setItems("3 - \uD83D\uDC64 Listar todos");
        painel.setItems("4 - ➕ Cadastrar novo paciente");
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

    public ArrayList<Paciente> listAll() {
        ArrayList<Paciente> resultM = super.listAll();

        for (Paciente result : resultM){
            new Separator();
            new PacienteView(result);
            new Separator();
        }

        return resultM;
    }

    public Paciente findById() {
        int id = new InputInt("Digite o id do paciente:").getInput();

        Paciente result = super.findById(id);

        new Title("Resultado:");

        new Separator();
        new PacienteView(result);
        new Separator();

        return result;
    }

    public Paciente findByCpfCnpj() {
        String cpfCnpj = new InputStr("Digite o cpf ou cnpj do paciente:").getInput();

        Paciente result = super.findByCpfCnpj(cpfCnpj);

        new Title("Resultado:");

        new Separator();
        new PacienteView(result);
        new Separator();

        return result;
    }

    public Paciente create() {
        Paciente pacienteVirtual = new Paciente();

        String nome = new InputStr("Nome do paciente:").getInput();
        String dataNascimento = new InputStr("Data de nascimento: ").getInput();
        String endereco = new InputStr("Endereço: ").getInput();
        String telefone = new InputStr("Telefone: ").getInput();
        String cpfCnpj = new InputStr("Cpf ou Cnpj: ").getInput();
        int genero = new InputInt("Gênero, 1 para Masculino e 0 para feminino: ").getInput();
        boolean generoBoolean;

        if(genero == 1){
            generoBoolean = true;
        }else{
            generoBoolean = false;
        }

        pacienteVirtual.setNome(nome);
        pacienteVirtual.setData_nascimento(ConvertDate.ConvertDate(dataNascimento));
        pacienteVirtual.setEndereco(endereco);
        pacienteVirtual.setTelefone(telefone);
        pacienteVirtual.setCpf_cnpj(cpfCnpj);
        pacienteVirtual.setGenero(generoBoolean);

        new Title("Cadastrado com sucesso!");

        return super.create(pacienteVirtual);
    }
}
