package external;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public Connection ConnectionDb() {
        try{
            String url = "jdbc:postgresql://localhost:5432/Vacinacoes";
            String username = "postgres";
            String password = "2121";

            Connection connection = DriverManager.getConnection(url, username, password);

            return connection;

        }catch (Error | SQLException e){
            throw new Error(e);
        }
    }
}
