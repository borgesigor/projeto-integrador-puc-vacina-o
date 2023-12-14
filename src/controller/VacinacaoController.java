package controller;
import model.Vacinacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VacinacaoController {
    Connection connection;

    public VacinacaoController(Connection connection) {
        this.connection = connection;
    }

    public Vacinacao findById(Integer id) {
        try{
            String query = "SELECT * FROM vacinacao WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                throw new Error("Não foi encontrado nenhuma vacinação.");
            }

            Vacinacao vacinacao = null;

            while(result.next()){
                vacinacao = new Vacinacao();

                vacinacao.setId(result.getInt("id"));
                vacinacao.setDate(result.getDate("data"));
                vacinacao.setId_ampola(result.getInt("id_ampola"));
                vacinacao.setId_profissional(result.getInt("id_profissional"));
                vacinacao.setId_paciente(result.getInt("id_paciente"));
                vacinacao.setObservacoes(result.getString("observacoes"));

            }

            return vacinacao;
        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }

    public ArrayList<Vacinacao> findByProfissionalId(Integer id) {
        try{
            String query = "SELECT * FROM vacinacao WHERE id_profissional=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                throw new Error("Não foi encontrado nenhuma vacinação.");
            }

            ArrayList<Vacinacao> vacinacaoArrayList = new ArrayList<>();
            Vacinacao vacinacao = null;

            while(result.next()){
                vacinacao = new Vacinacao();

                vacinacao.setId(result.getInt("id"));
                vacinacao.setDate(result.getDate("data"));
                vacinacao.setId_ampola(result.getInt("id_ampola"));
                vacinacao.setId_profissional(result.getInt("id_profissional"));
                vacinacao.setId_paciente(result.getInt("id_paciente"));
                vacinacao.setObservacoes(result.getString("observacoes"));

                vacinacaoArrayList.add(vacinacao);
            }

            return vacinacaoArrayList;
        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }

    public ArrayList<Vacinacao> findByAmpolaId(Integer id) {
        try{
            String query = "SELECT * FROM vacinacao WHERE id_ampola=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                throw new Error("Não foi encontrado nenhuma vacinação.");
            }

            ArrayList<Vacinacao> vacinacaoArrayList = new ArrayList<>();
            Vacinacao vacinacao = null;

            while(result.next()){
                vacinacao = new Vacinacao();

                vacinacao.setId(result.getInt("id"));
                vacinacao.setDate(result.getDate("data"));
                vacinacao.setId_ampola(result.getInt("id_ampola"));
                vacinacao.setId_profissional(result.getInt("id_profissional"));
                vacinacao.setId_paciente(result.getInt("id_paciente"));
                vacinacao.setObservacoes(result.getString("observacoes"));

                vacinacaoArrayList.add(vacinacao);
            }

            return vacinacaoArrayList;
        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }

    public ArrayList<Vacinacao> findByPacienteId(Integer id) {
        try{
            String query = "SELECT * FROM vacinacao WHERE id_paciente=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                throw new Error("Não foi encontrado nenhuma vacinação.");
            }

            ArrayList<Vacinacao> vacinacaoArrayList = new ArrayList<>();
            Vacinacao vacinacao = null;

            while(result.next()){
                vacinacao = new Vacinacao();

                vacinacao.setId(result.getInt("id"));
                vacinacao.setDate(result.getDate("data"));
                vacinacao.setId_ampola(result.getInt("id_ampola"));
                vacinacao.setId_profissional(result.getInt("id_profissional"));
                vacinacao.setId_paciente(result.getInt("id_paciente"));
                vacinacao.setObservacoes(result.getString("observacoes"));

                vacinacaoArrayList.add(vacinacao);
            }

            return vacinacaoArrayList;
        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }

    public ArrayList<Vacinacao> listAll() {
        try{
            String query = "SELECT * FROM vacinacao";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                throw new Error("Não foi encontrado nenhuma vacinação.");
            }

            ArrayList<Vacinacao> vacinacaoArrayList = new ArrayList<>();
            Vacinacao vacinacao = null;

            while(result.next()){
                vacinacao = new Vacinacao();

                vacinacao.setId(result.getInt("id"));
                vacinacao.setDate(result.getDate("data"));
                vacinacao.setId_ampola(result.getInt("id_ampola"));
                vacinacao.setId_profissional(result.getInt("id_profissional"));
                vacinacao.setId_paciente(result.getInt("id_paciente"));
                vacinacao.setObservacoes(result.getString("observacoes"));

                vacinacaoArrayList.add(vacinacao);
            }

            return vacinacaoArrayList;
        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }

    public Vacinacao create(Vacinacao dados) {
        try{
            String query = "INSERT INTO vacinacao (data, id_ampola, id_profissional, id_paciente, observacoes) VALUES (?, ?, ?, ?, ?) RETURNING id";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setDate(1, dados.getDate());
            preparedStatement.setInt(2, dados.getId_ampola());
            preparedStatement.setInt(3, dados.getId_profissional());
            preparedStatement.setInt(4, dados.getId_paciente());
            preparedStatement.setString(5, dados.getObservacoes());

            boolean resultado = preparedStatement.execute();

            if(resultado){
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    int idGerado = resultSet.getInt("id");
                    dados.setId(idGerado);
                }
            }

            return dados;

        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }
}
