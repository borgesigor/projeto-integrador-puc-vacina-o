package controller;

import model.Paciente;
import helpers.ValidadorDeCpfouCnpj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PacienteController {
    Connection connection;

    public PacienteController(Connection connection) {
        this.connection = connection;
    }

    public Paciente create(Paciente dados){
        try{

            if(!ValidadorDeCpfouCnpj.isValid(dados.getCpf_cnpj())){
                throw new Error("Digite um cpf ou cnpj válido");
            }

            boolean verify = false;

            try {
                verify = findByCpfCnpj(dados.getCpf_cnpj()) != null;
            }catch (Error ignore){}

            if(verify){
                throw new Error("Já existe um paciente com este cpf/cnpj");
            }

            String query = "INSERT INTO paciente (nome, data_nascimento, endereco, telefone, cpf_cnpj, genero) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, dados.getNome());
            preparedStatement.setDate(2, dados.getData_nascimento());
            preparedStatement.setString(3, dados.getEndereco());
            preparedStatement.setString(4, dados.getTelefone());
            preparedStatement.setString(5, dados.getCpf_cnpj());
            preparedStatement.setBoolean(6, dados.getGenero());

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

    public Paciente findById(Integer id) {
        try{
            String query = "SELECT * FROM paciente WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                throw new Error("Não foi encontrado nenhum paciente.");
            }

            Paciente paciente = null;

            while (result.next()) {
                paciente = new Paciente();

                paciente.setId(result.getInt("id"));
                paciente.setNome(result.getString("nome"));
                paciente.setCpf_cnpj(result.getString("cpf_cnpj"));
                paciente.setData_nascimento(result.getDate("data_nascimento"));
                paciente.setEndereco(result.getString("endereco"));
                paciente.setTelefone(result.getString("telefone"));
                paciente.setGenero(result.getBoolean("genero"));
            }

            return paciente;
        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }

    public Paciente findByCpfCnpj(String cpfCnpj) {
        try{

            String query = "SELECT * FROM paciente WHERE cpf_cnpj = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cpfCnpj);

            ResultSet result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                throw new Error("Não foi encontrado nenhum paciente.");
            }

            Paciente paciente = null;

            while (result.next()) {
                paciente = new Paciente();

                paciente.setId(result.getInt("id"));
                paciente.setNome(result.getString("nome"));
                paciente.setCpf_cnpj(result.getString("cpf_cnpj"));
                paciente.setData_nascimento(result.getDate("data_nascimento"));
                paciente.setEndereco(result.getString("endereco"));
                paciente.setTelefone(result.getString("telefone"));
                paciente.setGenero(result.getBoolean("genero"));
            }

            return paciente;

        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }

    public ArrayList<Paciente> listAll(){
        try{
            String query = "SELECT * FROM paciente";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                throw new Error("Não foi encontrado nenhum paciente.");
            }

            ArrayList<Paciente> pacienteArrayList = new ArrayList<>();
            Paciente paciente = null;

            while (result.next()) {
                paciente = new Paciente();

                paciente.setId(result.getInt("id"));
                paciente.setNome(result.getString("nome"));
                paciente.setCpf_cnpj(result.getString("cpf_cnpj"));
                paciente.setData_nascimento(result.getDate("data_nascimento"));
                paciente.setEndereco(result.getString("endereco"));
                paciente.setTelefone(result.getString("telefone"));
                paciente.setGenero(result.getBoolean("genero"));

                pacienteArrayList.add(paciente);
            }

            return pacienteArrayList;

        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }
}
