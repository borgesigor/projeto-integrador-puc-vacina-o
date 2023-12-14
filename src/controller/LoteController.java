package controller;

import model.Lote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoteController {
    Connection connection;

    public LoteController(Connection connection) {
        this.connection = connection;
    }

    public Lote findById(Integer id) {
        try{
            String query = "SELECT * FROM lote WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                throw new Error("Não foi encontrado nenhum lote.");
            }

            Lote lote = null;

            while (result.next()) {
                lote = new Lote();

                lote.setId(result.getInt("id"));
                lote.setData_fabricacao(result.getDate("data_fabricacao"));
                lote.setData_validade(result.getDate("data_validade"));
                lote.setRemetente(result.getString("remetente"));
                lote.setArmazenamento_recomendado(result.getString("armazenamento_recomendado"));
            }

            return lote;
        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }

    public ArrayList<Lote> listAll(){
        try{
            String query = "SELECT * FROM lote";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                throw new Error("Não foi encontrado nenhum lote.");
            }

            ArrayList<Lote> loteArrayList = new ArrayList<>();
            Lote lote = null;

            while (result.next()) {
                lote = new Lote();

                lote.setId(result.getInt("id"));
                lote.setData_fabricacao(result.getDate("data_fabricacao"));
                lote.setData_validade(result.getDate("data_validade"));
                lote.setRemetente(result.getString("remetente"));
                lote.setArmazenamento_recomendado(result.getString("armazenamento_recomendado"));

                loteArrayList.add(lote);
            }

            return loteArrayList;

        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }

    public Lote create(Lote dados) {
        try{
            String query = "INSERT INTO lote (data_fabricacao, data_validade, remetente, armazenamento_recomendado) VALUES (?, ?, ?, ?) RETURNING id";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setDate(1, dados.getData_fabricacao());
            preparedStatement.setDate(2, dados.getData_validade());
            preparedStatement.setString(3, dados.getRemetente());
            preparedStatement.setString(4, dados.getArmazenamento_recomendado());

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
