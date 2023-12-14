package controller;
import model.Profissional;
import helpers.ValidadorDeCpfouCnpj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfissionalController {
    Connection connection;

    public ProfissionalController(Connection connection) {
        this.connection = connection;
    }

    public Profissional create(Profissional dados) {
        try {

            if (!ValidadorDeCpfouCnpj.isValid(dados.getCpf_cnpj())) {
                throw new Error("Digite um cpf ou cnpj válido");
            }

            boolean verify = false;

            try {
                verify = findByCpfCnpj(dados.getCpf_cnpj()) != null;
            } catch (Error ignore) {
            }

            if (verify) {
                throw new Error("Já existe um profissional com este cpf/cnpj");
            }

            String query = "INSERT INTO Profissional (nome, cpf_cnpj) VALUES (?, ?) RETURNING id";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, dados.getNome());
            preparedStatement.setString(2, dados.getCpf_cnpj());

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

    public Profissional findById(Integer id) {
        try{
            String query = "SELECT * FROM profissional WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                throw new Error("Não foi encontrado nenhum profissional.");
            }

            Profissional profissional = null;

            while (result.next()) {
                profissional = new Profissional();

                profissional.setId(result.getInt("id"));
                profissional.setNome(result.getString("nome"));
                profissional.setCpf_cnpj(result.getString("cpf_cnpj"));
            }

            return profissional;
        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }

    public Profissional findByCpfCnpj(String cpf){
        try{

            String query = "SELECT * FROM Profissional WHERE cpf_cnpj = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cpf);

            ResultSet result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                throw new Error("Não foi encontrado nenhum profissional.");
            }

            Profissional profissional = null;

            while (result.next()) {
                profissional = new Profissional();

                profissional.setId(result.getInt("id"));
                profissional.setNome(result.getString("nome"));
                profissional.setCpf_cnpj(result.getString("cpf_cnpj"));
            }

            return profissional;

        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }

    public ArrayList<Profissional> listAll() {
        try{
            String query = "SELECT * FROM profissional";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                throw new Error("Não foi encontrado nenhum profissional.");
            }

            ArrayList<Profissional> profissionalArrayList = new ArrayList<>();
            Profissional profissional = null;

            while (result.next()) {
                profissional = new Profissional();

                profissional.setId(result.getInt("id"));
                profissional.setNome(result.getString("nome"));
                profissional.setCpf_cnpj(result.getString("cpf_cnpj"));

                profissionalArrayList.add(profissional);
            }

            return profissionalArrayList;

        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }

}
