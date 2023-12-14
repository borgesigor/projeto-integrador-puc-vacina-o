package controller;

import model.Ampola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AmpolaController {
    Connection connection;

    public AmpolaController(Connection connection) {
        this.connection = connection;
    }

    public Ampola create(Ampola dados) {
        try {
            String query = "INSERT INTO ampola (quantidade_doses, id_vacina, id_lote) VALUES (?, ?, ?) RETURNING id";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, dados.getQuantidade_doses());
            preparedStatement.setInt(2, dados.getId_vacina());
            preparedStatement.setInt(3, dados.getId_lote());

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

    public Ampola findById(Integer id) {
        try{

            String query = "SELECT * FROM ampola WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                throw new Error("Não foi encontrado nenhuma ampola.");
            }

            Ampola ampola = null;

            while (result.next()) {
                ampola = new Ampola();

                ampola.setId(result.getInt("id"));
                ampola.setQuantidade_doses(result.getInt("quantidade_doses"));
                ampola.setId_vacina(result.getInt("id_vacina"));
                ampola.setId_lote(result.getInt("id_lote"));
            }

            return ampola;

        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }

    public ArrayList<Ampola> listAll(){
        try{

            String query = "SELECT * FROM ampola";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                throw new Error("Não foi encontrado nenhuma ampola.");
            }

            ArrayList<Ampola> ampolaArrayList = new ArrayList<>();
            Ampola ampola = null;

            while (result.next()) {
                ampola = new Ampola();

                ampola.setId(result.getInt("id"));
                ampola.setQuantidade_doses(result.getInt("quantidade_doses"));
                ampola.setId_vacina(result.getInt("id_vacina"));
                ampola.setId_lote(result.getInt("id_lote"));

                ampolaArrayList.add(ampola);
            }

            return ampolaArrayList;

        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }

    public ArrayList<Ampola> findAllByLoteId(Integer id) {
        try{

            String query = "SELECT * FROM ampola WHERE id_lote=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                throw new Error("Não foi encontrado nenhuma ampola.");
            }

            Ampola ampola = null;
            ArrayList<Ampola> ampolaArrayList = new ArrayList<>();

            while (result.next()) {
                ampola = new Ampola();

                ampola.setId(result.getInt("id"));
                ampola.setQuantidade_doses(result.getInt("quantidade_doses"));
                ampola.setId_vacina(result.getInt("id_vacina"));
                ampola.setId_lote(result.getInt("id_lote"));

                ampolaArrayList.add(ampola);
            }

            return ampolaArrayList;

        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }

    public ArrayList<Ampola> findAllByVacinaId(Integer id) {
        try{

            String query = "SELECT * FROM ampola WHERE id_vacina=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                throw new Error("Não foi encontrado nenhuma ampola.");
            }

            Ampola ampola = null;
            ArrayList<Ampola> ampolaArrayList = new ArrayList<>();

            while (result.next()) {
                ampola = new Ampola();

                ampola.setId(result.getInt("id"));
                ampola.setQuantidade_doses(result.getInt("quantidade_doses"));
                ampola.setId_vacina(result.getInt("id_vacina"));
                ampola.setId_lote(result.getInt("id_lote"));

                ampolaArrayList.add(ampola);
            }

            return ampolaArrayList;

        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }
}
