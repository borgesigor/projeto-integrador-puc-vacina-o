package controller;

import model.Vacina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VacinaController {
    Connection connection;

    public VacinaController(Connection connection) {
        this.connection = connection;
    }

    public Vacina findById(Integer id) {
        try{
            String query = "SELECT * FROM vacina WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                throw new Error("Não foi encontrado nenhuma vacina.");
            }

            Vacina vacina = null;

            while (result.next()) {
                vacina = new Vacina();

                vacina.setId(result.getInt("id"));
                vacina.setNome(result.getString("nome"));
                vacina.setPais_origem(result.getString("pais_origem"));
                vacina.setFabricante(result.getString("fabricante"));
            }

            return vacina;
        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }

    public ArrayList<Vacina> listAll(){
        try{
            String query = "SELECT * FROM vacina";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                throw new Error("Não foi encontrado nenhuma vacina.");
            }

            ArrayList<Vacina> vacinaArrayList = new ArrayList<>();
            Vacina vacina = null;

            while (result.next()) {
                vacina = new Vacina();

                vacina.setId(result.getInt("id"));
                vacina.setNome(result.getString("nome"));
                vacina.setPais_origem(result.getString("pais_origem"));
                vacina.setFabricante(result.getString("fabricante"));

                vacinaArrayList.add(vacina);
            }

            connection.close();

            return vacinaArrayList;

        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }

    public Vacina create(Vacina dados) {
        try{

            String query = "INSERT INTO vacina (nome, pais_origem, fabricante) VALUES (?, ?, ?) RETURNING id";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, dados.getNome());
            preparedStatement.setString(2, dados.getPais_origem());
            preparedStatement.setString(3, dados.getFabricante());

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
