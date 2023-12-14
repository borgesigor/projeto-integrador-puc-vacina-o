package service;

import helpers.Input;
import helpers.Input.*;
import java.sql.Connection;
import external.DatabaseConnection;

public class Cli {
    ProfissionalService profissionalService;
    VacinaService vacinaService;
    AmpolaService ampolaService;
    PacienteService pacienteService;
    VacinacaoService vacinacaoService;
    LoteService loteService;
    String login;

    public Cli(){
        Connection connection = new DatabaseConnection().ConnectionDb();

        this.profissionalService = new ProfissionalService(connection);
        this.vacinaService = new VacinaService(connection);
        this.ampolaService = new AmpolaService(connection);
        this.pacienteService = new PacienteService(connection);
        this.vacinacaoService = new VacinacaoService(connection);
        this.loteService = new LoteService(connection);
    }

    public void Reiniciar(){
        new Cli().start();
    }

    public void start(){
        Input.Painel painel =  new Input.Painel();

        painel.setTitle("Painel");
        painel.setItems("1 - \uD83D\uDC68\u200D⚕\uFE0F Profissionais");
        painel.setItems("2 - \uD83D\uDC89 Vacinas");
        painel.setItems("3 - \uD83C\uDF76 Ampolas");
        painel.setItems("4 - \uD83D\uDE37 Pacientes");
        painel.setItems("5 - \uD83C\uDFE5 Vacinações");
        painel.setItems("6 - \uD83D\uDDF3 Lotes");
        painel.setItems("0 - \uD83D\uDEAA Sair do Programa");

        painel.show();

        int key = new InputInt("Selecione uma opção:").getInput();

        switch (key){
            case (1):
                profissionalService.start();
                Reiniciar();
                break;
            case(2):
                vacinaService.start();
                Reiniciar();
                break;
            case(3):
                ampolaService.start();
                Reiniciar();
                break;
            case(4):
                pacienteService.start();
                Reiniciar();
                break;
            case(5):
                vacinacaoService.start();
                Reiniciar();
                break;
            case(6):
                loteService.start();
                Reiniciar();
                break;
            case (0):
                System.exit(0);
        }
    }

}
